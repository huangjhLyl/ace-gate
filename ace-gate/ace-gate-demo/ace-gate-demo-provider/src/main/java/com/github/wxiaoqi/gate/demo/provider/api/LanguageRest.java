package com.github.wxiaoqi.gate.demo.provider.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.wxiaoqi.gate.agent.rest.ApiGateSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;


/**
 * Created by ace on 2017/7/9.
 */
@Controller
@RequestMapping("language")
@ApiGateSecurity
@Slf4j
public class LanguageRest {
    @RequestMapping(value = "/chinese",method = RequestMethod.GET)
    public @ResponseBody String sayChineseHelloWorld() throws InterruptedException {
        return "你好，世界！";
    }
    @RequestMapping(value = "/english",method = RequestMethod.GET)
    public @ResponseBody String sayEnglishHelloWorld(){
        return "Hello World！";
    }

    public static void main(String[] args) {
        String APP_PRIVATE_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnxMzrsWs2PJgMQRkEx9I/ej5re+ZinqTERCZkfgr3Q3pg3DXIVARhUm103CHeagjapb3T5iwwSDc59JbyaoGV/8rkmGBGtYXQv/fv3fcbZVi8nDkGypdp+DesSzTXbbURSzVelTIN5nl0TcfhHBnZYh0p/5Mm8Bg1FqCbOWX4i7ZdcaB0f7rINfSdvQjNOFPus4pAjCVdwUVMD1ZoGu22UriaoCfBfSZ0/UXqcT7PQQXwsXp2GiCBONQNp5zYownoWVkoAEFtbC5BgQXYnlkJlBaTuekbdHOqEqSqrN+Pl2N9qDhwhW2TsyRL9Pz69joR4E4Q/9LjSREDOQWx05/mQIDAQAB";
        String ALIPAY_PUBLIC_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfEzOuxazY8mAxBGQTH0j96Pmt75mKepMREJmR+CvdDemDcNchUBGFSbXTcId5qCNqlvdPmLDBINzn0lvJqgZX/yuSYYEa1hdC/9+/d9xtlWLycOQbKl2n4N6xLNNdttRFLNV6VMg3meXRNx+EcGdliHSn/kybwGDUWoJs5ZfiLtl1xoHR/usg19J29CM04U+6zikCMJV3BRUwPVmga7bZSuJqgJ8F9JnT9RepxPs9BBfCxenYaIIE41A2nnNijCehZWSgAQW1sLkGBBdieWQmUFpO56Rt0c6oSpKqs34+XY32oOHCFbZOzJEv0/Pr2OhHgThD/0uNJEQM5BbHTn+ZAgMBAAECggEAHRX2ujscqqJOokfKQJDU2NBTbtFDgsV8xH8DhWQaKRYVH1pvi4DHY1ah72JA8yaiKyglWiFyGC35Fk4pSEtVU5utjNtVLuH8aNy+PZUd8c+BwxCPNMRfUYXVUhH/J8B5OZjEA9Uisr/p/IIWujGPRkpYAiuGOGhJjCpsdJraVZmvVRr+xfz39hlt1/IowQSLLCZxg0NKrP6SgAuW/c1Bt3mIxx5+pn6DcpsF5rLwSzOdenH7y/fXHy8cw2ZyYQwspCsgNNXgWKZMeMaVhIIDUPZ37xmePbesbTGkpGGTmy8EczB7TFotCp8j9FtrkF8pVNDSx1t/jVq+AtwQPB0uAQKBgQDaSL+C7ZGQn4VeEg0rA/euX+WqMcgV6kp1yXJO1Tmuxm8uZ2QOMEgy7YP24kovyiAPkERxToscIOJ6tiHqdEFfZ+T89k0m+Ya/RWVQNkyIC18U0mVWtrEu1xX7C0J3FkxOuldjI06H4Em4gTHPSnYxwkcB0+Ju5n/ULnQvkCou2QKBgQC6j3wy7zMueSyZfqUyiAFyVyKRns5cdu7NqXIVVsemrsknd+wC37CBSBsxXQHFJL6hli2teoMdD8WzX4+IJrOZPR30Fi9pcAgcmEjY7Hqghc7K9DgD2q2kb0B5VCbKKybaevgnKUSkMTGWnYNcHbpEcHVretpLfQqGm7eN/0newQKBgAlRvctrNSHFjb4cay+A9dweAAIqKe61mAQ7W4P+Gn5ZvnlutJjeetahyVaLBrvPl1rSv/lObyX+SW0PxZbq67eVCfO62ERnttcHsXGhP9c3FRIggW8YUaw8RpX3PxkyfIOH9R8obLvkWYFlEqlU9oLdVoa/cxVlBeQbL56wOvzRAoGBALdH+cuMZir1DtTpr+AZ79nmMub/Llt22JsiKhs+uzxNIP/ud+ZG3n/UWCrB5dhJhiL3iH6TCraFZUQY0MkAxJb6jS/cj821K7L0BTVf9gU4D/Yhwdo/fzI5vgy0mLsrc6tW/e5zQ6VPUxlWO9RGvFU6apEiC8331VxzJPyS3n7BAoGBAKd/wew2fkRBJ+EPM8o61f36uf7LTy8w+zGeVld3pK+S7oGCuPA7HEz+CA8BxOZ72Ze8jT/judwG8N+8MGS6d8kjFVVTau/wgDgvO4nR+3MoTVx5DSQNLSDnNGOGFc2wJxnOb/UbQZWNZhgdDWt758OedWamm+Tdgr4QDFfcsTTP";
        String outtradeno="12313";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(outtradeno);
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
