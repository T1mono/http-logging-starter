package ru.t1.opencschool.httploggingstarter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Простой контроллер для проверки работы логирования.
 */
@RestController
@RequestMapping("/api/v1")
public class HttpLoggingController {
    @GetMapping("/test")
    public String test() {
        return "GET Response";
    }

    @PostMapping("/test")
    public String postTest() {
        return "POST Response";
    }

    @PutMapping("/test")
    public String putTest() {
        return "PUT Response";
    }

    @DeleteMapping("/test")
    public String deleteTest() {
        return "DELETE Response";
    }
}
