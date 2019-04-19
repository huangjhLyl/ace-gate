package com.github.wxiaoqi.gate.back.util;

import javax.xml.bind.annotation.*;

/**
 * @author huangjh
 * @date 2019/4/19 9:29
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "head"
})
public class ROOT {
    @XmlElement(required = true,name="HEAD")
    Head head = new Head("","","","","");

}
