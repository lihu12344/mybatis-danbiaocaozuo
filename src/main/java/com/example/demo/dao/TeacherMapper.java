package com.example.demo.dao;

import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.TeacherCount;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TeacherMapper extends Mapper<Teacher> {

    List<TeacherCount> getCount();

    List<Teacher> get();
}
