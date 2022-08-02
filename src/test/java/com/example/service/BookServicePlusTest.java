package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于测试 IBookService（运用Mybatis plus快速构建方式） 的测试类
 * 说明：为了规范，本类与IBookService.java放在同一包下，即：com.example.service。
 * 经验证，本类放在与工程默认生成的测试类（SSMPApplicationTests)同级，也是可以正常运行的。
 */
@SpringBootTest
public class BookServicePlusTest {
    @Autowired
    private IBookService iBookService;

    @Test
    void testSelectOne(){
        System.out.println(iBookService.getById(1));
    }

    @Test
    void testSelectAll(){
        System.out.println(iBookService.list());
    }

    @Test
    void testSave(){
        Book book = new Book();

        book.setName("今日核酸2");
        book.setType("公共卫生2");
        book.setDescription("出行必备2");
        //ifSave=true代表新增成功
        boolean ifSave = iBookService.save(book);
        if(ifSave){
            System.out.println("新增数据成功！");
        }
    }

    @Test
    void testUpate(){
        Book book = new Book();
        book.setId(17);
        book.setName("今日核酸2update");
        book.setType("公共卫生2update");
        book.setDescription("公共卫生2update");
        boolean ifUpdate = iBookService.updateById(book);
        if(ifUpdate){
            System.out.println("更新数据成功！");
        }
    }

    @Test
    void testDelete(){
        boolean ifDelete = iBookService.removeById(18);
        if(ifDelete){
            System.out.println("删除数据成功！");
        }
    }

    @Test
    void testGetPage(){
        IPage<Book> bookIPage = new Page<Book>(1,5);
        iBookService.page(bookIPage);
        System.out.println(bookIPage.getCurrent()); //1
        System.out.println(bookIPage.getSize());  //5
        System.out.println(bookIPage.getTotal()); //16
        System.out.println(bookIPage.getPages()); //4
        System.out.println("The records size have: " + bookIPage.getRecords().size()); //5

    }

    /**
     * Query by condition.
     * ==>  Preparing: SELECT id,type,name,description FROM book WHERE type = ?
     * ==> Parameters: Java基础(String)
     */
    @Test
    void testGetByCondition(){
        String strParam = "Thinking in Java";
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("type", "Java基础");
        System.out.println(iBookService.listByMap(columnMap));
    }

    @Test
    void testGetByLambdaQuery(){
        LambdaQueryChainWrapper<Book> bookLambdaQueryChainWrapper = iBookService.lambdaQuery();
        System.out.println(bookLambdaQueryChainWrapper.list());
    }

    @Test
    void testGetMaxId(){
        System.out.println(iBookService.getMaxId());
    }
}
