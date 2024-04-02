package com.example.project.untils;

import java.util.Locale;
import java.util.Random;

public class CodeGeneratorUtil {
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
    private static final Random RANDOM = new Random();

    public static String generateCode(int length) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomCharacter = CHARACTERS.charAt(randomIndex);
            code.append(randomCharacter);
        }

        return code.toString().toUpperCase(Locale.ROOT);
    }
}
