package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/save")
    public String save(){
        User user=new User();
        user.setId(1);
        user.setName("吴姝悦");
        user.setAge(24);

        int i=userMapper.updateByPrimaryKeySelective(user);
        System.out.println(i);

        return "success";
    }

    @RequestMapping("/get")
    public List<User> get(){
        PageHelper.startPage(0,10);
        return userMapper.selectAll();
    }

    @RequestMapping("/get2")
    public List<User> get2(){
        Example example=new Example(User.class);
        example.setForUpdate(true);
        Example.Criteria criteria=example.createCriteria().andLike("name","瓜田李下%");
        return userMapper.selectByExample(example);
    }

    @RequestMapping("/get3")
    public List<User> get3(){
        Example example=new Example(User.class);
        example.selectProperties("id","name");
        example.setDistinct(true);
        PageHelper.offsetPage(0,10);
        return userMapper.selectByExample(example);
    }

    @RequestMapping("/get4")
    public List<User> get4(){
        Example example=new Example(User.class);
        example.createCriteria().andLike("name","上官飞燕%");
        example.orderBy("id").asc();
        return userMapper.selectByExample(example);
    }

    @RequestMapping("/get5")
    public List<User> get5(){
        return null;
    }
}
