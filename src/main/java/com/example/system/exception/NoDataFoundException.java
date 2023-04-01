package com.example.system.exception;

/**
 * データが存在しなかった場合のExceptionクラス.<br/>
 */
public class NoDataFoundException extends RuntimeException {

  /**
   * コンストラクタ.
   */
  public NoDataFoundException(String message) {
    super(message);
  }
}