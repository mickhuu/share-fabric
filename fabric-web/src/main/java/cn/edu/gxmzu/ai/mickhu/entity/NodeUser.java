package cn.edu.gxmzu.ai.mickhu.entity;


import cn.edu.gxmzu.ai.mickhu.entity.test.Record;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点用户类
 */
public  class NodeUser {
    /**
     * 节点基本属性
     */
    //用户id
    private String id;

    //用户的上传数据
    private  Map<Integer, Record> dataMap;


    public NodeUser( String id, Map<Integer, Record> dataMap) {
        this.id = id;
        this.dataMap = dataMap;
    }

    public String getId() {
        return id;
    }

    public Map<Integer, Record> getDataMap() {
        return dataMap;
    }

    @Override
    public String toString() {
        return "NodeUser{"
                    + "id='" + id
                    + '\'' + ", dataMap=" + dataMap
                + '}';
    }

    public static void main( String[] args) {
        Map<Integer, Record> dataMap = new HashMap<>();
        //数据标签
        String[] tags = new String[]{"person", "medical"};
        //获取当前时间戳
        String timeStamp = String.valueOf(System.currentTimeMillis());
        dataMap.put(1, new Record("this is my data", tags, timeStamp));
        NodeUser nodeUser = new NodeUser("111", dataMap);
        System.out.println(nodeUser);
    }
}
