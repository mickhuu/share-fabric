package cn.edu.gxmzu.ai.mickhu.service;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 13:28
 */
public interface DataService {
    /**
     * 加密数据
     * @param data
     * @return 密文
     */
    String encode(String data);


    /**
     * 解密数文
     * @param cipher
     * @return
     */
    String decode(String cipher);
}
