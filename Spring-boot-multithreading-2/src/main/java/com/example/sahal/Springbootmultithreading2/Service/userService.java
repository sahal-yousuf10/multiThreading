package com.example.sahal.Springbootmultithreading2.Service;

import com.example.sahal.Springbootmultithreading2.Model.User;
import com.example.sahal.Springbootmultithreading2.Repository.userRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class userService {

    @Autowired
    private userRepository userRepository;

    private List<User> parseCSVFile(MultipartFile file) throws Exception{
        List<User> userList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                User user = new User();
                user.setFirstName(data[0]);
                user.setLastName(data[1]);
                user.setEmail(data[2]);
                user.setGender(data[3]);
                user.setCompanyName(data[4]);
                user.setCity(data[5]);
                user.setJobTitle(data[6]);
                userList.add(user);
            }
            return userList;
        }
        catch (Exception ex){
            log.error("Failed to parse CSV file "+ex);
            throw ex;
        }
    }
}
