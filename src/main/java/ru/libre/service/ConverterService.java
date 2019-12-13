package ru.libre.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.libre.dto.ParamsDto;

public interface ConverterService {
    Resource convert(MultipartFile file, ParamsDto params);
}
