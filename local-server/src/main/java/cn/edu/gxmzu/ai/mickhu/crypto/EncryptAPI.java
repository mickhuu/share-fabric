package cn.edu.gxmzu.ai.mickhu.crypto;


import cn.edu.gxmzu.ai.mickhu.entity.ShareMessage;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptAPI {

    public EncryptAPI() {
    }

    private static final String pwd = "seedKey";

    /**
     * 利用AES加密消息
     */
    public ShareMessage encryptMessageByAes(String seedKey, String message) {
        if(seedKey == null || "".equals(seedKey)) seedKey = pwd;
        // AES Symmetric Key Generation and Encryption of Plaintext Message
        KeyGenerator keyGen = null;
        SecureRandom random = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        random.setSeed(seedKey.getBytes());
        keyGen.init(128, random);
        // 产生AES秘钥(随机生成???)
        SecretKey aesKey = keyGen.generateKey();
        //System.out.println("AES秘钥:" + aesKey.toString());
        // 使用AES加密明文
        message = AES.encrypt(message, aesKey);
        // 返回消息实体(记录这个AES的秘钥)
        return new ShareMessage(message, aesKey);
    }

    /**
     * 加密AES秘钥
     *
     * @param shareMessage 消息体
     */
    public ShareMessage encryptKeyByRsa(ShareMessage shareMessage, BigInteger pk, BigInteger n) {
        // 对消息体内的AES对应的大数进行加密
        BigInteger key = RSA.encrypt(shareMessage.getBigIntKey(), pk, n);
        // 给原始消息体设置用RSA加密后的AES秘钥
        shareMessage.setKey(key);
        // 返回消息体
        return shareMessage;
    }

    /**
     * RSA解密AES秘钥
     */
    public ShareMessage decryptKey(ShareMessage shareMessage, BigInteger sk, BigInteger n) {
        BigInteger key = RSA.decrypt(shareMessage.getBigIntKey(), sk, n);
        // 重新设置AES秘钥
        shareMessage.setKey(key);
        return shareMessage;
    }

    /**
     * AES解密
     * @param shareMessage
     * @return
     */
    public String decryptMessageByAes(ShareMessage shareMessage) {
        SecretKey aesKey = shareMessage.getKey();
        //System.out.println("AES秘钥:" + aesKey.toString());
        // 使用AES解密
        return AES.decrypt(shareMessage.getMessage(), aesKey);
    }
}
