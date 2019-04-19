package com.github.wxiaoqi.gate.back.util;

import com.github.wxiaoqi.gate.back.entity.User;

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
public class UserReq extends ROOT{
    @XmlElement(required = true,name="body")
    protected demo body = new demo();

}
