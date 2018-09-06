package org.gt.common;

import java.util.ResourceBundle;

/**
 * ��Ŀ����������
 * 
 */
public class ResourceUtil {

    //private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");
    /**
     * ��ȡ�����ĳ���
     *
     * @return �����ĳ���
     */
    public static String getRandCodeLength() {
        return "4";//bundle.getString("randCodeLength");
    }

    /**
     * ��ȡ����������
     *
     * @return ����������
     */
    public static String getRandCodeType() {
        return "5";//bundle.getString("randCodeType");
    }

}