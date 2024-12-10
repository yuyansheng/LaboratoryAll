package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

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