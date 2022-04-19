package cn.edu.gxmzu.ai.mickhu.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-14 14:40
 */
public class ProxyUser implements Serializable {
    private static final long serialVersionUID = 3559533002594201718L;
    private String host;
    private String port;
    private CertUser certUser;
    private BigInteger[] pk;
    private BigInteger[] sk;
    private BigInteger[] receiverPK;

    public ProxyUser(String host, String port, CertUser certUser) {
        this.host = host;
        this.port = port;
        this.certUser = certUser;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public CertUser getCertUser() {
        return certUser;
    }

    public BigInteger[] getPk() {
        return pk;
    }

    public void setPk(BigInteger[] pk) {
        this.pk = pk;
    }

    public BigInteger[] getSk() {
        return sk;
    }

    public void setSk(BigInteger[] sk) {
        this.sk = sk;
    }

    public BigInteger[] getReceiverPK() {
        return receiverPK;
    }

    public void setReceiverPK(BigInteger[] receiverPK) {
        this.receiverPK = receiverPK;
    }
}
