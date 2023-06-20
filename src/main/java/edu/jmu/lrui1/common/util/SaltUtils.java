package edu.jmu.lrui1.common.util;

import java.util.Random;

/**
 * @author: lrui1
 * @description
 * @date: 2023/6/17
 */
public class SaltUtils  {
    /**
     * 默认随机salt长度为4
     */
    public static final Integer count = 4;

    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<n;i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

}
