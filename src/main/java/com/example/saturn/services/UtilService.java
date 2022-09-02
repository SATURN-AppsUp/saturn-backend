package com.example.saturn.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@AllArgsConstructor
@Service
public class UtilService {

    public String genRandomCode(int length) {
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i=0; i< length;i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }
}
