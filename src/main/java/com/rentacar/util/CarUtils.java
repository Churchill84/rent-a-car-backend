package com.rentacar.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CarUtils {

    private CarUtils() {
    }

    private static final String IMAGE_STORE_DIR = "/var/app_images"; // Define your image storage path

    public static String downloadAndStoreImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        Path targetPath = Path.of(IMAGE_STORE_DIR, fileName);
        Files.createDirectories(targetPath.getParent());

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return targetPath.toString();
    }
}
