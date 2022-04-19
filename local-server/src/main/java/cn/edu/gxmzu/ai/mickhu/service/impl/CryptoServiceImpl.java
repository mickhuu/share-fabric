package cn.edu.gxmzu.ai.mickhu.service.impl;

import cn.edu.gxmzu.ai.mickhu.crypto.AES;
import cn.edu.gxmzu.ai.mickhu.crypto.EncryptAPI;
import cn.edu.gxmzu.ai.mickhu.crypto.RSA;
import cn.edu.gxmzu.ai.mickhu.entity.ShareMessage;
import cn.edu.gxmzu.ai.mickhu.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Base64;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-13 15:59
 */
@Service
public class CryptoServiceImpl implements CryptoService {

    static EncryptAPI encryptAPI = new EncryptAPI();

//    private static BigInteger n;

    @Override
    public ShareMessage encodeByAES(String plainText, String myKey) {
        return encryptAPI.encryptMessageByAes(myKey, plainText);
    }

    @Override
    public String decodeByAES(String cipher, String myKey) {
        byte[] decodedKey = Base64.getDecoder().decode(myKey);
        // Constructs a secret key from the given byte array
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return AES.decrypt(cipher, secretKey);
    }

    @Override
    public String decodeByAES(ShareMessage shareMessage) {
        return encryptAPI.decryptMessageByAes(shareMessage);
    }

    @Override
    public ShareMessage encodeByRSA(ShareMessage shareMessage, BigInteger pk, BigInteger n) {
        return encryptAPI.encryptKeyByRsa(shareMessage, pk, n);
    }

    @Override
    public ShareMessage decodeByRSA(ShareMessage shareMessage, BigInteger sk, BigInteger n) {
        return encryptAPI.decryptKey(shareMessage, sk, n);
    }

    @Override
    public ShareMessage reEncode(ShareMessage shareMessage, BigInteger senderSK, BigInteger receiverPK, BigInteger n) {
        //设置转换重加密中间密钥
        BigInteger midKey = receiverPK.multiply(senderSK);
        shareMessage.setKey(midKey);
        // 获取Sender提供的会话秘钥
        BigInteger key = shareMessage.getBigIntKey();
        // 重新生成秘钥
        key = RSA.encrypt(key, midKey, n);
        // 设置代理重加密转换后的秘钥
        shareMessage.setKey(key);
        return shareMessage;
    }

    @Override
    public String reDecode(ShareMessage shareMessage, BigInteger sk, BigInteger n) {
        shareMessage = encryptAPI.decryptKey(shareMessage, sk, n);
        // 返回明文
        return encryptAPI.decryptMessageByAes(shareMessage);
    }


}
