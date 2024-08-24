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

        boolean skipHeader = true;

        for (Row row : sheet) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }

            if (row != null) {
                String topic = getCellValueAsString(row.getCell(0));  // Cột A (hoặc cột đầu tiên)
                String idea = getCellValueAsString(row.getCell(1));   // Cột B (hoặc cột thứ hai)
                String summary = getCellValueAsString(row.getCell(2)); // Cột C (hoặc cột thứ ba)
                data.add(Arguments.of(topic, idea, summary));
            }
        }

        workbook.close();
        fileInputStream.close();
        return data.stream();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
