package com.sn.collaborationwebapp.serviceImpl;

import com.sn.collaborationwebapp.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file, String originalFileName) throws IOException {
        // Generate a random ID
        String randomId = UUID.randomUUID().toString();

        // Extract the file extension from the original file name
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // Concatenate the random ID and file extension to create a new unique file name
        String fileName = randomId.concat(fileExtension);

        // Full path to store the image
        String filePath = path + File.separator + fileName;

        // Create the folder if it does not exist
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Copy the file to the specified path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Return the original file name as the stored file name
        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
