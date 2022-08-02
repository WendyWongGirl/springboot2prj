package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.utils.R;
import com.example.domain.Book;
import com.example.service.IBookService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表现层类
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        //自定义异常：书名=测试测试时，抛出异常
        if ("测试测试".equals(book.getName())) {
            throw new IOException();
        }
        boolean flag = bookService.save(book);
        return new R(flag, book, flag ? "添加成功" : "添加数据失败，请重试！");
    }

    @PutMapping
    public R udpate(@RequestBody Book book) {
        boolean flag = bookService.modify(book);
        return new R(flag, book, flag ? "修改成功" : "修改数据失败，请重试！");
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable Integer id) {
        boolean flag = bookService.removeById(id);
        return new R(flag, flag ? "删除主键{"+id+"}成功" : "删除主键{" + id + "}数据失败，请重试！");
    }

    @GetMapping("{id}")
    public R queryById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        if (book != null) {
            return new R(true, "查询主键{" + id + "}数据成功！");
        } else {
            return new R(false, "查询主键{" + id + "}数据失败，请重试！");
        }
    }

    @GetMapping
    public R queryList() {
        List<Book> bookList = bookService.list();

        if (bookList != null || bookList.size() > 0) {
//            return new R(true, bookList.stream().map(Book::getName).collect(Collectors.toList()),"全查询数据成功！");
            return new R(true, bookList.stream().collect(Collectors.toMap(Book::getId, a-> a, (k1,k2)->k1)),"全查询数据成功！");
            /*
                将list转成json格式 （pom.xml已引入GSON相关依赖）
            String dataStr = new Gson().toJson(bookList);
            return new R(true, dataStr,"全查询数据成功！");
             */
        } else {
            return new R(false, "全查询数据失败，请重试！");
        }
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R queryByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, Book book) {
        IPage<Book> bookIPage = bookService.queryByPage(currentPage, pageSize, book);
        if (bookIPage != null) {
            return new R(null != bookIPage, bookIPage, "分页查询成功！");
        } else {
            return new R(null != bookIPage, "分页查询失败！");
        }
    }
}
