# springboot2prj

这是一个基于SpringMVC+Springboot2+Mybatis plus的Web项目工程。（非前后端分离）

### Brief Introduction
这是一个基于SpringMVC+Springboot2+Mybatis plus的Web项目工程。 
集成了Swagger2，项目启动后，浏览器访问http://localhost/doc.html（或者http://localhost/swagger-ui.html），查看相关接口API文档说明，以及模拟请求发送与响应情况。 
通过Vue.js+ElementUI+Axios，在静态网页（html+css+js）上实现数据可视化展示以及增删改分页操作。

### SwaggerUI2访问

http://localhost:8081/swagger-ui.html

### SQL Scripts
-- The project was default the database is db_ctdigitech.book definition
-- More details please read application.yml

CREATE TABLE `book` (
`id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
`type` varchar(20) DEFAULT NULL,
`name` varchar(50) DEFAULT NULL,
`description` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

