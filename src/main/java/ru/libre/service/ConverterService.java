package ru.libre.service;

import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ConverterService {
    /**
     * Конвертация входящего файла
     *
     * @param file Файл для конвертации
     * @param from Исходный формат.
     *             Необязательный, в случае отсутсвия параметра, исходный формат будет определяться по расширению
     * @param to   Целевой формат
     * @return Сконвертированный файл
     */
    Resource convert(@NonNull MultipartFile file, @Nullable String from, @NonNull String to) throws IOException;
}
