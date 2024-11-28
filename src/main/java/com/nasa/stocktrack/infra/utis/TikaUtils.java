package com.nasa.stocktrack.infra.utis;

import org.apache.tika.Tika;

public class TikaUtils {

    private static final Tika TIKA = new Tika();

    public static String getMimeType(byte[] content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        return TIKA.detect(content);
    }
}
