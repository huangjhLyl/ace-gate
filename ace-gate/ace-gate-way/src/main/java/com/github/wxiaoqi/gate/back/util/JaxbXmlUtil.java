package com.github.wxiaoqi.gate.back.util;



/**
 * Jaxb工具类 xml和java类相互转换
 *
 *@author huangjh
 *@date 2019/4/19 9:07
 */
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbXmlUtil {

    public static final String DEFAULT_ENCODING = "GBK";

    /**
     * pojo转换成xml 默认编码UTF-8
     *
     * @param obj 待转化的对象
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj) throws Exception {
        return convertToXml(obj, DEFAULT_ENCODING);
    }

    /**
     * pojo转换成xml
     *
     * @param obj 待转化的对象
     * @param encoding 编码
     * @return xml格式字符串
     * @throws Exception JAXBException
     */
    public static String convertToXml(Object obj, String encoding) throws Exception {
        String result = null;
        StringWriter writer = new StringWriter();
        writer.append("<?xml version=\"1.0\" encoding=\""+encoding+"\"?>"+"\n");
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);


        marshaller.marshal(obj, writer);
        result = writer.toString();


        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml xml格式字符串
     * @param t 待转化的对象
     * @return 转化后的对象
     * @throws Exception JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertToJavaBean(String xml, Class<T> t) throws Exception {
        T obj = null;
        JAXBContext context = JAXBContext.newInstance(t);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obj = (T) unmarshaller.unmarshal(new StringReader(xml));
        return obj;
    }

    public static void main(String[] args) {
        try {
//模拟请求报文
//            UserReq userReq = new UserReq();
//            userReq.head.setChanCode("1231");
//            userReq.head.setTrsTime("2");
//            userReq.head.setTrsCode("T0004");
//            userReq.head.setTrsDate("20190419");
//            userReq.head.setTrsSerial("2");
//            userReq.body.setXingming("141600");
//            userReq.body.setZjhm("2");
//            userReq.body.setZjlx("2");
            UserRsp userRsp = new UserRsp();
            userRsp.head.setChanCode("1231");
            userRsp.head.setTrsTime("2");
            userRsp.head.setTrsCode("T0004");
            userRsp.head.setTrsDate("20190419");
            userRsp.head.setTrsSerial("2");
            userRsp.head.setResCode("00");
            userRsp.head.setResMsg("成功");
            userRsp.head.setSpSerial("2");

            userRsp.body.setXxjyjg("检查成功");
            Dkxx dkxx = new Dkxx();
            dkxx.setDkffje("2");
            dkxx.setDkpz("2");
            dkxx.setDkzh("2");
            dkxx.setHtbh("2");
            dkxx.setJjxh("2");

            List list = new ArrayList<>();
            list.add(dkxx);

            dkxx = new Dkxx();
            dkxx.setDkffje("3");
            dkxx.setDkpz("3");
            dkxx.setDkzh("3");
            dkxx.setHtbh("3");
            dkxx.setJjxh("3");
            list.add(dkxx);

            userRsp.body.dkxx.addAll(list);

            String s = JaxbXmlUtil.convertToXml(userRsp);
            UserRsp userRsp1 = JaxbXmlUtil.convertToJavaBean(s, userRsp.getClass());
            System.out.println(s);
            System.out.println(userRsp1.head.toString());
            System.out.println(userRsp1.body.xxjyjg);
            List<Dkxx> dkxx1 = userRsp1.body.dkxx;
            for (Dkxx dkxx2 : dkxx1) {
                System.out.println(dkxx2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
