package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.util.file.FIleType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FIleUploadController {
    static String baseURL = "http://localhost:9090/";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("fileType") String fileTypeString) {
        // 获取文件名
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FIleType fileType = FIleType.valueOf(fileTypeString);
        // 将文件保存到资源文件夹
        try {
            Path uploadPath = Paths.get("resources/"+fileType.getPath());
            System.out.println("file = " + file + ", fileTypeString = " + fileTypeString);

            if (!Files.exists(uploadPath)) {
                System.out.println("uploadPath = " + uploadPath);
                Files.createDirectories(uploadPath);
            }


            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 生成图片的URL（这里简单处理，实际情况可能需要拼接服务器地址）
            String fileUrl = baseURL + "resources/" +fileType.getPath() + fileName;

            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
}
