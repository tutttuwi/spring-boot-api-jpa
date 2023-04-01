package com.example.system.constant;

/**
 * ユーティリティ定数定義.
 */
public enum UtilConst {
  // @formatter:off

  LOCALDATE_FORMAT("yyyy/MM/dd"),
  LOCALDATETIME_FORMAT("yyyy/[]M/[]d []H:[]m:[]s"),
  ;

  // @formatter:on

  /**
   * Property Key Name.
   */
  public final String KEY;

  /**
   * Constractor.
   */
  UtilConst(String key) {
    this.KEY = key;
  }

}
