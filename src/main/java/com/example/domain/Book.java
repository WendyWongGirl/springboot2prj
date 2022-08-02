package com.example.domain;

import lombok.Data;

/**
 * 主体类（用于数据传递）
 */
@Data
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
}
