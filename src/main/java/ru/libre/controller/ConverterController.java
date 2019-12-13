package ru.libre.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.libre.service.ConverterService;

import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class ConverterController {

    private final ConverterService service;

    @PostMapping(value = "/convert", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> convert(@RequestParam("file") final MultipartFile file,
                                            @RequestParam(value = "from", required = false) String from,
                                            @RequestParam("to") String to) throws IOException {
        return ResponseEntity.ok(service.convert(file, from, to));
    }
}
