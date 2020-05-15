/**
 */
package com.devepos.adt.abaptags;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Tag</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getDescription <em>Description</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getChildTags <em>Child Tags</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#isIsRoot <em>Is Root</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getCreatedBy <em>Created By</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getCreatedDateTime <em>Created Date Time</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getChangedBy <em>Changed By</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getChangedDateTime <em>Changed Date Time</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getTaggedObjectCount <em>Tagged Object Count</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#isChanged <em>Changed</em>}</li>
 *   <li>{@link com.devepos.adt.abaptags.ITag#getParentTagId <em>Parent Tag Id</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.abaptags.IAbapTagsPackage#getTag()
 * @model extendedMetaData="kind='elementOnly' name='tag'"
 * @generated
 */
public interface ITag extends ITagBase {

	/**
	 * Validates this tag
	 *
	 * @return the status of the validation
	 */
	IStatus validate();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Description</em>' attribute.
	 * @see       #setDescription(String)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_Description()
	 * @model     extendedMetaData="kind='attribute' name='description'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getDescription
	 * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @param     value the new value of the '<em>Description</em>' attribute.
	 * @see             #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Child Tags</b></em>' containment reference list.
	 * The list contents are of type {@link com.devepos.adt.abaptags.ITag}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Tags</em>' containment reference list.
	 * @see com.devepos.adt.abaptags.IAbapTagsPackage#getTag_ChildTags()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='tag' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ITag> getChildTags();

	/**
	 * Returns the value of the '<em><b>Is Root</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Is Root</em>' attribute.
	 * @see       #setIsRoot(boolean)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_IsRoot()
	 * @model     extendedMetaData="kind='attribute' name='isRoot'"
	 * @generated
	 */
	boolean isIsRoot();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#isIsRoot <em>Is Root</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Root</em>' attribute.
	 * @see #isIsRoot()
	 * @generated
	 */
	void setIsRoot(boolean value);

	/**
	 * Returns the value of the '<em><b>Created By</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Created By</em>' attribute.
	 * @see       #setCreatedBy(String)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_CreatedBy()
	 * @model     extendedMetaData="kind='attribute' name='createdBy'
	 *            namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreatedBy();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getCreatedBy
	 * <em>Created By</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @param     value the new value of the '<em>Created By</em>' attribute.
	 * @see             #getCreatedBy()
	 * @generated
	 */
	void setCreatedBy(String value);

	/**
	 * Returns the value of the '<em><b>Created Date Time</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Created Date Time</em>' attribute.
	 * @see       #setCreatedDateTime(String)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_CreatedDateTime()
	 * @model     extendedMetaData="kind='attribute' name='createdDateTime'
	 *            namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreatedDateTime();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getCreatedDateTime <em>Created Date Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created Date Time</em>' attribute.
	 * @see #getCreatedDateTime()
	 * @generated
	 */
	void setCreatedDateTime(String value);

	/**
	 * Returns the value of the '<em><b>Changed By</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Changed By</em>' attribute.
	 * @see       #setChangedBy(String)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_ChangedBy()
	 * @model     extendedMetaData="kind='attribute' name='changedBy'
	 *            namespace='##targetNamespace'"
	 * @generated
	 */
	String getChangedBy();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getChangedBy
	 * <em>Changed By</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @param     value the new value of the '<em>Changed By</em>' attribute.
	 * @see             #getChangedBy()
	 * @generated
	 */
	void setChangedBy(String value);

	/**
	 * Returns the value of the '<em><b>Changed Date Time</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @return    the value of the '<em>Changed Date Time</em>' attribute.
	 * @see       #setChangedDateTime(String)
	 * @see       com.devepos.adt.abaptags.IAbapTagsPackage#getTag_ChangedDateTime()
	 * @model     extendedMetaData="kind='attribute' name='changedDateTime'
	 *            namespace='##targetNamespace'"
	 * @generated
	 */
	String getChangedDateTime();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getChangedDateTime <em>Changed Date Time</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed Date Time</em>' attribute.
	 * @see #getChangedDateTime()
	 * @generated
	 */
	void setChangedDateTime(String value);

	/**
	 * Returns the value of the '<em><b>Tagged Object Count</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the value of the '<em>Tagged Object Count</em>' attribute.
	 * @see #setTaggedObjectCount(int)
	 * @see com.devepos.adt.abaptags.IAbapTagsPackage#getTag_TaggedObjectCount()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='taggedObjectCount' namespace='##targetNamespace'"
	 * @generated
	 */
	int getTaggedObjectCount();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getTaggedObjectCount <em>Tagged Object Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tagged Object Count</em>' attribute.
	 * @see #getTaggedObjectCount()
	 * @generated
	 */
	void setTaggedObjectCount(int value);

	/**
	 * Returns the value of the '<em><b>Changed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changed</em>' attribute.
	 * @see #setChanged(boolean)
	 * @see com.devepos.adt.abaptags.IAbapTagsPackage#getTag_Changed()
	 * @model extendedMetaData="kind='attribute' name='changed' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isChanged();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#isChanged <em>Changed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changed</em>' attribute.
	 * @see #isChanged()
	 * @generated
	 */
	void setChanged(boolean value);

	/**
	 * Returns the value of the '<em><b>Parent Tag Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Tag Id</em>' attribute.
	 * @see #setParentTagId(String)
	 * @see com.devepos.adt.abaptags.IAbapTagsPackage#getTag_ParentTagId()
	 * @model extendedMetaData="kind='attribute' name='parentTagId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getParentTagId();

	/**
	 * Sets the value of the '{@link com.devepos.adt.abaptags.ITag#getParentTagId <em>Parent Tag Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Tag Id</em>' attribute.
	 * @see #getParentTagId()
	 * @generated
	 */
	void setParentTagId(String value);

} // ITag
