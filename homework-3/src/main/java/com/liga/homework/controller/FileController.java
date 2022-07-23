package com.liga.homework.controller;

import com.liga.homework.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @GetMapping("/help")
    ResponseEntity<String> getHelp(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=utf-8");
        return new ResponseEntity <>(fileService.getHelpFromFile(),headers, HttpStatus.OK);
    }

    @GetMapping("/test")
    ResponseEntity<String> getDB(){
        return new ResponseEntity <>(fileService.open(), HttpStatus.OK);
    }

    @GetMapping("/save")
    ResponseEntity<String> saveInFile(){
        return new ResponseEntity <>(fileService.save(), HttpStatus.OK);
    }

}
