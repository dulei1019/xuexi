package com.atguigu.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author
 * @Description:
 */
public interface FileUploadService {
    String upload(MultipartFile file);
}
