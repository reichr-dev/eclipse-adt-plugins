package com.devepos.adt.tools.base.ui.tree;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.WorkbenchJob;

import com.devepos.adt.tools.base.elementinfo.ILazyLoadableContent;
import com.devepos.adt.tools.base.elementinfo.LazyLoadingRefreshMode;
import com.devepos.adt.tools.base.internal.messages.Messages;

/**
 * Tree content provider which implements a lazy loading mechanism to fetch
 * further child nodes
 *
 * @author stockbal
 */
public class LazyLoadingTreeContentProvider implements ITreeContentProvider {

	@FunctionalInterface
	public interface IExpanderCheckFunction {
		/**
		 * If the given tree node should be expanded the method has to return
		 * <code>true</code> <br>
		 * The default implementation allows expansion for all nodes. Sub classes should
		 * override for custom logic
		 *
		 * @param  node tree node that has child nodes
		 * @return      <code>true</code> if the given node should be expanded
		 */
		boolean shouldExpandNode(ITreeNode node);
	}

	private TreeViewer viewer;
	private final int refreshModeExpansionLevel;
	private final LazyLoadingRefreshMode refreshMode;
	private IExpanderCheckFunction expansionCheck;

	/**
	 * Creates new instance of a tree content provider that support lazy loading of
	 * the child nodes of a given node
	 *
	 * @see ILazyLoadingNode
	 */
	public LazyLoadingTreeContentProvider() {
		this(null, 1);
	}

	/**
	 * Creates new instance of a tree content provider that support lazy loading of
	 * the child nodes of a given node. <br>
	 * This constructor allows the setting of refresh mode which shall be used to
	 * refresh/expand collection nodes which were lazily loaded. <br>
	 * If a non <code>null</code> value was supplied any <code>refresh mode</code>
	 * at a node that was set via
	 * {@link ILazyLoadableContent#setContentRefreshMode(LazyLoadingRefreshMode)}
	 * will be ignored <br>
	 * <br>
	 * <strong>WARNING:</strong> <br>
	 * Setting the refresh mode to
	 * {@link LazyLoadingRefreshMode#ROOT_AND_ALL_CHILDREN} can potentially result
	 * in a momentary freezing of the user interface if the resulting hierarchy is
	 * very big
	 *
	 * @param refreshMode           the mode which shall be used to refresh lazy
	 *                              loaded children
	 * @param refreshExpansionLevel the level to which the child nodes that will be
	 *                              refreshed depending on the
	 *                              <code>refreshMode</code> parameter
	 * @see                         ILazyLoadingNode
	 * @see                         ICollectionTreeNode
	 */
	public LazyLoadingTreeContentProvider(final LazyLoadingRefreshMode refreshMode, final int refreshExpansionLevel) {
		this.viewer = null;
		this.refreshMode = refreshMode;
		assertTrue(refreshExpansionLevel == TreeViewer.ALL_LEVELS || refreshExpansionLevel > 0);
		this.refreshModeExpansionLevel = refreshExpansionLevel;
	}

	/**
	 * Sets expansion checker to be used during refresh of lazy loading loading node
	 *
	 * @param expansionCheck the check function to be used during expansion of a
	 *                       {@link ICollectionNode}
	 */
	public void setExpansionChecker(final IExpanderCheckFunction expansionCheck) {
		this.expansionCheck = expansionCheck;
	}

	@Override
	public Object[] getChildren(final Object parentElement) {
		if (parentElement instanceof LoadingTreeItemsNode) {
			return null;
		}
		if (!(parentElement instanceof ICollectionTreeNode)) {
			return null;
		}
		final ICollectionTreeNode collectionNode = (ICollectionTreeNode) parentElement;

		if (parentElement instanceof ILazyLoadingNode) {
			final ILazyLoadingNode lazyNode = (ILazyLoadingNode) parentElement;
			if (lazyNode.isLoaded()) {
				return getChildren(collectionNode);
			} else if (lazyNode.isLoading()) {
				return new Object[] { LoadingTreeItemsNode.INSTANCE };
			} else {
				// start retrieval of the children of the node
				startChildNodeRetrieval(lazyNode);
				return new Object[] { LoadingTreeItemsNode.INSTANCE };
			}
		} else if (parentElement instanceof ICollectionTreeNode) {
			return getChildren((ICollectionTreeNode) parentElement);
		}
		return null;
	}

