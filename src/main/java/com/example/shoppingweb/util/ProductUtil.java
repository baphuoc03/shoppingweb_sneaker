package com.example.shoppingweb.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductUtil {
    public static void deleteImg(List<String> imgs)  {
        if(imgs.size()==0) return;
        for (String img:imgs) {
            System.out.println("asdas");
            Path fileToDeletePath = Paths.get("src/main/resources/static/admin/images/" + img);
            try {
                Files.delete(fileToDeletePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
