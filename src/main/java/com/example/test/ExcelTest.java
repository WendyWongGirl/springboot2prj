package com.example.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.*;

public class ExcelTest {

    public static void main(String[] args){
        Date date = new Date();


        List<Map<Integer, String>> dataList = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "111");
        map.put(2, "20221008001");
        map.put(3, "20221008001");
        map.put(4, "成功了");
        map.put(5, "wendy");
        map.put(6, date.toString());
        map.put(7, "wendy222");
        map.put(8, date.toString());
        map.put(9, "entry");

        dataList.add(map);

        Map<String, Object> resultMap = import2DBFromExcel10w(dataList);
//        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
//            String s = "key====>" + entry.getKey() + ",value===>" + entry.getValue();
//            System.out.println(s);
//        }

//        map.forEach((key, value) -> System.out.println("key====>" + key + ",value===>" + value));

        map.forEach((key, value) -> System.out.println(key + "," +value));
    }

    public static Map<String, Object> import2DBFromExcel10w(List<Map<Integer, String>> dataList) {
        HashMap<String, Object> result = new HashMap<>();
        //结果集中数据为0时,结束方法.进行下一次调用
        if (dataList.size() == 0) {
            result.put("empty", "0000");
            return result;
        }
        //JDBC分批插入+事务操作完成对10w数据的插入
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long startTime = System.currentTimeMillis();
            System.out.println(dataList.size() + "条,开始导入到数据库时间:" + startTime + "ms");
            conn = JDBCDruidUtils.getConnection();
            //控制事务:默认不提交
            conn.setAutoCommit(false);
            String sql = "insert into ACT_RESULT_LOG (onlineseqid,businessid,becifno,ivisresult,createdby,createddate,updateby,updateddate,risklevel) values";
            sql += "(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //循环结果集:这里循环不支持"烂布袋"表达式
            for (int i = 0; i < dataList.size(); i++) {
                Map<Integer, String> item = dataList.get(i);
                ps.setString(1, item.get(0));
                ps.setString(2, item.get(1));
                ps.setString(3, item.get(2));
                ps.setString(4, item.get(3));
                ps.setString(5, item.get(4));
                ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                ps.setString(7, item.get(6));
                ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                ps.setString(9, item.get(8));
                //将一组参数添加到此 PreparedStatement 对象的批处理命令中。
                ps.addBatch();
            }
            //执行批处理
            ps.executeBatch();
            //手动提交事务
            conn.commit();
            long endTime = System.currentTimeMillis();
            System.out.println(dataList.size() + "条,结束导入到数据库时间:" + endTime + "ms");
            System.out.println(dataList.size() + "条,导入用时:" + (endTime - startTime) + "ms");
            result.put("success", "1111");
        } catch (Exception e) {
            result.put("exception", "0000");
            e.printStackTrace();
        } finally {
            //关连接
            JDBCDruidUtils.close(ps, conn);
        }
        return result;
    }
}
