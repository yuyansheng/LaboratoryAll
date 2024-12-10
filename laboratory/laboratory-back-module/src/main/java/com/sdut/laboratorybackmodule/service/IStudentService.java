package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Student;

import java.util.List;

public interface IStudentService{

    /**
     * 查询全部 Student 数据
     * @return List<Student>
     */
    List<Student> listStudent();

    /**
     * 根据 id 查询一条 Student 数据
     * @param id
     * @return Student
     */
    Student selectStudentById(Integer id);

    /**
     * 新增一条 Student 数据
     * @return
     */
    int insertStudent(Student student);

    /**
     * 根据 id 删除一条 Student 数据
     * @return
     */
    int deleteStudentById(Integer id);

    /**
     * 更新一条 Student 数据
     * @return
     */
    int updateStudent(Student student);

}
