package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.News;
import com.sdut.laboratorybackmodule.service.INewsService;
import com.sdut.laboratorybackmodule.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "*")
public class NewsController {

    private final INewsService newsService;

    @Autowired
    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("getNews/{id}")
    public News getNewsById(@PathVariable int id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/all")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping("/add")
    public void addNews(@RequestBody News news) {
        newsService.addNews(news);
    }

    @PutMapping("/update")
    public void updateNews(@RequestBody News news) {
        newsService.updateNews(news);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable int id) {
        newsService.deleteNews(id);
    }

    @GetMapping("/paged")
    public Map<String, Object> getPagedNews(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        List<News> newsList = newsService.getPagedNews(pageSize, pageNum);

        // 获取总记录数
        int totalNewsCount = newsService.getNewsCount();

        // 构建返回的响应数据
        Map<String, Object> result = new HashMap<>();
        result.put("data", newsList);
        result.put("total", totalNewsCount);
        System.out.println(result);
        return result;
    }
}
