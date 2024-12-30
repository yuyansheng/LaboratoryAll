package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Publication;
import com.sdut.laboratorybackmodule.service.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publication")
@CrossOrigin(origins = "*")
public class PublicationController {

    @Autowired
    IPublicationService publicationService;

    // 插入新出版物
    @PostMapping("/add")
    public void createPublication(@RequestBody Publication publication) {
        publicationService.insertPublication(publication);
    }

    // 获取所有出版物
    @GetMapping("/all")
    public List<Publication> getAllPublications() {
        return publicationService.getAllPublications();
    }

    // 根据ID获取出版物
    @GetMapping("/getById/{id}")
    public Publication getPublicationById(@PathVariable Long id) {
        return publicationService.getPublicationById(id);
    }

    // 更新出版物
    @PutMapping("/update/{id}")
    public void updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        publication.setId(id); // Set the ID to ensure the update is for the correct record
        publicationService.updatePublication(publication);
    }

    // 删除出版物
    @DeleteMapping("/delete/{id}")
    public void deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
    }
}