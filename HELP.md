# Getting Started

### Brief Introduction
这是一个基于SpringMVC+Springboot2+Mybatis plus的Web项目工程。
通过Vue.js+ElementUI+Axios，在静态网页（html+css+js）上实现数据可视化展示以及增删改分页操作。

### SQL Scripts
-- The project was default the database is db_ctdigitech.book definition
-- More details please read application.yml

CREATE TABLE `book` (
`id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
`type` varchar(20) DEFAULT NULL,
`name` varchar(50) DEFAULT NULL,
`description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)


