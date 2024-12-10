package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Student;
import com.sdut.laboratorybackmodule.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    IStudentService iStudentService;

    /**
     * 获取全部学生数据接口
     * @return List<Student>
     */
    @GetMapping(value = "/info",produces = {"application/json;charset=UTF-8"})
    public List<Student> listStudentInfo(){
        return iStudentService.listStudent();
    }

    /**
     * 根据 id 查询一条 Student 数据接口
     * @param id
     * @return Student
     */
    @GetMapping(value = "/info/{id}",produces = {"application/json;charset=UTF-8"})
    public Student studentInfoById(@PathVariable(value = "id")Integer id){
        return iStudentService.selectStudentById(id);
    }

    /**
     * 新增一条 Student 数据
     * @param student
     */
    @PostMapping(value = "/add",produces = {"application/json;charset=UTF-8"})
    public void addStudent(@RequestBody Student student){
        iStudentService.insertStudent(student);
    }

    /**
     * 删除一条 Student 数据
     * @param id
     */
    @PostMapping(value = "/remove/{id}",produces = {"application/json;charset=UTF-8"})
    public void removeStudent(@PathVariable("id") Integer id){
        iStudentService.deleteStudentById(id);
    }

    /**
     * 修改一条 Student 数据
     * @param student
     */
    @PostMapping(value = "/edit",produces = {"application/json;charset=UTF-8"})
    public void editStudent(@RequestBody Student student){
        iStudentService.updateStudent(student);
    }
}