package cn.edu.gxmzu.ai.mickhu.utils;

import cn.edu.gxmzu.ai.mickhu.config.FabricConfig;
import org.apache.commons.compress.utils.IOUtils;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.fail;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-12 18:32
 */
public class CryptoUtils {
    private static CryptoPrimitives crypto;
    private static final String SIGNING_ALGORITHM = "SHA256withECDSA";

    static {
        init();
    }

    private static void init() {
        try {
            crypto = new CryptoPrimitives();
            crypto.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }
    /**
     * 签名
     * @param message 明文消息
     * @param key 私钥
     * @return 签名摘要
     */
    public static byte[] Sign(String message, PrivateKey key) {
        byte[] plainText = message.getBytes(UTF_8);
        byte[] signature = new byte[0];
        try {
            //PrivateKey key = (PrivateKey) crypto.getTrustStore().getKey("key", "123456".toCharArray());
            signature = crypto.sign(key, plainText);

//        } catch (KeyStoreException | CryptoException | UnrecoverableKeyException
//                | NoSuchAlgorithmException e) {
        } catch (CryptoException e) {
            fail("Could not verify signature. Error: " + e.getMessage());
        }
        return signature;
    }

    /**
     * 校验签名
     * @param plainText 明文消息
     * @param certName 证书名
     * @param signature 签名摘要
     * @return True/False
     */
    public static boolean Verify(byte[] plainText, String certName, byte[] signature) {
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    CryptoUtils.class.getResourceAsStream(FabricConfig.WALLET_PATH + "\\" + certName));
            byte[] cert = IOUtils.toByteArray(bis);
            bis.close();
            return crypto.verify(cert, SIGNING_ALGORITHM, signature, plainText);
        } catch (CryptoException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static X509Certificate readX509Certificate(final Path certificatePath) {
        try (Reader certificateReader = Files.newBufferedReader(certificatePath, UTF_8)) {
            return Identities.readX509Certificate(certificateReader);
        } catch (IOException | CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PrivateKey getPrivateKey(final Path privateKeyPath) {
        try (Reader privateKeyReader = Files.newBufferedReader(privateKeyPath, UTF_8)) {
            return Identities.readPrivateKey(privateKeyReader);
        } catch (IOException | InvalidKeyException e){
            e.printStackTrace();
        }
        return null;
    }
}
