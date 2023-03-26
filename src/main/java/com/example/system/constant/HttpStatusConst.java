package com.example.system.constant;

/**
 * 定数定義.
 */
public enum MdcConst {
    // @formatter:off

    X_TRACK_ID("X_TRACK_ID"),
    ;

    // @formatter:on

    /**
     * Property Key Name.
     */
    public final String KEY;

    /**
     * Constractor.
     */
    private MdcConst(String key) {
        this.KEY = key;
    }

}
