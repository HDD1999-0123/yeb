package com.hdd.server.yebenum;

import lombok.val;

/**
 * @author hedd
 * @create 2021/4/23
 */
public enum JobLevelEnum {

    LEVEL_CHU("初级"),
    LEVEL_ZHONG("中级"),
    LEVEL_GAO("高级"),
    LEVEL_ZHENGGAO("正高级"),
    LEVEL_FUGAO("副高级");


    private final String val;

    JobLevelEnum(String val) {
        this.val = val;
    }
}
