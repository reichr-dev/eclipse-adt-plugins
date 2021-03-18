package com.devepos.adt.atm.internal.tagging;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.devepos.adt.atm.model.abaptags.ITagPreviewInfo;
import com.devepos.adt.atm.model.abaptags.util.AbapTagsResourceFactory;
import com.devepos.adt.base.content.AbstractEmfContentHandler;
import com.sap.adt.communication.content.AdtMediaType;

/**
 * Content Handler for {@link ITagPreviewInfo}
 *
 * @author stockbal
 */
public class TagPreviewInfoContentHandler extends AbstractEmfContentHandler<ITagPreviewInfo> {

	public TagPreviewInfoContentHandler() {
		super(AdtMediaType.APPLICATION_XML, ".abaptagprevinfo");
	}

	@Override
	public Class<ITagPreviewInfo> getSupportedDataType() {
		return ITagPreviewInfo.class;
	}

	@Override
	protected Resource createResource() {
		return new AbapTagsResourceFactory().createResource(getVirtualResourceUri());
	}

	@Override
	protected ITagPreviewInfo getRootElement(final EObject rootElement) {
		return (ITagPreviewInfo) rootElement;
	}

}
