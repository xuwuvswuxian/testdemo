package com.sys.jdbctemplate;

import com.sys.entity.PageBean;
import com.sys.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcTemplateUtil.getDataSource());

    public static void main(String[] args) {

//        User user = new User();
//        user.setName("你好");
//        user.setAge(101);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        user.setCreateTime(sdf.format(new Date()));
//        user.setSex("1");
//        UserDao userDao = new UserDao();
//        userDao.addUser(user);
//        new UserDao().updateUserById("李四",14);
        new UserDao().deleteUserById(15);
        new UserDao().listUser().forEach(x -> System.out.println(x));


//        User user = new UserDao().selectById(1);
//        System.out.println(user);
//
//         new UserDao().listUser(new PageBean(2)).forEach( x-> System.out.println(x));
    }
    //查询所有字段
    private List<User> listUser() {
        String sql = "select * from sys_user";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //分页查询
    private List<User> listUser(PageBean page){
        String sql = "select * from sys_user where 1=1 limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(User.class),
                (page.getPage()-1)*page.getPageSize(),page.getPageSize());
    }

    //根据指定id来查询用户
    private User selectById(Integer id){
        String sql = "select * from sys_user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    //添加用户
    private void addUser(User user){
        String sql = "insert into sys_user (name,age,create_time,sex) values (?,?,?,?)";
        template.update(sql, user.getName(),user.getAge(),user.getCreateTime(),user.getSex());
    }

    //根据id来修改用户的姓名
    private void updateUserById(String name,Integer id){
        String sql="update sys_user set name = ? where id = ?";
        template.update(sql,name,id);
    }

    //根据id来删除用户的数据
    private void deleteUserById(Integer id){
        String sql="delete from sys_user where id = ?";
        template.update(sql,id);
    }

    //add update delete DML语言都是 update()
    //select 根据返回的结果分为单查询和多查询 DDL语言还需要后面传递反射的实体类对象才能有效
    //单返回结果的查询就用queryForObject()，多返回的结果用query()即可
}
