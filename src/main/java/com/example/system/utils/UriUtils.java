package com.example.system.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * URIユーティリティ.
 */
@Slf4j
@Component
public class UriUtils {

    private static String schema;
    private static String hostAndPort;

    /**
     * リソースURIパスを返却します.
     *
     * @param resourcePath String
     * @return
     */
    public static String getUriPath(String resourcePath) {
        return schema + ":" + hostAndPort + resourcePath;
    }

    /**
     * Static変数にDIするためのメソッド定義
     */
    @Autowired
    public void setValue(
            @Value("${settings.uri.schema}") String schema,
            @Value("${settings.uri.host}") String hostAndPort
    ) {
        UriUtils.schema = schema;
        UriUtils.hostAndPort = hostAndPort;
    }
}