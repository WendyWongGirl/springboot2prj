package com.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prime {
    public static void main(String[] args) {
//        System.out.println(isPrime(430348));
        System.out.println(isPrime2(4));
//        System.out.println(isPrime3(4));
    }

    // 暴力枚举
    public static boolean isPrime(int n) {
        // 防止溢出
        for(int i = 2; i <= n / i; i++)
            if(n % i == 0)
                return false;
        return true;
    }

    // 埃氏筛法
    public static boolean isPrime2(long x){
        int n= new Long(x).intValue();
        int [] arr = new int[n+1];
        // 1:prime   0:not prime
        Arrays.fill(arr,1);

        for(int i = 2; i <= n; i++){
            if(arr[i] == 1){
                // remove the count by i
                for(int j = i+i; j <= n; j += i){
                    arr[j] = 0;
                }
            }
        }
        if(arr[n] == 1){
            System.out.println("______" +n);
        }else{
            int minNum = 0;
            for (int i=0; i<arr.length-1; i++){
                if(arr[i+1]<arr[i]){
                    minNum = arr[i+1];
                }else{
                    arr[i+1] = arr[i];
                    minNum = arr[i];
                }
            }
            System.out.println(minNum);
        }
        return arr[n] == 1 ? true : false;
    }


    // 线性筛法
    public static boolean isPrime3(int n){
        // 质数集合
        List<Integer> primes = new ArrayList<>();
        int [] arr = new int[n+1];
        // 1:质数   0:非质数
        Arrays.fill(arr,1);

        for(int i = 2; i <= n; i++){
            if(arr[i] == 1)
                primes.add(i); // 添加集合中
            // 筛选，
            for(int j = 0; j < primes.size() && primes.get(j) <= n / i; j++){
                // 标记
                arr[i*primes.get(j)] = 0;
                // 保证每个合数只会被它的最小质因数筛去，减少冗余
                if(i % primes.get(j) == 0)
                    break;
            }
        }
        return arr[n] == 1 ? true : false;
    }
}
