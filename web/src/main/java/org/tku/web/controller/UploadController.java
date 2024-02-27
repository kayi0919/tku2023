package org.tku.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    //修改自己路徑
    private static final String UPLOAD_DIR = "D:/tkuspring/practice/tku2023/web/src/main/resources/static/img/";

    @GetMapping("/web/upload")
    public String showUploadForm(Model model) {
        return "manager/upload";
    }

    @PostMapping("/web/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "請選擇一個檔案來上傳");
            return "manager/upload"; // 直接返回upload.html模板
        }

        try {
            String fileName = file.getOriginalFilename();
            String filePath = "/img/" + fileName; // 相對於static資料夾的路徑
            File dest = new File(UPLOAD_DIR + fileName);
            file.transferTo(dest);

            model.addAttribute("imageUrl", filePath);
            model.addAttribute("message", "上傳成功");
            return "upload"; // 成功後仍然停留在upload.html模板
        } catch (IOException e) {
            model.addAttribute("message", "上傳失敗：" + e.getMessage());
            return "upload"; // 若出現錯誤，仍然停留在upload.html模板
        }
    }


}

