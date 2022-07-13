package com.example.sahal.Springbootmultithreading2.Controller;

import com.example.sahal.Springbootmultithreading2.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class userController {
    
    @Autowired
    private userService userService;
    
    public ResponseEntity<String> saveUsers(@RequestParam(value = "files")MultipartFile[] files) throws Exception{
        String result = null;
        for (MultipartFile file : files){
            //result = userService.saveUsers(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
