package ru.libre.dto;

import lombok.Data;
import ru.libre.enums.FileType;

@Data
public class ParamsDto {
    private FileType from;
    private FileType to;
}
