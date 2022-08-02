package com.example.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用于测试 BookDao 的测试类
 * 说明：为了规范，本类与BookDao.java放在同一包下，即：com.example.dao。
 * 经验证，本类放在与工程默认生成的测试类（SSMPApplicationTests)同级，也是可以正常运行的。
 */
@SpringBootTest
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;

    @Test
    void testSelectOne(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSelectAll(){
        System.out.println(bookDao.selectList(null));
    }

    @Test
    void testSave(){
        Book book = new Book();
//        //在保持mysql自增列的情况下，注释掉mybatis plus自增列配置，手动指定要新增数据的id值
//        int minId = bookDao.getMaxId();
//        book.setId(minId-1);

        book.setName("密码学");
        book.setType("数据安全");
        book.setDescription("编程人员需要知道的加密知识");
        //ifSave=1代表新增成功
        int ifSave = bookDao.insert(book);
        if(ifSave > 0 ){
            System.out.println("新增数据成功！");
        }
    }

    @Test
    void testUpate(){
        Book book = new Book();
        book.setId(1);
        book.setName("测试测试");
        book.setType("测试测试");
        book.setDescription("测试测试");
        int ifUpdate = bookDao.updateById(book);
        if(ifUpdate > 0 ){
            System.out.println("更新数据成功！");
        }
    }

    @Test
    void testDelete(){
        int ifDelete = bookDao.deleteById(3);
        if(ifDelete > 0 ){
            System.out.println("删除数据成功！");
        }
    }

    @Test
    void testGetPage(){
        IPage<Book> bookIPage = new Page<>(1,5);
        IPage<Book> selectedPage = bookDao.selectPage(bookIPage, null);
        System.out.println(selectedPage.getCurrent()); //1
        System.out.println(selectedPage.getSize());  //5
        System.out.println(selectedPage.getTotal()); //16
        System.out.println(selectedPage.getPages()); //4
        System.out.println("The records size have: " + selectedPage.getRecords().size()); //5

    }

    @Test
    void testGetByCondition(){
        /**
         * Query the records of name=‘Thinking in Java’ ：
         * ==>  Preparing: SELECT id,type,name,description FROM book WHERE (name = ?)
         * ==> Parameters: Thinking in Java(String)
         */
        String strParam = "Thinking in Java";
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
        queryWrapper.eq(StringUtils.isNotBlank(strParam), "name", "Thinking in Java");
        bookDao.selectList(queryWrapper);
    }

    @Test
    void testGetByLambdaQuery(){
        /**
         * Query the records of name be like ‘测试’ ：
         * ==>  Preparing: SELECT id,type,name,description FROM book WHERE (name LIKE ?)
         * ==> Parameters: %测试%(String)
         *
         * If the parameter is null, there will not take the condition without query parameter.
         * Just excute the sql: SELECT id,type,name,description FROM book
         */
//        String name = null;
        String name = "测试";
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<Book>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name), Book::getName, name);
        bookDao.selectList(lambdaQueryWrapper);
    }
}
