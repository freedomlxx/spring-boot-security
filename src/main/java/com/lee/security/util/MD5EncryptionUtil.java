package com.lee.security.util;


import java.util.UUID;

public class MD5EncryptionUtil {

    /**
     *  根据用户名随机生成盐值
     * @param username 用户名
     * @return
     */
    public static String getSalt(String username) {
        String salt = UUID.nameUUIDFromBytes(username.getBytes()).toString().replaceAll("-", "");
        return salt;
    }


    /*public static String encryptionPwd(String originalPassword, String salt, String username, String hashAlgorithm, int hashIteration) {
        SimpleHash newPassword = new SimpleHash(hashAlgorithm,originalPassword,username+salt,hashIteration);
        return newPassword.toString();
    }*/

    public static void main(String[] args) {
        String username = "zhangsan";
        String password = "123456";
        String hashAlgorithm = "md5";
        String salt = "78d92ba9477b3661bc8be4bd2e8dd8c0";
        int hashIteration = 2;

        //System.out.println(encryptionPwd(password, salt, username, hashAlgorithm, hashIteration));
    }
}
