package org.eeasonloo.controller;

import org.eeasonloo.constant.MessageConstant;
import org.eeasonloo.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            String fileSavePath = "C:\\my_java\\repository\\healthcare_parent\\healthcare_backend\\src\\main\\webapp\\upload";
            // Generate a unique UUID name for image + suffix(.jpg)
            String originalFilename  = imgFile.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(lastIndexOf);
            String newFilename = UUID.randomUUID().toString() + suffix;

            File uploadSaveFile = new File(fileSavePath, newFilename);
            imgFile.transferTo(uploadSaveFile);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,newFilename);

        } catch (IOException e) {
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);

        }


    }
}
