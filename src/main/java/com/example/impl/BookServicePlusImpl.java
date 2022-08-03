package com.example.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.BookDao;
import com.example.domain.Book;
import com.example.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层实现类（市面常用实现方式，Mybatis plus快速构建方式）
 */
@Service
public class BookServicePlusImpl extends ServiceImpl<BookDao, Book> implements IBookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean modify(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public int getMaxId() {
        return bookDao.getMaxId();
    }

    @Override
    public IPage<Book> queryByPage(int currentPage, int pageSize) {
        IPage<Book> page = new Page<>(currentPage, pageSize);
        bookDao.selectPage(page, null);
        return page;
    }

    @Override
    public IPage<Book> queryByPage(int currentPage, int pageSize, Book queryBook) {
        IPage<Book> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(queryBook.getType()), Book::getType, queryBook.getType());
        lambdaQueryWrapper.like(Strings.isNotEmpty(queryBook.getName()), Book::getName, queryBook.getName());
        lambdaQueryWrapper.like(Strings.isNotEmpty(queryBook.getDescription()), Book::getDescription, queryBook.getDescription());
        return bookDao.selectPage(page, lambdaQueryWrapper);
    }
}
