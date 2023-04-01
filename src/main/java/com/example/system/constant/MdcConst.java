package com.example.system.constant;

/**
 * MDCログ用 定数定義.<br/>
 * MDC(Mapped Diagnostic Contexts)ログに設定するキーを管理します。
 */
public enum MdcConst {
  // @formatter:off
  X_TRACK_ID("X-System-Track-Id"), //TODO: システム個別設定

  X_SESSION_ID("X-System-Session-Id"), //TODO: システム個別設定
  X_REQUEST_ID("X-System-Request-Id"), //TODO: システム個別設定
  ;

  // @formatter:on

  /**
   * Property Key Name.
   */
  public final String KEY;

  /**
   * Constractor.
   */
  MdcConst(String key) {
    this.KEY = key;
  }

}
