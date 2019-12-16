package ru.libre.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class InfoController {

    @GetMapping(value = "/version", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convert() throws IOException {
        return ResponseEntity.ok("0.0.1");
    }
}
