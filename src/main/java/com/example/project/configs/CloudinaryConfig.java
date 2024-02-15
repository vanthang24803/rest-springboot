package com.example.project.configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "djla9thdo";

    private final String API_KEY = "523438667831375";

    private final String API_SECRET = "xFYxKxOs6M3Sz8BYYgKsFnLrnd8";

    @Bean
    public Cloudinary cloudinary(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

}
