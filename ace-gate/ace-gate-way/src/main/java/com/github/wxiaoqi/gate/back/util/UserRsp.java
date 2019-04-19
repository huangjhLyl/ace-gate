package com.github.wxiaoqi.gate.back.util;

import javax.xml.bind.annotation.*;

/**
 * @author huangjh
 * @date 2019/4/19 9:48
 */
@XmlRootElement(name = "ROOT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "body"
})
public class UserRsp extends ROOT{
    @XmlElement(required = true,name="BODY")
    protected demoRsp body = new demoRsp();

}
