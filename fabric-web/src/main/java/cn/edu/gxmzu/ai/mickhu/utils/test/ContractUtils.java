package cn.edu.gxmzu.ai.mickhu.utils.test;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;

import java.util.concurrent.TimeoutException;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-08 9:55
 */
public class ContractUtils {
    private Contract contract;

    public ContractUtils(Contract contract) {
        this.contract = contract;
    }

    public byte[] evaluateTransaction(String func, String... agrs){
        byte[] result = null;
        try {
            result = contract.evaluateTransaction(func, agrs[0]);
            System.out.println(func+":"+new String(result));
        }catch (ContractException e){
            System.out.println("chiancode execution error");
            e.printStackTrace();
        }
        return result;
    }
    public byte[] submitTransaction(String func, String... agrs){
        byte[] result = null;
        try {
            result = contract.evaluateTransaction(func, agrs[0]);
            System.out.println(func+":"+new String(result));
        }catch (ContractException e){
            System.out.println("chiancode execution error");
            e.printStackTrace();
        }
        return result;
    }
}
