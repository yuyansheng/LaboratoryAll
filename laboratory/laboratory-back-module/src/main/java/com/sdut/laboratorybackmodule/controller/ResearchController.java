package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Research;
import com.sdut.laboratorybackmodule.service.IResearchService;
import com.sdut.laboratorybackmodule.service.impl.ResearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/research")
@CrossOrigin(origins = "*")
public class ResearchController {
    @Autowired
    private IResearchService researchService;

    @PostMapping("/add")
    public void addResearch(@RequestBody Research research) {
        researchService.insertResearch(research);
    }

    @GetMapping("/{id}")
    public Research getResearchById(@PathVariable Long id) {
        return researchService.getResearchById(id);
    }

    @GetMapping("/all")
    public List<Research> getAllResearches() {
        return researchService.getAllResearches();
    }

    @PutMapping("/update")
    public void updateResearch(@RequestBody Research research) {
        researchService.updateResearch(research);
    }

    @DeleteMapping("/{id}")
    public void deleteResearch(@PathVariable Long id) {
        researchService.deleteResearch(id);
    }
}