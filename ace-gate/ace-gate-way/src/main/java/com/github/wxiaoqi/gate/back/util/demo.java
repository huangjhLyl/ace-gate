package com.github.wxiaoqi.gate.back.util;

import lombok.Data;

/**
 * @author huangjh
 * @date 2019/4/19 11:46
 */
@Data
public class demo {
    private String zjlx;

    private String zjhm;

    private String xingming;

    @Override
    public String toString() {
        return "demo{" +
                "zjlx='" + zjlx + '\'' +
                ", zjhm='" + zjhm + '\'' +
                ", xingming='" + xingming + '\'' +
                '}';
    }
}
