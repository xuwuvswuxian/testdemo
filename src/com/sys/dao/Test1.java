package com.sys.dao;

import com.sys.daoutil.DBUtil;
import com.sys.entity.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        User user = new User();
        user.setName("王小二");
        user.setAge(22);
        user.setSex("0");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        user.setCreateTime(time);
        addUser(user);

        List<User> list = findAllUser(30,"武");
        list.forEach(System.out::println);
    }

    private static List<User> findAllUser(Integer age2,String name2) {
        List<User> list = new ArrayList<>();
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql ="select * from sys_user";
//            String sql ="select * from sys_user where age > ? and name like ?";
            //把driver转化为字节码，意思是开启数据库与IDEA的一个驱动
//            Class.forName(DRIVER);
            //把连接的路径，以及用户名和密码对比
//            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            conn = DBUtil.startConn();
           //根据IDEA的字符串转化，获取为数据库的语句（这里是查询语句）
            ps = conn.prepareStatement(sql);
//            ps.setObject(1,age2);
//            ps.setObject(2,name2+"%");
            //返回一个sql的查询语句的结果集 相当于 select * from sys_user 把打印的结果保存到ResultSet里面
            rs = ps.executeQuery();
            while(rs.next()){  //判断是否还有下一个结果的内容，有就返回true没有就是false
                user = new User();
                Integer id = rs.getInt("id");  //根据结果集找到数据库列表字段为id的数据，保存到IDEA的id
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime = rs.getString("create_time");
                String delFlag = rs.getString("del_flag");
                user.setId(Long.valueOf(id));  //因为数据库的bigint类型就等于我们的long长整型，所以要转换
                user.setName(name);            //根据数据库name字段所获取到的内容set给用户的姓名
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(delFlag);
                list.add(user);                //以上字段全部添加后，就添加进用户集合里面
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //循环完毕，关闭资源，先开后关，后关先开，并且要做非空判断
            DBUtil.closeConn2(conn,ps,rs);
        }
        //最后把用户的结果返回出去
        return list;
    }

    private static void addUser(User user){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "insert into sys_user (name,age,sex,create_time) values(?,?,?,?)";
            conn = DBUtil.startConn();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,user.getName());
            ps.setObject(2,user.getAge());
            ps.setObject(3,user.getSex());
            ps.setObject(4,user.getCreateTime());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
