package com.example.sahal.Springbootmultithreading.controller;

import com.example.sahal.Springbootmultithreading.model.User;
import com.example.sahal.Springbootmultithreading.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class userController {

    @Autowired
    private userService service;

    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        for (MultipartFile file : files) {
            service.saveUsers(file);
        }
//        service.saveUsers(files[0]);
//        service.saveUsers(files[1]);
//        service.saveUsers(files[2]);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(value = "/users", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers() {
        return  service.findAllUsers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsersByThread", produces = "application/json")
    public  ResponseEntity getUsers(){
        log.info("first method");
        CompletableFuture<List<User>> users1=service.findAllUsers();
        log.info("second method");
        CompletableFuture<List<User>> users2=service.findAllUsers();
        log.info("third method");
        CompletableFuture<List<User>> users3=service.findAllUsers();
        CompletableFuture.allOf(users1,users2,users3).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
