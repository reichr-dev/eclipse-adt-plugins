package com.devepos.adt.tools.base.ui.internal;

import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.BundleContext;

import com.devepos.adt.tools.base.plugin.AbstractAdtUIPlugin;
import com.devepos.adt.tools.base.ui.IAdtToolsBaseImages;

/**
 * The activator class controls the plug-in life cycle
 */
public class AdtToolsBaseUIPlugin extends AbstractAdtUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.devepos.adt.tools.base.ui"; //$NON-NLS-1$

	// The shared instance
	private static AdtToolsBaseUIPlugin plugin;

	/**
	 * The constructor
	 */
	public AdtToolsBaseUIPlugin() {
		super(PLUGIN_ID);
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AdtToolsBaseUIPlugin getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(final ImageRegistry imageRegistry) {
		registerImage(imageRegistry, IAdtToolsBaseImages.ARROW_DOWN, "icons/ArrowDown.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.ARROW_UP, "icons/ArrowUp.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.REFRESH, "icons/Refresh.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.IMPORT, "icons/ImportWizard.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.EXPORT, "icons/ExportWizard.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.EXPAND_ALL, "icons/ExpandAll.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.COLLAPSE_ALL, "icons/CollapseAll.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.WAITING_INDICATOR, "icons/WaitingIndicator.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.EDIT_ACTION, "icons/EditMode.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.UNLOCK_ACTION, "icons/Unlock.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.SAP_GUI_OBJECT, "icons/SAPGUIObject.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.DATA_PREVIEW, "icons/DataPreview.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.WHERE_USED_LIST, "icons/WhereUsed.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.SEARCH, "icons/Search.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.RUN_OBJECT, "icons/RunObject.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.OTHER_OBJECT, "icons/OtherObject.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.AUTOMATIC_LAYOUT, "icons/AutomaticOrientation.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.HORIZONTAL_LAYOUT, "icons/HorizontalOrientation.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.VERTICAL_LAYOUT, "icons/VerticalOrientation.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.TRANSPORT, "icons/Transport.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.SHARE, "icons/Share.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.SHARE_OVR, "icons/ovr/Share.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.USER, "icons/User.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.DELETE_OVR, "icons/ovr/Delete.png");
		registerImage(imageRegistry, IAdtToolsBaseImages.VIEW_MENU, "icons/ViewMenu.png");

		registerDeleteOvr(imageRegistry);
	}

	private void registerDeleteOvr(final ImageRegistry imageRegistry) {
		imageRegistry.put(IAdtToolsBaseImages.UNSHARE, overlayImage(imageRegistry.get(IAdtToolsBaseImages.SHARE),
			new String[] { null, null, null, IAdtToolsBaseImages.DELETE_OVR }));

	}

}
