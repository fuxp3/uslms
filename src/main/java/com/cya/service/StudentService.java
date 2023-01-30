package com.cya.service;

import com.cya.dao.StudentMapper;
import com.cya.entity.Student;
import com.cya.util.ro.PageIn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Student addStu(Student student){
        studentMapper.addStu(student);
        return student;
    }

    public PageInfo<Student> getStudentList(PageIn pageIn) {
        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());
        List<Student> listByLike = studentMapper.findListByLike(pageIn.getKeyword());
        return new PageInfo<>(listByLike);
    }
}
