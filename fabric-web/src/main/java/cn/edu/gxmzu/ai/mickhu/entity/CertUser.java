package cn.edu.gxmzu.ai.mickhu.entity;

import cn.edu.gxmzu.ai.mickhu.entity.test.GetDefault;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;

import java.io.Serializable;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * @description CertUser Entity
 * @author mickhu
 * @date 2022-04-07 17:48
 */
public class CertUser implements User, Serializable {
    private static final long serialVersionUID = 3559533002594201717L;
    private String Name;
    private Set<String> Roles;
    private String Account;
    private String Affiliation;
    private transient Enrollment Enrollment;
    private String MspId;

    public CertUser() {
    }

    public CertUser(String name, Set<String> roles, String account, String affiliation, Enrollment enrollment, String mspId) {
        Name = name;
        this.Roles = roles;
        Account = account;
        this.Affiliation = affiliation;
        this.Enrollment = enrollment;
        MspId = mspId;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public Set<String> getRoles() {
        return Roles;
    }

    @Override
    public String getAccount() {
        return Account;
    }

    @Override
    public String getAffiliation() {
        return Affiliation;
    }

    @Override
    public Enrollment getEnrollment() {
        return Enrollment;
    }

    @Override
    public String getMspId() {
        return MspId;
    }

    @Override
    public String toString() {
        return "CertUser{" +
                "Name='" + Name + '\'' +
                ", Roles='" + Roles +
                ", Account='" + Account + '\'' +
                ", Affiliation='" + Affiliation + '\'' +
                ", Enrollment='" + Enrollment + '\'' +
                ", MspId='" + MspId + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        CertUser certUser = GetDefault.getCertUserDefault("mickhu");
        System.out.println(certUser);
    }
}
