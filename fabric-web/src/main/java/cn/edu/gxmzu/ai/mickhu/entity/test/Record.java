package cn.edu.gxmzu.ai.mickhu.entity.test;

import java.util.Arrays;


/**
 * 数据报告单模板
 */
public class Record {
    //数据
    private final String data;

    //数据标签
    private final String[] tags;

    //时间戳
    private final String timeStamp;

    //报告单构造器
    public Record(String data, String[] tags, String timeStamp) {
        this.data = data;
        this.tags = tags;
        this.timeStamp = timeStamp;
    }

    public String getData() {
        return data;
    }

    public String[] getTags() {
        return tags;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Record{"
                + "data='" + data + '\''
                + ", tags=" + Arrays.toString(tags)
                + ", timeStamp='" + timeStamp + '\''
                + '}';
    }


}
