package com.cya.controller;

import com.cya.entity.Student;
import com.cya.service.StudentService;
import com.cya.util.R;
import com.cya.util.http.CodeEnum;
import com.cya.util.ro.PageIn;
import com.cya.util.vo.PageOut;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/list")
    public R getUsers(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        // 封装分页出参对象
        PageInfo<Student> studentList = studentService.getStudentList(pageIn);
        PageOut pageOut = new PageOut();
        pageOut.setCurrPage(studentList.getPageNum());
        pageOut.setPageSize(studentList.getPageSize());
        pageOut.setTotal((int) studentList.getTotal());

        pageOut.setList(studentList.getList());

        return R.success(CodeEnum.SUCCESS,pageOut);
    }

    @PostMapping("/addStu")
    public R addReader(@RequestBody Student student) {
        if (student == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        return R.success(CodeEnum.SUCCESS,studentService.addStu(student));
    }
}
