package cn.edu.gxmzu.ai.mickhu.entity;

import cn.edu.gxmzu.ai.mickhu.crypto.RSA;

import java.math.BigInteger;

/**
 * @author mickhu
 * @describe 代理节点实体
 * @date 2022-04-09 14:00
 */
public class Proxy {

    private BigInteger[] pMap;

    private BigInteger[] qMap;

    private RSA Rsa;

    //实际节点生产的代理参数集
    public Proxy(BigInteger[] pMap, BigInteger[] qMap, String shuntParameter) {
        this.pMap = pMap;
        this.qMap = qMap;
        init(shuntParameter);
    }

    //模拟多个代理参数集
    public Proxy(String shuntParameter) {
        pMap = new BigInteger[]{
            new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519074969"),
            new BigInteger("8513222065247162701695105220665738877312063308356937563625345485856710133446374665834898192825484459951443770023314504441479244278247980992441766519075563"),
        };
        qMap = new BigInteger[]{
                new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328586993"),
                new BigInteger("8364581280641288933593527550533091363060086128207408134848028170130641974184553465641962883238792572920670310338579332490687347012348067644317739328587119"),

        };
        init(shuntParameter);
    }

    public void init(String shuntParameter) {
        BigInteger[] q_p = shuntProxy(shuntParameter);
        Rsa = new RSA(q_p[0], q_p[1]);
    }

    /**
     * 分流选取代理参数
     * @param shuntParameter 分流参数
     * @return 返回选中的代理参数
     */
    public BigInteger[] shuntProxy(String shuntParameter){
        //1. get min length at pMap and qMap
        int size = Math.min(pMap.length, qMap.length);
        //2. get index by shuntParameter choose
        int index = Integer.decode(shuntParameter) % size;
        //3. choose q at qMap and p at pMap
        BigInteger[] result = new BigInteger[]{qMap[index], pMap[index]};
        //4. return q and p
        return result;
    }

}
