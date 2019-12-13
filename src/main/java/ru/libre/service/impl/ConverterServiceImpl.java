package ru.libre.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.libre.dto.ParamsDto;
import ru.libre.service.ConverterService;

@Service
public class ConverterServiceImpl implements ConverterService {
    @Override
    public Resource convert(MultipartFile file, ParamsDto params) {
        return null;
    }
}
