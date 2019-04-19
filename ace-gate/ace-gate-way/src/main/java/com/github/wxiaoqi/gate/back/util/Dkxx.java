package com.github.wxiaoqi.gate.back.util;

import lombok.Data;

/**
 * @author huangjh
 * @date 2019/4/19 14:31
 */
@Data
public class Dkxx {
    private String dkzh;//贷款账号

    private String htbh;//合同编号

    private String jjxh;//借据序号

    private String dkpz;//贷款品种

    private String dkffje;//贷款发放金额

    public String getDkzh() {
        return dkzh;
    }

    public void setDkzh(String dkzh) {
        this.dkzh = dkzh;
    }

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getJjxh() {
        return jjxh;
    }

    public void setJjxh(String jjxh) {
        this.jjxh = jjxh;
    }

    public String getDkpz() {
        return dkpz;
    }

    public void setDkpz(String dkpz) {
        this.dkpz = dkpz;
    }

    public String getDkffje() {
        return dkffje;
    }

    public void setDkffje(String dkffje) {
        this.dkffje = dkffje;
    }
}
