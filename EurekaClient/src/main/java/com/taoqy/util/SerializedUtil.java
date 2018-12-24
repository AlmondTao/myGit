//package com.taoqy.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.util.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
//import java.nio.charset.Charset;
//
///**
// * The util for Serialized
// * @author taoqy
// */
//public class SerializedUtil<T> implements RedisSerializer<T> {
//
//    private static final Charset DEFAULT_CHARTSET = IOUtils.UTF8;
//    private Class<T> clazz;
//
//
//    public SerializedUtil(Class<T> clazz){
//        super();
//        this.clazz = clazz;
//    }
//
//
//    public static String parseJsonString(Object obj){
//        if (obj == null){
//            return new String();
//        }
//        return JSONObject.toJSONString(obj);
//    }
//
//    public static Object parseObject(String jsonString,Class t){
//        if(StringUtils.isEmpty(jsonString) || t == null){
//            return new Object();
//        }
//
//        return JSONObject.parseObject(jsonString, t);
//
//    }
//
//    @Override
//    public byte[] serialize(Object obj)throws SerializationException{
//        if (obj == null){
//            return new byte[0];
//        }
//
//        return parseJsonString(obj).getBytes(DEFAULT_CHARTSET);
//
//
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if(bytes == null || bytes.length <= 0){
//            return null;
//        }
//        String str = new String(bytes, DEFAULT_CHARTSET);
//
//        return (T)parseObject(str, clazz);
//    }
//}
