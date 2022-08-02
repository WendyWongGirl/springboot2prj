package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用于测试 BookService（一般实现方式） 的测试类
 * 说明：为了规范，本类与BookService.java放在同一包下，即：com.example.service。
 * 经验证，本类放在与工程默认生成的测试类（SSMPApplicationTests)同级，也是可以正常运行的。
 */
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void testSelectOne(){
        System.out.println(bookService.queryById(1));
    }

    @Test
    void testSelectAll(){
        System.out.println(bookService.queryAll());
    }

    @Test
    void testSave(){
        Book book = new Book();

        book.setName("今日核酸");
        book.setType("公共卫生");
        book.setDescription("出行必备");
        //ifSave=true代表新增成功
        boolean ifSave = bookService.save(book);
        if(ifSave){
            System.out.println("新增数据成功！");
        }
    }

    @Test
    void testUpate(){
        Book book = new Book();
        book.setId(17);
        book.setName("今日核酸update");
        book.setType("公共卫生update");
        book.setDescription("公共卫生update");
        boolean ifUpdate = bookService.updateById(book);
        if(ifUpdate){
            System.out.println("更新数据成功！");
        }
    }

    @Test
    void testDelete(){
        boolean ifDelete = bookService.deleteById(18);
        if(ifDelete){
            System.out.println("删除数据成功！");
        }
    }

    @Test
    void testGetPage(){
        IPage<Book> bookIPage = bookService.queryPage(1, 5);
        System.out.println(bookIPage.getCurrent()); //1
        System.out.println(bookIPage.getSize());  //5
        System.out.println(bookIPage.getTotal()); //16
        System.out.println(bookIPage.getPages()); //4
        System.out.println("The records size have: " + bookIPage.getRecords().size()); //5

    }

    @Test
    void testGetByCondition(){
        String strParam = "Thinking in Java";
        System.out.println(bookService.queryByCondition(strParam));
    }

    @Test
    void testGetByLambdaQuery(){
        String name = "测试";
        System.out.println(bookService.queryByLambdaQuery(name));
    }
}
