/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.gxmzu.ai.mickhu.entity;

import lombok.Data;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Base64;

/**
 * @author singh
 */
@Data
public class ShareMessage {

    /**
     * 消息
     */
    private String message;

    /**
     * 秘钥(大数)
     */
    private BigInteger key;

    /**
     * 由发送方生成的中间秘钥
     */
    private BigInteger midKey;


    public ShareMessage(String message, SecretKey key) {
        this.message = message;
        // 秘钥转换
        this.key = new BigInteger(Base64.getEncoder().encodeToString(key.getEncoded()).getBytes());
    }

    public ShareMessage(String message, BigInteger key) {
        this.message = message;
        this.key = key;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public BigInteger getMidKey() {
        return midKey;
    }

    public void setMidKey(BigInteger midKey) {
        this.midKey = midKey;
    }

    /**
     * 将秘钥转换为大数
     */
    public void setKey(SecretKey key) {
        this.key = new BigInteger(Base64.getEncoder().encodeToString(key.getEncoded()).getBytes());
    }

    public void setKey(BigInteger key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public SecretKey getKey() {
        String keyString = new String(key.toByteArray());
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        // Constructs a secret key from the given byte array 
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return secretKey;
    }

    public BigInteger getBigIntKey() {
        return key;
    }
}
