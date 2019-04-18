package com.github.wxiaoqi.gate.demo.provider.api;

import lombok.Data;

/**
 * @author huangjh
 * @date 2019/4/16 17:51
 */
@Data
public class PayPaymentOrder {

    private String paySubject;
    private String payOutTradeNo;
    private String payTotalAmt;

    public PayPaymentOrder(String paySubject, String payOutTradeNo, String payTotalAmt) {
        this.paySubject = paySubject;
        this.payOutTradeNo = payOutTradeNo;
        this.payTotalAmt = payTotalAmt;
    }
}
