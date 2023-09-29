package com.example.shoppingweb.restcontroller.admin;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("image")
public class AnhRestController {

    private final String resource = "src/main/resources/static/admin/images/";

    @GetMapping("/loadImage/{imgName}")
    @ResponseBody
    public ResponseEntity<?> downloadFile(@PathVariable("imgName") String imgName) throws IOException {

        byte[] ima = Files.readAllBytes(new File(resource+imgName).toPath());
        ByteArrayResource byteArrayResource = new ByteArrayResource(ima);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .contentType(MediaType.valueOf(MediaType.IMAGE_GIF_VALUE))
                .contentType(MediaType.valueOf("image/jpg"))
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(byteArrayResource);

    }
}
