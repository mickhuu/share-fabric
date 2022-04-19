package cn.edu.gxmzu.ai.mickhu.crypto;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

/**
 * @author Divyansh
 */
public class RSA {

    /**
     * 加密
     * @param plaintext 明文
     */
    public static BigInteger encrypt(BigInteger plaintext, BigInteger e, BigInteger n) {
        // plainText^d mod n
        return plaintext.modPow(e, n);
    }

    /**
     * 解密
     * @param ciphertext 密文
     */
    public static BigInteger decrypt(BigInteger ciphertext, BigInteger d, BigInteger n) {
        return ciphertext.modPow(d, n);
    }
}