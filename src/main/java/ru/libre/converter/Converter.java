package ru.libre.converter;

import org.apache.commons.io.FilenameUtils;
import org.artofsolving.jodconverter.StandardConversionTask;
import org.artofsolving.jodconverter.document.DefaultDocumentFormatRegistry;
import org.artofsolving.jodconverter.document.DocumentFamily;
import org.artofsolving.jodconverter.document.DocumentFormat;
import org.artofsolving.jodconverter.document.DocumentFormatRegistry;
import org.artofsolving.jodconverter.office.OfficeException;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    public static DocumentFormat XLSX;

    /**
     * @see "https://wiki.openoffice.org/wiki/Documentation/DevGuide/Spreadsheets/Filter_Options#Token_7.2C_csv_import"
     */
    public static DocumentFormat CSV_SEMICOLON_CP1251;

    static {
        XLSX = new DocumentFormat("Calc MS Excel 2007 XML", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        XLSX.setInputFamily(DocumentFamily.SPREADSHEET);
        XLSX.setStoreProperties(DocumentFamily.SPREADSHEET, Map.of("FilterName", "Calc MS Excel 2007 XML"));

        CSV_SEMICOLON_CP1251 = new DocumentFormat("Comma Separated Values", "csv", "text/csv");
        CSV_SEMICOLON_CP1251.setInputFamily(DocumentFamily.SPREADSHEET);
        Map<String, Object> params = new HashMap<>();
        params.put("FilterName", "Text - txt - csv (StarCalc)");
        params.put("FilterOptions", "59,34,34"); // Field Separator: ';' (59) Text Delimiter: '"' (34), Encoding cp1251 (34)
        CSV_SEMICOLON_CP1251.setLoadProperties(params);
        CSV_SEMICOLON_CP1251.setStoreProperties(DocumentFamily.SPREADSHEET, params);
    }

    private final OfficeManager officeManager;
    private final DocumentFormatRegistry formatRegistry;
    private Map<String, ?> defaultLoadProperties;

    public Converter(OfficeManager officeManager) {
        this(officeManager, new DefaultDocumentFormatRegistry());
    }

    public Converter(OfficeManager officeManager, DocumentFormatRegistry formatRegistry) {
        this.defaultLoadProperties = this.createDefaultLoadProperties();
        this.officeManager = officeManager;
        this.formatRegistry = formatRegistry;
    }

    private Map<String, Object> createDefaultLoadProperties() {
        Map<String, Object> loadProperties = new HashMap<>();
        loadProperties.put("Hidden", true);
        loadProperties.put("ReadOnly", true);
        loadProperties.put("UpdateDocMode", (short) 1);
        return loadProperties;
    }

    public void setDefaultLoadProperties(Map<String, ?> defaultLoadProperties) {
        this.defaultLoadProperties = defaultLoadProperties;
    }

    public DocumentFormatRegistry getFormatRegistry() {
        return this.formatRegistry;
    }

    public void convert(File inputFile, File outputFile) throws OfficeException {
        String inputExtension = FilenameUtils.getExtension(inputFile.getName());
        String outputExtension = FilenameUtils.getExtension(outputFile.getName());
        DocumentFormat inputFormat = this.formatRegistry.getFormatByExtension(inputExtension);
        DocumentFormat outputFormat = this.formatRegistry.getFormatByExtension(outputExtension);
        this.convert(inputFile, outputFile, inputFormat, outputFormat);
    }

    public void convert(File inputFile, File outputFile, DocumentFormat outputFormat) throws OfficeException {
        String inputExtension = FilenameUtils.getExtension(inputFile.getName());
        DocumentFormat inputFormat = this.formatRegistry.getFormatByExtension(inputExtension);
        convert(inputFile, outputFile, inputFormat, outputFormat);
    }

    public void convert(File inputFile, File outputFile, DocumentFormat inputFormat, DocumentFormat outputFormat) throws OfficeException {
        StandardConversionTask conversionTask = new StandardConversionTask(inputFile, outputFile, outputFormat);
        conversionTask.setDefaultLoadProperties(this.defaultLoadProperties);
        conversionTask.setInputFormat(inputFormat);
        this.officeManager.execute(conversionTask);
    }
}
