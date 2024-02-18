package com.example.project.untils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenUntils {

    public static String generateToken() {
        return UUID.randomUUID().toString() +
                UUID.randomUUID().toString();
    }
}
