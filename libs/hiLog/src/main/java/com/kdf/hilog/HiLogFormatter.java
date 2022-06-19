package com.kdf.hilog;

public interface HiLogFormatter<T> {

    String format(T data);
}