package cn.edu.gxmzu.ai.mickhu.entity;

import lombok.Data;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-10 11:10
 */
@Data
public class DataURL {
    public String DataID;

    public String Cipher;

    public DataURL(String dataID, String Cipher) {
        DataID = dataID;
        this.Cipher = Cipher;
    }

    public String getDataID() {
        return DataID;
    }

    public String getCipher() {
        return Cipher;
    }
}
