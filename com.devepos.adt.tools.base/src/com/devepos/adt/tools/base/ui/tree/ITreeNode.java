package com.devepos.adt.tools.base.ui.tree;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.graphics.Image;

import com.devepos.adt.tools.base.util.IPropertyBag;

/**
 * Base Tree node which holds some general information about how a specific node
 * shall be displayed in the tree.<br>
 * e.g. <br>
 * <ul>
 * <li>Which Image should be shown</li>
 * </ul>
 *
 * @author stockbal
 */
public interface ITreeNode extends IPropertyBag, IAdaptable {
	/**
	 * Finds and returns the root node of this tree node. The root node is a node
	 * which has no parent node. If this node has no parent than it automatically is
	 * the root node
	 *
	 * @return
	 */
	ITreeNode getRoot();

	/**
	 * @return the parent node of this node
	 */
	ITreeNode getParent();

	/**
	 * Sets the parent of this node
	 *
	 * @param parent the parent node
	 */
	void setParent(ITreeNode parent);

	/**
	 * @return the image id of the node
	 */
	Image getImage();

	/**
	 * @return the internal name of the node
	 */
	String getName();

	/**
	 * Sets the name of the node
	 *
	 * @param name
	 */
	void setName(String name);

	/**
	 * @return the display name of the node
	 */
	String getDisplayName();

	/**
	 * Sets the display name of the node
	 *
	 * @param displayName
	 */
	void setDisplayName(String displayName);

	/**
	 * @return the description for the node
	 */
	String getDescription();

	/**
	 * Sets the description of the new
	 *
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Sets additional information which can be retrieved via
	 * {@link IAdaptable#getAdapter(Class)}
	 *
	 * @param info object with additional information for this node
	 */
	void setAdditionalInfo(Object info);

}
