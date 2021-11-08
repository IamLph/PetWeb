package com.pet.service;

import com.pet.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Result<String> upload(MultipartFile file);
}
