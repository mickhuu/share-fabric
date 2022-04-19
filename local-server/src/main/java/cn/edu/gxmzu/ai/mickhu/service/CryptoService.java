package cn.edu.gxmzu.ai.mickhu.service;

import cn.edu.gxmzu.ai.mickhu.entity.ShareMessage;

import java.math.BigInteger;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-13 15:59
 */
public interface CryptoService {

    /**
     * ʹ��AES����
     * @param plainText ����
     * @param myKey AES��Կ�������seedKey
     * @return cipher ����
     */
    ShareMessage encodeByAES(String plainText, String myKey);

    /**
     * ʹ��AES����
     * @param cipher AES����
     * @param myKey AES��Կ�������seedKey
     * @return plainText ����
     */
    String decodeByAES(String cipher, String myKey);

    /**
     * ʹ��AES����
//     * @param cipher AES����
//     * @param myKey AES��Կ�������seedKey
     * @param shareMessage ������Ϣʵ����
     * @return plainText ����
     */
    String decodeByAES(ShareMessage shareMessage);

    /**
     * ʹ��RSA��Կ����
//     * @param plainText ����
//     * @param pk ��Կ
     * @param shareMessage ������Ϣʵ����
     * @param pk ��Կ
     * @param n ��������ȷ����Ψһ�������
     * @return ���� cipher
     */
    ShareMessage encodeByRSA(ShareMessage shareMessage, BigInteger pk, BigInteger n);

    /**
     * ʹ��RSA˽Կ����
//     * @param cipher RSA����
     * @param shareMessage ������Ϣʵ����
     * @param sk ˽Կ
     * @param n ��������ȷ����Ψһ�������
     * @return ���� plainText
     */
    ShareMessage decodeByRSA(ShareMessage shareMessage, BigInteger sk, BigInteger n);

    /**
     * �ؼ���
     * @param shareMessage ������Ϣʵ��
     * @param senderSK ������˽Կ��Ȩ
     * @param receiverPK �����߹�Կ����
     * @param n ��������ȷ����Ψһ�������
     * @return
     */
    ShareMessage reEncode(ShareMessage shareMessage, BigInteger senderSK, BigInteger receiverPK, BigInteger n);

    /**
     * �ؽ���
     * @param shareMessage
     * @param sk
     * @param n
     * @return
     */
    String reDecode(ShareMessage shareMessage, BigInteger sk, BigInteger n);
}
