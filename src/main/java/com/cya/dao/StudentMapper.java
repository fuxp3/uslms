package com.cya.dao;

import com.cya.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {

    int addStu(Student student);

    List<Student> findListByLike(String keyword);

    int deleteById(Integer id);

    int updateStu(Student student);

    Student findStudentById(Integer id);
}
