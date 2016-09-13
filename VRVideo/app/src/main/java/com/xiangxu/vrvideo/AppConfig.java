package com.xiangxu.vrvideo;

/**
 * Created by xuxiang on 2016/9/8.
 */

public class AppConfig {
    public static boolean isDebug = true;  // turn off when release

    public static final int MAX_CACHE_AGE = 3600 * 12;       // 12 hours

    public static final long RESPONSE_CACHE_SIZE = 10240000; // 10 MB
    public static final int CONNECTION_TIME_OUT = 30;
    public static final int READ_TIME_OUT = 30;
    public static final int WRITE_TIME_OUT = 30;
}
