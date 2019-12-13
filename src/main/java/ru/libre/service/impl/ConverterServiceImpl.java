package ru.libre.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.libre.exception.ConversionException;
import ru.libre.service.ConverterService;
import ru.libre.service.helper.FormatHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class ConverterServiceImpl implements ConverterService {

    private final DocumentConverter converter;

    @Override
    public Resource convert(MultipartFile file, String from, String to) throws IOException {
        FormatHelper helper = FormatHelper.create(file.getOriginalFilename(), from, to);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            converter
                    .convert(file.getInputStream())
                    .as(helper.getSourceFormat())
                    .to(os)
                    .as(helper.getTargetFormat())
                    .execute();

            return new ByteArrayResource(os.toByteArray()) {
                @Override
                public String getFilename() {
                    return helper.getTargetName();
                }
            };
        } catch (OfficeException e) {
            log.error("Conversion error", e);
            throw new ConversionException("Conversion error", e);
        }
    }
}
