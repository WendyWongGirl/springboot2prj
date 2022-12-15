package com.example;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SSMPApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("init testing...");
    }

    @Test
    void testList(){
        List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

//        List<Lists<Integer>> lists=Lists.partition(numList,3);

        //打印结果：[[1, 2, 3], [4, 5, 6], [7, 8]]
        System.out.println(Lists.partition(numList, 3));
    }
}
