package cn.edu.gxmzu.ai.mickhu.entity;

import java.io.Serializable;

/**
 * @describe Institution Entity
 * @author mickhu
 * @date 2022-04-08 17:30
 */
public class Institution implements Serializable {
    private static final long serialVersionUID = 3559533002594201716L;
    private String ProduceID;
    private String ProcessID;
    private CertUser ProduceNode;
    private CertUser ProcessNode;
    private String DigitSignatureByProduce;
    private String DigitSignatureByProcess;

    public Institution(String produceID, String processID, CertUser produceNode, CertUser processNode, String digitSignatureByProduce, String digitSignatureByProcess) {
        ProduceID = produceID;
        ProcessID = processID;
        ProduceNode = produceNode;
        ProcessNode = processNode;
        DigitSignatureByProduce = digitSignatureByProduce;
        DigitSignatureByProcess = digitSignatureByProcess;
    }

    public String getProduceID() {
        return ProduceID;
    }

    public String getProcessID() {
        return ProcessID;
    }

    public CertUser getProduceNode() {
        return ProduceNode;
    }

    public CertUser getProcessNode() {
        return ProcessNode;
    }

    public String getDigitSignatureByProduce() {
        return DigitSignatureByProduce;
    }

    public String getDigitSignatureByProcess() {
        return DigitSignatureByProcess;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "ProduceID='" + ProduceID + '\'' +
                ", ProcessID='" + ProcessID + '\'' +
                ", ProduceNode=" + ProduceNode.toString() +
                ", ProcessNode=" + ProcessNode.toString() +
                ", DigitSignatureByProduce='" + DigitSignatureByProduce + '\'' +
                ", DigitSignatureByProcess='" + DigitSignatureByProcess + '\'' +
                '}';
    }
}