	private void startChildNodeRetrieval(final ILazyLoadingNode lazyNode) {
		String jobName = lazyNode.getLazyLoadingJobName();
		if (jobName == null) {
			jobName = NLS.bind(Messages.LazyLoadingTreeContentProvider_LoadingChildNodes_xmsg,
				((ITreeNode) lazyNode).getDisplayName());
		}
		Job.create(jobName, new ChildElementLoader(this.viewer.getControl().getDisplay(), lazyNode)).schedule();

	}

	private Object[] getChildren(final ICollectionTreeNode collectionNode) {
		final List<ITreeNode> children = collectionNode.getChildren();
		if (children == null) {
			return null;
		} else {
			return children.toArray(new Object[children.size()]);
		}
	}

	@Override
	public Object[] getElements(final Object inputElement) {
		return (Object[]) inputElement;

	}

	@Override
	public Object getParent(final Object element) {
		if (element instanceof ITreeNode) {
			return ((ITreeNode) element).getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(final Object element) {
		if (element instanceof LoadingTreeItemsNode) {
			return false;
		} else if (element instanceof ICollectionTreeNode) {
			if (element instanceof ILazyLoadingNode) {
				if (((ILazyLoadingNode) element).isLoaded()) {
					final List<ITreeNode> children = ((ICollectionTreeNode) element).getChildren();
					return children != null && !children.isEmpty();
				}
				return true;
			} else {
				return ((ICollectionTreeNode) element).hasChildren();
			}
		}

		return false;
	}

	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
		this.viewer = (TreeViewer) viewer;
	}

	/**
	 * Job for loading the child nodes of an element
	 *
	 * @author stockbal
	 */
	protected class ChildElementLoader implements ICoreRunnable {
		private final Display display;
		private final ILazyLoadingNode lazyLoadingNode;

		public ChildElementLoader(final Display display, final ILazyLoadingNode lazyLoadingNode) {
			this.display = display;
			this.lazyLoadingNode = lazyLoadingNode;

		}

		@Override
		public void run(final IProgressMonitor monitor) throws CoreException {
			try {
				this.lazyLoadingNode.loadChildren();
			} catch (final Throwable t) {
			}
			monitor.done();
			final WorkbenchJob treeUpdateJob = new WorkbenchJob(this.display,
				Messages.LazyLoadingTreeContentProvider_UpdatingTreeContent_xmsg) {

				@Override
				public IStatus runInUIThread(final IProgressMonitor monitor) {
					final Control control = LazyLoadingTreeContentProvider.this.viewer.getControl();
					if (control.isDisposed()) {
						return Status.CANCEL_STATUS;
					}
					monitor.beginTask(Messages.LazyLoadingTreeContentProvider_UpdatingTreeContent_xmsg, -1);
					LazyLoadingTreeContentProvider.this.viewer.refresh(ChildElementLoader.this.lazyLoadingNode);
					refreshLazyNode();
					monitor.done();
					return Status.OK_STATUS;
				}
			};
			treeUpdateJob.setSystem(true);
			treeUpdateJob.schedule();
		}

		private void refreshLazyNode() {
			final LazyLoadingRefreshMode refreshMode = LazyLoadingTreeContentProvider.this.refreshMode != null
				? LazyLoadingTreeContentProvider.this.refreshMode
				: this.lazyLoadingNode.getContentRefreshMode();
			if (refreshMode == LazyLoadingRefreshMode.ROOT_ONLY) {
				return;
			}
			if (!(this.lazyLoadingNode instanceof ICollectionTreeNode)) {
				return;
			}
			final List<ITreeNode> children = ((ICollectionTreeNode) this.lazyLoadingNode).getChildren();
			if (children == null || children.isEmpty()) {
				return;
			}
			for (final ITreeNode child : children.stream()
				.filter(child -> child instanceof ICollectionTreeNode)
				.collect(Collectors.toList())) {
				if (refreshMode == LazyLoadingRefreshMode.ROOT_AND_ALL_CHILDREN) {
					expandNode(child);
				} else if (refreshMode == LazyLoadingRefreshMode.ROOT_AND_NON_LAZY_CHILDREN
					&& !(child instanceof ILazyLoadingNode)) {
					expandNode(child);
				}
			}
		}

		private void expandNode(final ITreeNode child) {
			if (LazyLoadingTreeContentProvider.this.expansionCheck != null) {
				if (!LazyLoadingTreeContentProvider.this.expansionCheck.shouldExpandNode(child)) {
					return;
				}
			}
			LazyLoadingTreeContentProvider.this.viewer.expandToLevel(child,
				LazyLoadingTreeContentProvider.this.refreshModeExpansionLevel, true);
		}

	}

}
