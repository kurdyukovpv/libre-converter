package ru.libre.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.libre.dto.ParamsDto;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class ConverterController {

    @PostMapping(value = "/convert", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> convert(@RequestParam("file") final MultipartFile file,
                                            @RequestBody final ParamsDto params) {
        return null;
    }

}
