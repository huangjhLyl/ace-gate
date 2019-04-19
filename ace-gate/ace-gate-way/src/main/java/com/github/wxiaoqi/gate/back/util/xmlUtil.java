package com.github.wxiaoqi.gate.back.util;

import com.github.wxiaoqi.gate.back.entity.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.TimeZone;

/**
 * @author huangjh
 * @date 2019/4/19 8:49
 */
public class xmlUtil {
    //解决双下划线
    public static final XmlFriendlyNameCoder nameCoder = new XmlFriendlyNameCoder("-_", "_");
    // 编码格式
    private static final String ENCODING = "UTF-8";
    // dom解析驱动
    private static final DomDriver fixDriver = new DomDriver(ENCODING, nameCoder);
    // 通用解析器
    public static final XStream XSTREAM = new XStream(fixDriver);
    //时区
    private static final String CHINA_TIME_ZONE = "Asia/Shanghai";

    static {
        // 时区处理
        TimeZone zone = TimeZone.getTimeZone(CHINA_TIME_ZONE); //获得时区
        XSTREAM.registerConverter(new DateConverter(zone), XStream.PRIORITY_NORMAL);
        XSTREAM.autodetectAnnotations(true); //开启序列化的注解形式
        XSTREAM.setMode(XStream.NO_REFERENCES);//取消引用,如果没有这一步,会出现xml引用格式reference

    }

    /**
     * 报文转对象
     * @param xml
     * @return
     */
    public static Object fromXML(String xml) {
        Object target = null;
        XStream xstream = XSTREAM;
//        xstream.processAnnotations(AllCase.class);//开启此类的解析,否则无法解析
        try {
            target = xstream.fromXML(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 对象转报文
     * @param obj
     * @return
     */

    public static String toXML(Object obj) {
        XStream xstream = XSTREAM;
        xstream.autodetectAnnotations(true);
        xstream.alias("xml", obj.getClass());//设置别名
        String xml ="<?xml version=\"1.0\" encoding=\"GBK\"?>"+ xstream.toXML(obj);
        return xml;
    }

    /**
     * 文件流转对象
     * @param in
     * @return
     */
    public static Object fromXML(InputStream in) {
        Object target = null;
        XStream xstream = XSTREAM;
//        xstream.processAnnotations(AllCase.class);
        try {
            target =  xstream.fromXML(in);
            System.out.println("输入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 对象转文件流
     * @param obj
     * @param out
     */
    public static void toXML(Object obj, OutputStream out) {
        XStream xstream = XSTREAM;
        xstream.toXML(obj,out);
        System.out.println("输出成功");
    }

    public static void main(String[] args) {
        User user = new User();
        user.setAddress("123");
        String s = xmlUtil.toXML(user);
        System.out.println(s);
    }
}
