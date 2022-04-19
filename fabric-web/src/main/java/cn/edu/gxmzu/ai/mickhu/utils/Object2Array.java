package cn.edu.gxmzu.ai.mickhu.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import cn.edu.gxmzu.ai.mickhu.entity.CertUser;
import cn.edu.gxmzu.ai.mickhu.entity.Data;
import cn.edu.gxmzu.ai.mickhu.entity.Institution;
import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @describe JAVA对象&Byte数组之间转化
 * @author mickhu
 * @date 2022-04-11 13:40
 */
public class Object2Array {
    private static final Logger LOGGER = LoggerFactory.getLogger(Object2Array.class);

    public static void main(String[] args) throws Exception {
        Person person = new Person("Jone", 25, "good person");
//        byte[] objectToByteArray = objectToByteArray(data);
//        System.err.println("objectToByteArray: " + new String(objectToByteArray));
//        Object byteArrayToObject = byteArrayToObject(objectToByteArray);
//        System.err.println("byteArrayToObject: " + byteArrayToObject.toString());

        String d = "this is my data";
        CertUser produce = GetDefault.getCertUserDefault("mickhu");
        CertUser process = GetDefault.getCertUserDefault("mickhu");
        String DigitSignatureByProduce = CodeConvertUtils.convertBytesToHex(CryptoUtils.Sign(d, produce.getEnrollment().getKey()));
        String DigitSignatureByProcess = CodeConvertUtils.convertBytesToHex(CryptoUtils.Sign(d, process.getEnrollment().getKey()));
        Institution institution = new Institution(produce.getName(), process.getName(), produce, process, DigitSignatureByProduce, DigitSignatureByProcess);
//        Institution institution = new Institution(produce.getAccount(),process.getName());
        Data data = new Data("test", institution, "http://127.0.0.1:8080/aaaa", String.valueOf(System.currentTimeMillis()));
//        Data data = new Data("test", "http://127.0.0.1:8080/aaaa", String.valueOf(System.currentTimeMillis()));
        String test = JSON.toJSONString(data);
//        Record record = new Record("this is my data", new String[]{"ttt","111"}, "123456789");
//        Object record1 = JSON.parseObject(test, Record.class);

        System.out.println("fastjson json:"+test);

//        data = (Data)JSON.parse(byteArrayToObject.toString());
        Data objetc = JSON.parseObject(test, Data.class);
        System.out.println("fastjson object:"+objetc);
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
            LOGGER.error("objectToByteArray failed, " + e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close objectOutputStream failed, " + e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close byteArrayOutputStream failed, " + e);
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
            LOGGER.error("byteArrayToObject failed, " + e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close byteArrayInputStream failed, " + e);
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close objectInputStream failed, " + e);
                }
            }
        }
        return obj;
    }

    static class Person implements Serializable {
        private static final long serialVersionUID = 3559533002594201715L;
        private String name;
        private Integer age;
        private String desc;

        public Person(String name, int age, String desc) {
            this.name = name;
            this.age = age;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", desc=" + desc + "]";
        }

    }

}