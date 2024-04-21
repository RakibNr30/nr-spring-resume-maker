package com.resume.config.file;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class MultipartInitializer extends HttpServlet {
}
