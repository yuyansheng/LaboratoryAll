package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.util.file.FileType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FIleController {
    static String baseURL = "http://localhost:9090/";

    static String uploadDir = "uploads/";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("fileType") String fileTypeString,
                                   @RequestParam(value = "allowedRepetition" ,defaultValue = "false") boolean allowedRepetition
    ) {
        // 获取文件名
        // 默认文件上传成功，并返回状态信
        // 获取项目的相对路径（存储路径）
        FileType fileType = FileType.valueOf(fileTypeString);

        String dirPath = uploadDir + fileType.getPath(); // 存储文件的相对路径

        // 获取当前工作目录的绝对路径（基于项目根目录）
        Path uploadDirr = Paths.get(System.getProperty("user.dir"), dirPath);

        // 如果文件夹不存在，则创建
        File dir = uploadDirr.toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 获取文件名以及后缀名
        String fileName = file.getOriginalFilename();
        // 重新生成文件名（根据具体情况生成对应文件名）
        if(!allowedRepetition){
            fileName = UUID.randomUUID() + "_" + fileName;
        }

        // 创建文件保存的完整路径
        File destFile = new File(uploadDirr.toFile(), fileName);

        try {
            // 将文件保存到指定路径
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
                // 上传失败，返回失败信息
            return "File upload failed: " + e.getMessage();  //
        }

        // 携带上传状态信息回调到文件上传页面
        return fileName;
    }

    // 小文件加载
    @GetMapping("/load")
    public ResponseEntity<byte[]> loadFile(@RequestParam("fileName") String fileName,
                                           @RequestParam("fileType") String fileTypeString) {
        // 文件存储目录
        FileType filetype = FileType.valueOf(fileTypeString);
        String fileDir = uploadDir + filetype.getPath();
        File dir = new File(fileDir);
        if (!dir.exists()) {
            return ResponseEntity.status(404).body(null); // 文件未找到
        }
        // 读取文件内容
        try {
            File file = new File(fileDir, fileName);
            if (!file.exists()) {
                return ResponseEntity.status(404).body(null); // 文件未找到
            }

            // 自动检测文件的 MIME 类型
            String contentType = Files.probeContentType(file.toPath()); // 根据文件内容检测 MIME 类型

            // 如果 probeContentType 返回 null（无法自动识别），可以设置一个默认值
            if (contentType == null) {
                contentType = "application/octet-stream"; // 通用的二进制流 MIME 类型
            }


            byte[] fileContent = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType)) // 自动设置 Content-Type
                    .body(fileContent); // 返回文件内容
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // 出错返回500
        }
    }

    //大文件触发下载
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestParam("fileName") String fileName,
                                                           @RequestParam("fileType") String fileTypeString) {
        FileType fileType = FileType.valueOf(fileTypeString);
        File file = new File(uploadDir + fileType.getPath() + fileName);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        try {
            // 对文件名进行 URL 编码
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString())
                    .replaceAll("\\+", "%20")  // 处理空格为 %20
                    .replaceAll("%28", "(")
                    .replaceAll("%29", ")");
            headers.add("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // 或者使用合适的异常处理机制
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
