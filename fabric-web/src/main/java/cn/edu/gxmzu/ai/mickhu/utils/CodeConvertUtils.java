package cn.edu.gxmzu.ai.mickhu.utils;

import java.io.*;
import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-11 11:29
 */
public class CodeConvertUtils {

    //日期转换时间戳
    public static String Date2TimeStamp(String date){
        long res = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).
                parse(date, new ParsePosition(0)).getTime() / 1000;
        return String.valueOf(res);
    }

    //字节数组转16进制
    public static String convertBytesToHex(byte[] bytes) {

        BigInteger bigInteger = new BigInteger(1, bytes);

        String convertedResult = String.format("%x", bigInteger);

//        System.out.println("Converted Hex from String: " + convertedResult);
        return convertedResult;
    }

    //字符串转16进制
    public static String convertStringToHex(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            String charToHex = Integer.toHexString(c);
            stringBuilder.append(charToHex);
        }
//        System.out.println("Converted Hex from String: "+stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * 对象转Byte数组
     * @param obj
     * @return
     */
    public static byte[] objectToByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.println("objectToByteArray failed, " + e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    log.println("close objectOutputStream failed, " + e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    log.println("close byteArrayOutputStream failed, " + e);
                }
            }

        }
        return bytes;
    }

    /**
     * Byte数组转对象
     * @param bytes
     * @return
     */
    public static Object byteArrayToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        } catch (Exception e) {
            log.println("byteArrayToObject failed, " + e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    log.println("close byteArrayInputStream failed, " + e);
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    log.println("close objectInputStream failed, " + e);
                }
            }
        }
        return obj;
    }
}
