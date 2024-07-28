package com.service.levain.domain.service;


import com.service.levain.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.service.levain.global.exception.ErrorCode.FILE_NOT_FOUND;
import static com.service.levain.global.exception.ErrorCode.FILE_UPLOAD_FAILED;

@Service
public class FileService {
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws FileNotFoundException {
        // 중복 문제를 해결하는 방법
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // UUID로 받은 값과 원래 파일의 이름의 확장자를 조합해서 새로운 이름을 만들어준다.
        String saveName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + saveName;
        try {
            FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
            fos.write(fileData);
            fos.close();
        } catch (FileNotFoundException e) {
            throw new CustomException(FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new CustomException(FILE_UPLOAD_FAILED);
        }
        return saveName;
    }

    public void deleteFile(String filePath) {
        File deleteFile = new File(filePath);
        if (deleteFile.exists()) {
            deleteFile.delete();
        } else {
            System.out.println("파일이 존재하지 않습니다." + filePath);
            throw new CustomException(FILE_NOT_FOUND);
        }

    }

    private String extractLocalName(String fullPath) {
        int lastSlashIndex = fullPath.lastIndexOf("/");
        return fullPath.substring(lastSlashIndex + 1);
    }
}