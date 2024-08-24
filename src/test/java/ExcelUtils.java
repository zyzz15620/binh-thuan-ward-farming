import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.params.provider.Arguments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExcelUtils {

    public static Stream<Arguments> excelDataProvider() throws IOException {
        List<Arguments> data = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/environmental_topics_1000.xlsx");
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String topic = row.getCell(1).getStringCellValue();
            String idea = row.getCell(2).getStringCellValue();
            String summary = row.getCell(3).getStringCellValue();
            data.add(Arguments.of(topic, idea, summary));
        }

        workbook.close();
        return data.stream();
    }
}
