package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Student;
import com.sdut.laboratorybackmodule.mapper.StudentMapper;
import com.sdut.laboratorybackmodule.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 查询全部 Student 数据
     * @return List<Student>
     */
    @Override
    public List<Student> listStudent() {
        return studentMapper.listStudent();
    }

    /**
     * 根据 id 查询一条 Student 数据
     * @param id
     * @return Student
     */
    @Override
    public Student selectStudentById(Integer id) {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 新增一条 Student 数据
     * @param student
     * @return
     */
    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    /**
     * 根据 id 删除一条 Student 数据
     * @param id
     * @return
     */
    @Override
    public int deleteStudentById(Integer id) {
        return studentMapper.deleteStudentById(id);
    }

    /**
     * 更新一条 Student 数据
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }
}
