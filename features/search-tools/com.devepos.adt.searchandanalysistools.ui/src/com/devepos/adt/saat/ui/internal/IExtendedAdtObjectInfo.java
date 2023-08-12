package com.devepos.adt.saat.ui.internal;

import com.devepos.adt.base.ObjectType;

/**
 * Additional information about a result object from an object search
 *
 * @author stockbal
 */
public interface IExtendedAdtObjectInfo {
  String API_STATE_DEPRECATED = "DEPRECATED";
  String API_STATE_RELEASED = "RELEASED";

  /**
   * Returns the name of responsible person of this ADT object
   *
   * @return the name of responsible person of this ADT object
   */
  String getOwner();

  /**
   * Returns the source type of the object search result <br>
   * This is only relevant for objects of type {@link ObjectType#DATA_DEFINITION}
   * at the moment
   *
   * @return
   */
  IDataSourceType getSourceType();

  /**
   * Returns <code>true</code> if the result object is released
   *
   * @return
   */
  boolean isReleased();
}
