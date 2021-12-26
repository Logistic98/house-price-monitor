package com.house.utils;

import cn.hutool.core.date.DateUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class HouseUtils {

    /**
     * 获取当前时间字符串（形如20210726164215）--24小时制
     * @return
     */
    public static String getCurrentTimeString(){
        String currentTime = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
        return currentTime;
    }

    /**
     * Map<String,String>转换为byte[]数组
     * @param map
     * @return
     * @throws IOException
     */
    public static byte[] mapToBytes(Map<String,String> map) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(map);
        bytes = outputStream.toByteArray();
        return bytes;
    }

    /**
     * 将byte[]数组转换为Map<String,String>
     * @param bytes
     * @return
     * @throws IOException
     */
    public static Map<String,String> bytesToMap(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteInputStream byteInputStream = new ByteInputStream(bytes, bytes.length);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Map<String,String> map = (Map<String, String>) objectInputStream.readObject();
        return map;
    }

}
