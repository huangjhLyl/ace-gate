package com.github.wxiaoqi.gate.back.util;

import com.github.wxiaoqi.gate.back.entity.User;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author huangjh
 * @date 2019/4/19 9:32
 */
@XmlRootElement
@Data
public class Head {
    private String chanCode;//发起渠道编号
    private String trsDate;//交易日期
    private String trsTime;//交易时间
    private String trsCode;//交易码
    private String trsSerial;//请求唯一编号

    private String resCode;//响应码
    private String resMsg;//响应信息
    private String spSerial;//综合平台流水号

    public Head(String chanCode, String trsDate, String trsTime, String trsCode, String trsSerial) {
        this.chanCode = chanCode;
        this.trsDate = trsDate;
        this.trsTime = trsTime;
        this.trsCode = trsCode;
        this.trsSerial = trsSerial;
    }

    public Head() {
    }

    @Override
    public String toString() {
        return "Head{" +
                "chanCode='" + chanCode + '\'' +
                ", trsDate='" + trsDate + '\'' +
                ", trsTime='" + trsTime + '\'' +
                ", trsCode='" + trsCode + '\'' +
                ", trsSerial='" + trsSerial + '\'' +
                ", resCode='" + resCode + '\'' +
                ", resMsg='" + resMsg + '\'' +
                ", spSerial='" + spSerial + '\'' +
                '}';
    }
}
