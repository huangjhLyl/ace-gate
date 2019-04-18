package com.github.wxiaoqi.gate.demo.provider.config;

/**
 * @author huangjh
 * @date 2019/4/16 16:28
 */
public class AlipayConfig {
    // 1.商户appid
    public static String APPID = "2019041663894172";

    // 2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfEzOuxazY8mAxBGQTH0j96Pmt75mKepMREJmR+CvdDemDcNchUBGFSbXTcId5qCNqlvdPmLDBINzn0lvJqgZX/yuSYYEa1hdC/9+/d9xtlWLycOQbKl2n4N6xLNNdttRFLNV6VMg3meXRNx+EcGdliHSn/kybwGDUWoJs5ZfiLtl1xoHR/usg19J29CM04U+6zikCMJV3BRUwPVmga7bZSuJqgJ8F9JnT9RepxPs9BBfCxenYaIIE41A2nnNijCehZWSgAQW1sLkGBBdieWQmUFpO56Rt0c6oSpKqs34+XY32oOHCFbZOzJEv0/Pr2OhHgThD/0uNJEQM5BbHTn+ZAgMBAAECggEAHRX2ujscqqJOokfKQJDU2NBTbtFDgsV8xH8DhWQaKRYVH1pvi4DHY1ah72JA8yaiKyglWiFyGC35Fk4pSEtVU5utjNtVLuH8aNy+PZUd8c+BwxCPNMRfUYXVUhH/J8B5OZjEA9Uisr/p/IIWujGPRkpYAiuGOGhJjCpsdJraVZmvVRr+xfz39hlt1/IowQSLLCZxg0NKrP6SgAuW/c1Bt3mIxx5+pn6DcpsF5rLwSzOdenH7y/fXHy8cw2ZyYQwspCsgNNXgWKZMeMaVhIIDUPZ37xmePbesbTGkpGGTmy8EczB7TFotCp8j9FtrkF8pVNDSx1t/jVq+AtwQPB0uAQKBgQDaSL+C7ZGQn4VeEg0rA/euX+WqMcgV6kp1yXJO1Tmuxm8uZ2QOMEgy7YP24kovyiAPkERxToscIOJ6tiHqdEFfZ+T89k0m+Ya/RWVQNkyIC18U0mVWtrEu1xX7C0J3FkxOuldjI06H4Em4gTHPSnYxwkcB0+Ju5n/ULnQvkCou2QKBgQC6j3wy7zMueSyZfqUyiAFyVyKRns5cdu7NqXIVVsemrsknd+wC37CBSBsxXQHFJL6hli2teoMdD8WzX4+IJrOZPR30Fi9pcAgcmEjY7Hqghc7K9DgD2q2kb0B5VCbKKybaevgnKUSkMTGWnYNcHbpEcHVretpLfQqGm7eN/0newQKBgAlRvctrNSHFjb4cay+A9dweAAIqKe61mAQ7W4P+Gn5ZvnlutJjeetahyVaLBrvPl1rSv/lObyX+SW0PxZbq67eVCfO62ERnttcHsXGhP9c3FRIggW8YUaw8RpX3PxkyfIOH9R8obLvkWYFlEqlU9oLdVoa/cxVlBeQbL56wOvzRAoGBALdH+cuMZir1DtTpr+AZ79nmMub/Llt22JsiKhs+uzxNIP/ud+ZG3n/UWCrB5dhJhiL3iH6TCraFZUQY0MkAxJb6jS/cj821K7L0BTVf9gU4D/Yhwdo/fzI5vgy0mLsrc6tW/e5zQ6VPUxlWO9RGvFU6apEiC8331VxzJPyS3n7BAoGBAKd/wew2fkRBJ+EPM8o61f36uf7LTy8w+zGeVld3pK+S7oGCuPA7HEz+CA8BxOZ72Ze8jT/judwG8N+8MGS6d8kjFVVTau/wgDgvO4nR+3MoTVx5DSQNLSDnNGOGFc2wJxnOb/UbQZWNZhgdDWt758OedWamm+Tdgr4QDFfcsTTP";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmO6zGC9D26jI24nAVSj2/uynDEWEPAXMEV6p5WG5c8ZdUu45nNPFNyOvRW/gfQlgB0GeLqAyAPzthMVBtJNYyUyeUaMCDNL2abi8PZDIP4PCnqHNW4G5Ee2PXSDH/6AcXGeakBgtRM5pMOvAKGqUtHSRhbVKL8xxVdqafYd3LTQTywzVX0iGtUaKq89Kld2XL2qOQ53WcwR6WOJyB5RMZD704+2kFUEfvNKPiy2aNQK7aQBJa2F7V5JEsnK2e73a2jVuBgpXEjJKDzAQ+YBtOGPn3Cs6sT/2IvMAWfiiqGug5VBCX9kBZJ7rUoM2ZFu/BtLO5do3OTr/gBQkRJ+XVQIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.xxx.com/alipay/notify_url.do";

    // 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    public static String CHARSET = "UTF-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGNTYPE = "RSA2";

}