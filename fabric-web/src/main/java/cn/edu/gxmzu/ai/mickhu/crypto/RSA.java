package cn.edu.gxmzu.ai.mickhu.crypto;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

/**
 * @author Divyansh
 */
public class RSA {

    private static final Integer SIZE = 1024;
    public BigInteger n;
    private BigInteger euler;
    public BigInteger e, d;
    private BigInteger ed;

    public RSA(BigInteger p, BigInteger q) {
        initialize(p, q);
    }

    /**
     * 将公钥与私钥转换为大数
     */
    public RSA(PrivateKey privateKey, PublicKey publicKey, BigInteger p, BigInteger q) {
        initialize(p, q);
    }

    /**
     * 初始化生成e与d
     * e: 公钥Public Key
     * d: 私钥Private Key
     */
    public void initialize(BigInteger p, BigInteger q) {
        // p and q are 2154 digit Prime Numbers which are used in the generation of RSA Keys
        // p与q是用来产生RSA秘钥的大质数
        // n = p * q
        n = p.multiply(q);
        // 计算欧拉函数
        euler = p.subtract(BigInteger.valueOf(1));
        euler = euler.multiply(q.subtract(BigInteger.valueOf(1)));
        do {
            e = new BigInteger(SIZE, new Random());
        } while ((e.compareTo(euler) != 1) || (e.gcd(euler).compareTo(BigInteger.valueOf(1)) != 0));
        d = e.modInverse(euler);
        ed = e.multiply(d);
        // 生成的都是随机的e和d
         System.out.println("e>evler:"+e.compareTo(euler));
         System.out.println("公钥e:" + e);
         System.out.println("私钥d:" + d);
    }

    /**
     * 加密
     * @param plaintext 明文
     */
    public BigInteger encrypt(BigInteger plaintext) {
        // plainText^d mod n
        return plaintext.modPow(e, n);
    }

    /**
     * 解密
     * @param ciphertext 密文
     */
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }
}