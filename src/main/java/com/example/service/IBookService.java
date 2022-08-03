package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Book;

/**
 * 业务层接口类（市面常用方式，使用Mybatis plus快速构建）
 */
public interface IBookService extends IService<Book> {

    boolean modify(Book book);

    /**
     * 自定义方法：查询表中最大的id
     * @return int 最大ID值
     */
    int getMaxId();

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页显示页数
     * @return IPage<Book>
     */
    IPage<Book> queryByPage(int currentPage, int pageSize);

    /**
     * 带查询的分页
     * @param currentPage
     * @param pageSize
     * @param queryBook
     * @return
     */
    IPage<Book> queryByPage(int currentPage, int pageSize, Book queryBook);
}
