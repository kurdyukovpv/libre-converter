package ru.libre.service.helper;

import org.apache.commons.io.FilenameUtils;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class FormatHelper {
    private String name;
    private DocumentFormat source;
    private DocumentFormat target;

    private FormatHelper(String fileName, String from, String to) {
        source = DefaultDocumentFormatRegistry
                .getFormatByExtension(from != null ? from : FilenameUtils.getExtension(fileName));

        target = DefaultDocumentFormatRegistry
                .getFormatByExtension(to != null ? to : FilenameUtils.getExtension(fileName));
        this.name = fileName;
    }

    /**
     * Создание хелпера
     *
     * @param fileName Имя файла
     * @param to       Целевой формат
     * @return FormatHelper
     */
    public static FormatHelper create(@Nullable String fileName, @Nullable String from, @NonNull String to) {
        if (fileName == null) {
            fileName = "file";
        }
        return new FormatHelper(fileName, from, to);
    }

    /**
     * Возвращает исходный формат {{@link org.jodconverter.document.DocumentFormat}}
     *
     * @return {{@link org.jodconverter.document.DocumentFormat}}
     */
    public DocumentFormat getSourceFormat() {
        return source;
    }

    /**
     * Возвращает целевой формат {{@link org.jodconverter.document.DocumentFormat}}
     *
     * @return {{@link org.jodconverter.document.DocumentFormat}}
     */
    public DocumentFormat getTargetFormat() {
        return target;
    }

    /**
     * Возвращает целевое имя
     *
     * @return Имя
     */
    public String getTargetName() {
        return FilenameUtils.removeExtension(name) + "." + target.getExtension();
    }
}
