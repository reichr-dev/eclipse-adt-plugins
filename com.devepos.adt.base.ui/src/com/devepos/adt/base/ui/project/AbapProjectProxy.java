package com.devepos.adt.base.ui.project;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.devepos.adt.base.ui.util.AdtUIUtil;
import com.devepos.adt.base.util.IPropertyChangeListener;
import com.devepos.adt.base.util.ObjectContainer;
import com.devepos.adt.base.util.PropertyChangeEvent;
import com.sap.adt.communication.session.AdtSystemSessionFactory;
import com.sap.adt.communication.session.ISystemSession;
import com.sap.adt.destinations.model.IDestinationData;
import com.sap.adt.destinations.ui.logon.AdtLogonServiceUIFactory;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;
import com.sap.adt.tools.core.project.IAbapProject;

/**
 * Proxy with convencience methods on {@link IProject} and {@link IAbapProject}
 *
 * @author stockbal
 */
public class AbapProjectProxy implements IAbapProjectProvider {

  private Optional<IProject> project;
  private Set<IPropertyChangeListener> propertyChangeListeners = new HashSet<>();

  /**
   * @param project
   */
  public AbapProjectProxy(final IProject project) {
    this.project = Optional.ofNullable(project);
  }

  /**
   * Updates the project reference in the proxy
   *
   * @param project
   */
  @Override
  public void setProject(final IProject project) {
    fireProjectChanged(project, this.project.orElse(null));
    this.project = Optional.ofNullable(project);
  }

  /**
   * Retrieves the ABAP nature from the project
   *
   * @return
   */
  @Override
  public IAbapProject getAbapProject() {
    return project.get().getAdapter(IAbapProject.class);
  }

  /**
   * Ensures that the current user is logged on to the proxy project
   *
   * @return
   */
  @Override
  public boolean ensureLoggedOn() {
    if (!hasProject()) {
      return false;
    }
    final ObjectContainer<Boolean> isLoggedOncontainer = new ObjectContainer<>(Boolean.FALSE);
    Display.getDefault().syncExec(() -> {
      isLoggedOncontainer.setObject(AdtLogonServiceUIFactory.createLogonServiceUI()
          .ensureLoggedOn(getAbapProject().getDestinationData(), PlatformUI.getWorkbench()
              .getProgressService())
          .isOK());
    });
    return isLoggedOncontainer.getObject();
  }

  /**
   * Retrieves the destination id of the project
   *
   * @return
   */
  @Override
  public String getDestinationId() {
    return getAbapProject().getDestinationId();
  }

  /**
   * Retrieves the name of the project
   *
   * @return
   */
  @Override
  public String getProjectName() {
    return project.get().getName();
  }

  @Override
  public boolean hasProject() {
    return project.isPresent() && getAbapProject() != null;
  }

  @Override
  public IProject getProject() {
    return project.orElse(null);
  }

  @Override
  public void openObjectReference(final IAdtObjectReference objectReference) {
    AdtUIUtil.navigateWithObjectReference(objectReference, project.get());
  }

  @Override
  public void openObjectReferenceInSapGui(final IAdtObjectReference objectReference) {
    AdtUIUtil.openAdtObjectRefInSapGui(objectReference, project.get());
  }

  @Override
  public ISystemSession createStatelessSession() {
    return AdtSystemSessionFactory.createSystemSessionFactory()
        .createStatelessSession(getDestinationId());
  }

  @Override
  public IAbapProjectProvider copy() {
    return new AbapProjectProxy(project.orElse(null));
  }

  @Override
  public IDestinationData getDestinationData() {
    if (!hasProject()) {
      return null;
    }
    return getAbapProject().getDestinationData();
  }

  @Override
  public void addPropertyChangeListener(final IPropertyChangeListener l) {
    propertyChangeListeners.add(l);
  }

  @Override
  public void removePropertyChangeListener(final IPropertyChangeListener l) {
    propertyChangeListeners.remove(l);
  }

  private void fireProjectChanged(final IProject oldProject, final IProject newProject) {
    if (propertyChangeListeners.isEmpty()) {
      return;
    }
    var event = new PropertyChangeEvent(this, IAbapProjectProvider.PROPERTY_PROJECT, oldProject,
        newProject);
    for (var listener : propertyChangeListeners) {
      listener.propertyChanged(event);
    }
  }

}
