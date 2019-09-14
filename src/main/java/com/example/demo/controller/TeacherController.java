package com.example.demo.controller;

import com.example.demo.dao.TeacherMapper;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.TeacherCount;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping("/save")
    public String save(){
        for(int i=0;i<10;i++){
            Teacher teacher=new Teacher();

            teacher.setName("瓜田李下"+i);
            teacher.setAge(i);

            teacherMapper.insert(teacher);
        }

        return "success";
    }

    @RequestMapping("/get")
    public List<Teacher> get(){
        PageHelper.startPage(1,2);
        return teacherMapper.selectAll();
    }

    @RequestMapping("/get2")
    public List<Teacher> get2(){
        RowBounds rowBounds=new RowBounds(1,4);
        List<Teacher> list=teacherMapper.selectByRowBounds(null,rowBounds);

        System.out.println(rowBounds.getOffset()+"  "+rowBounds.getLimit());

        return list;
    }

    @RequestMapping("/get3")
    public List<Teacher> get3(){
        PageRowBounds rowBounds=new PageRowBounds(1,3);
        //rowBounds.setCount(true);
        List<Teacher> list= teacherMapper.selectByExampleAndRowBounds(null,rowBounds);

        System.out.println("总页数为："+rowBounds.getCount()+"\n总条数为："+rowBounds.getTotal()+
                "\n页码： "+rowBounds.getOffset()+"\n本页数："+rowBounds.getLimit());

        return list;
    }

    @RequestMapping("/get4")
    public List<Teacher> get4(){
        Example example=new Example(Teacher.class);
        example.orderBy("name").asc();

        return teacherMapper.selectByExample(example);
    }

    @RequestMapping("/get5")
    public List<Teacher> get5(){
        Example example=new Example(Teacher.class);
        example.selectProperties("id","name");

        return teacherMapper.selectByExample(example);
    }

    @RequestMapping("/get6")
    public List<Teacher> get6(){
        Example example=new Example(Teacher.class);
        example.setForUpdate(true);
        example.setDistinct(true);
        example.createCriteria().andLike("name","瓜田李下%");

        return teacherMapper.selectByExample(example);
    }

    @RequestMapping("/get8")
    public List<Teacher> get8(String name,Integer age){
        Example example=new Example(Teacher.class);
        Example.Criteria c1=example.createCriteria();

        if(name!=null){
            c1.andLike("name",name+"%");
        }

        if(age!=null) {
            c1.orEqualTo("age",age);
        }

        return teacherMapper.selectByExample(example);
    }

    @RequestMapping("/count")
    public List<TeacherCount> count(){
        return teacherMapper.getCount();
    }

    @RequestMapping("/get9")
    public List<Teacher> get9(){
        return teacherMapper.get();
    }
}
