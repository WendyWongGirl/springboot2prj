package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.domain.Book;

import java.util.List;

/**
 * 业务层接口类（一般实现方式）
 */
public interface BookService {

    Book queryById(Integer id);

    List<Book> queryAll();

    Boolean save(Book book);

    Boolean updateById(Book book);

    Boolean deleteById(Integer id);

    IPage<Book> queryPage(int currentPage, int pageSize);

    List<Book> queryByCondition(String queryParam);

    List<Book> queryByLambdaQuery(String queryParam);
}
