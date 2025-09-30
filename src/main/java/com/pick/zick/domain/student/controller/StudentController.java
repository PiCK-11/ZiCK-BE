package com.pick.zick.domain.student.controller;

import com.pick.zick.domain.student.service.GetRandomHash;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;

    @GetMapping("/qr")
    public String getRandomHash() throws NoSuchAlgorithmException {
        return getRandomHash.makeHash();
    }
}
