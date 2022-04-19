package cn.edu.gxmzu.ai.mickhu.entity;

import java.io.Serializable;

/**
 * @describe Data Entity
 * @author mickhu
 * @date 2022-04-08 17:00
 */
public class Data implements Serializable {
    private static final long serialVersionUID = 3559533002594201715L;
    private String DataID;
    private Institution institution;
    private String UsageURL;
    private String TimeStamp;

    public Data(String dataID, Institution institution, String usageURL, String timeStamp) {
        DataID = dataID;
        this.institution = institution;
        UsageURL = usageURL;
        TimeStamp = timeStamp;
    }

    public String getDataID() {
        return DataID;
    }

    public Institution getInstitution() {
        return institution;
    }

    public String getUsageURL() {
        return UsageURL;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    @Override
    public String toString() {
        return "Data{" +
                "DataID='" + DataID + '\'' +
                ", institution=" + institution +
                ", UsageURL='" + UsageURL + '\'' +
                ", TimeStamp='" + TimeStamp + '\'' +
                '}';
    }

}
