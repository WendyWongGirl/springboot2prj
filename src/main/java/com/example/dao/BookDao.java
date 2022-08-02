package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 数据访问层
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {

    @Select("select max(id) from book")
    int getMaxId();
}
