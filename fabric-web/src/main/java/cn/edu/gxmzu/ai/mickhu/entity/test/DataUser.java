package cn.edu.gxmzu.ai.mickhu.entity.test;

import java.util.Map;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-16 21:02
 */
public class DataUser {
    private String id;

    private Map<Integer, Record> dataMap;

    public String getId() {
        return id;
    }

    public Map<Integer, Record> getDataMap() {
        return dataMap;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDataMap(Map<Integer, Record> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public String toString() {
        return "DataUser{" +
                "id='" + id + '\'' +
                ", dataMap=" + dataMap +
                '}';
    }
}
