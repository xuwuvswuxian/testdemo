package com.sys.jdbctemplate;

import com.sys.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcTemplateUtil.getDataSource());

    public static void main(String[] args) {

        new UserDao().listUser().forEach(x -> System.out.println(x));

    }

    public List<User> listUser() {
        String sql = "select * from sys_user";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
