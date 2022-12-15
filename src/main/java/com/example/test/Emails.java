package com.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Emails {
    public static void main(String[] args) {

        String email = "julia@gmail.com";
        boolean ifvaild = false;
        if(!email.matches("\\d*") || !email.matches("\\_")){
            String tegex = "[a-zA-Z0-9_]+@\\w+(\\.com|\\.cn){1}";
            boolean flag = email.matches(tegex);
            if (flag) {
                ifvaild = true;
                System.out.println("邮箱格式正确");
            } else {
                ifvaild = false;
                System.out.println("邮箱格式有误");
            }
        }else {
            ifvaild = false;
            System.out.println("不符合位数规定");
        }

//        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
//        Pattern pattern = Pattern.compile(reg);
//        boolean flag = false;
//        if (email != null) {
//            Matcher matcher = pattern.matcher(email);
//            flag = matcher.matches();
//        }
//        System.out.println(flag);

        System.out.println(lowerFirstCapse("Wendy"));
    }

    /**
     * 转换首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
