package com.example.system.constant;

/**
 * AlphaNumeric Const Class.
 */
public enum SymbolConst {
    UNDER_SCORE("_"), DOT("."), SHARP("#");

    public final String VALUE;

    private SymbolConst(String value) {
        this.VALUE = value;
    }
}
