package com.example.system.constant;

/**
 * 記号定数管理.
 */
public enum SymbolConst {
  UNDER_SCORE("_"), DOT("."), SHARP("#");

  public final String VALUE;

  SymbolConst(String value) {
    this.VALUE = value;
  }
}
