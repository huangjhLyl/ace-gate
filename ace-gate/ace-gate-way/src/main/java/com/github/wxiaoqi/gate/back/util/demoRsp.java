package com.github.wxiaoqi.gate.back.util;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangjh
 * @date 2019/4/19 11:46
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dkxx",
        "xxjyjg"
})
public class demoRsp {
    protected String xxjyjg;//检查校验结果

    @XmlElement(required = true,name="dkxx")
    protected List<Dkxx> dkxx = new ArrayList<>();


    public void setXxjyjg(String xxjyjg) {
        this.xxjyjg = xxjyjg;
    }
}
