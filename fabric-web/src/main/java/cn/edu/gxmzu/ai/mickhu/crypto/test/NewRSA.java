package cn.edu.gxmzu.ai.mickhu.crypto.test;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

public class NewRSA {
    private static final Integer SIZE = 1024;
    public BigInteger p, q;
    public BigInteger n;
    public BigInteger euler;
    public BigInteger e, d;
    public BigInteger ed;

    public NewRSA(String shuntParameter) {
        initialize(shuntParameter);
    }

    /**
     * 将公钥与私钥转换为大数
     */
    public NewRSA(PrivateKey privateKey, PublicKey publicKey, String shuntParameter) {
        initialize(shuntParameter);
    }

    /**
     * 初始化生成e与d
     * e: 公钥Public Key
     * d: 私钥Private Key
     */
    public void initialize(String shuntParameter) {
        int size = 2;
        BigInteger[] pMap = new BigInteger[]{
                new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519074969"),
                new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519075563"),

        };
        BigInteger[] qMap = new BigInteger[]{
                new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328586993"),
                new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328587119"),

        };
        // p and q are 2154 digit Prime Numbers which are used in the generation of RSA Keys
        // p与q是用来产生RSA秘钥的大质数
//        p = new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519074969");
//        q = new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328586993");
        int index = Integer.decode(shuntParameter) % size;//(int) (Math.random() * SIZE);
        p = pMap[index];
        q = qMap[index];
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
