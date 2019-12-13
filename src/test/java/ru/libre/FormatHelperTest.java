package ru.libre;

import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.libre.service.helper.FormatHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест FormatHelper")
public class FormatHelperTest {

    @Test
    void targetFormatTest() {
        FormatHelper helper = FormatHelper.create("Ответ_Иванову.И.И.docx", null, "Pdf");
        assertEquals(DefaultDocumentFormatRegistry.PDF, helper.getTargetFormat());
    }

    @Test
    void sourceFormatIsNullTest() {
        FormatHelper helper = FormatHelper.create("Ответ_Иванову.И.И.docx", null, "Pdf");
        assertEquals(DefaultDocumentFormatRegistry.DOCX, helper.getSourceFormat());
    }

    @Test
    void sourceFormatTest() {
        FormatHelper helper = FormatHelper.create("Ответ_Иванову.И.И.docx", "docX", "Pdf");
        assertEquals(DefaultDocumentFormatRegistry.DOCX, helper.getSourceFormat());
    }

    @Test
    void targetFileNameTest() {
        FormatHelper helper = FormatHelper.create("Ответ_Иванову.И.И.docx", null, "Pdf");
        assertEquals("Ответ_Иванову.И.И.pdf", helper.getTargetName());
    }
}
