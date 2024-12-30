package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Software;
import com.sdut.laboratorybackmodule.service.ISoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/software")
@CrossOrigin(origins = "*")
public class SoftwareController {

    @Autowired
    private ISoftwareService softwareService;

    @PostMapping("/add")
    public void addSoftware(@RequestBody Software software) {
        softwareService.addSoftware(software);
    }

    @GetMapping("getById/{id}")
    public Software getSoftwareById(@PathVariable Long id) {
        return softwareService.getSoftwareById(id);
    }

    @GetMapping("/all")
    public List<Software> getAllSoftware() {
        return softwareService.getAllSoftware();
    }

    @PutMapping("/update")
    public void updateSoftware(@RequestBody Software software) {
        softwareService.updateSoftware(software);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSoftware(@PathVariable Long id) {
        softwareService.deleteSoftware(id);
    }
}
