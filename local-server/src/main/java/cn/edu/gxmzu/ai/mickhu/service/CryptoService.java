package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.ShareMessage;

import java.math.BigInteger;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-13 15:59
 */
public interface CryptoService {

    /**
     * 使用AES加密
     * @param plainText 明文
     * @param myKey AES密钥随机种子seedKey
     * @return cipher 密文
     */
    ShareMessage encodeByAES(String plainText, String myKey);

    /**
     * 使用AES解密
     * @param cipher AES密文
     * @param myKey AES密钥随机种子seedKey
     * @return plainText 明文
     */
    String decodeByAES(String cipher, String myKey);

    /**
     * 使用AES解密
//     * @param cipher AES密文
//     * @param myKey AES密钥随机种子seedKey
     * @param shareMessage 共享消息实体类
     * @return plainText 明文
     */
    String decodeByAES(ShareMessage shareMessage);

    /**
     * 使用RSA公钥加密
//     * @param plainText 明文
//     * @param pk 公钥
     * @param shareMessage 共享消息实体类
     * @param pk 公钥
     * @param n 分流参数确定的唯一共享代餐
     * @return 密文 cipher
     */
    ShareMessage encodeByRSA(ShareMessage shareMessage, BigInteger pk, BigInteger n);

    /**
     * 使用RSA私钥解密
//     * @param cipher RSA密文
     * @param shareMessage 共享消息实体类
     * @param sk 私钥
     * @param n 分流参数确定的唯一共享代餐
     * @return 明文 plainText
     */
    ShareMessage decodeByRSA(ShareMessage shareMessage, BigInteger sk, BigInteger n);

    /**
     * 重加密
     * @param shareMessage 共享消息实体
     * @param senderSK 发送者私钥授权
     * @param receiverPK 接收者公钥加密
     * @param n 分流参数确定的唯一共享代餐
     * @return
     */
    ShareMessage reEncode(ShareMessage shareMessage, BigInteger senderSK, BigInteger receiverPK, BigInteger n);

    /**
     * 重解密
     * @param shareMessage
     * @param sk
     * @param n
     * @return
     */
    String reDecode(ShareMessage shareMessage, BigInteger sk, BigInteger n);
}
