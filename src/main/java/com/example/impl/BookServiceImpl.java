package com.example.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dao.BookDao;
import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层实现类（一般实现方式）
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book queryById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> queryAll() {
        return bookDao.selectList(null);
    }

    @Override
    public Boolean save(Book book) {
        //excute result = 1, insert success; otherwise failed.
        return bookDao.insert(book) > 0;
    }

    @Override
    public Boolean updateById(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public IPage<Book> queryPage(int currentPage, int pageSize) {
        IPage<Book> bookIPage = new Page<Book>(currentPage, pageSize);
        return bookDao.selectPage(bookIPage, null);
    }

    @Override
    public List<Book> queryByCondition(String queryParam) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
        queryWrapper.eq(StringUtils.isNotBlank(queryParam), "name", "Thinking in Java");
        return bookDao.selectList(queryWrapper);
    }

    @Override
    public List<Book> queryByLambdaQuery(String queryParam) {
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<Book>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(queryParam), Book::getName, queryParam);
        return bookDao.selectList(lambdaQueryWrapper);
    }
}
