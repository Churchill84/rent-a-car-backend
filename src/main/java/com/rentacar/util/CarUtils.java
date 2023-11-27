package com.rentacar.util;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CarUtils {

    private static final String IMAGE_STORE_DIR = "/var/app_images"; // Define your image storage path

    public static String downloadAndStoreImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = extractFileNameFromURL(url);

        Path targetPath = Path.of(IMAGE_STORE_DIR, fileName);
        try (InputStream in = url.openStream()) {
            Files.createDirectories(targetPath.getParent()); // Ensure the directory exists
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return targetPath.toString();
    }

    private static String extractFileNameFromURL(URL url) {
        String[] pathSegments = url.getPath().split("/");
        return pathSegments[pathSegments.length - 1];
    }
}
