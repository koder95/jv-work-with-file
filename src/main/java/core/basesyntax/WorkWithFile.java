package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {

    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int RECORD_SIZE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] csv = readCsv(fromFileName);
        Report report = Report.make(csv);
        saveToCsv(report, toFileName);
    }

    private String[][] readCsv(String fileName) {
        try {
            List<String> allLines = Files.readAllLines(Path.of(fileName));
            String[][] data = new String[allLines.size()][RECORD_SIZE];
            int i = OPERATION_TYPE_INDEX;
            for (String line : allLines) {
                String[] record = line.split(",");
                data[i][OPERATION_TYPE_INDEX] = record[OPERATION_TYPE_INDEX];
                data[i++][AMOUNT_INDEX] = record[AMOUNT_INDEX];
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
    }

    private void saveToCsv(Report report, String fileName) {
        String[] lines = new String[] {
                "supply," + report.getSupply(),
                "buy," + report.getBuy(),
                "result," + (report.getSupply() - report.getBuy())
        };
        try {
            Files.write(Path.of(fileName), Arrays.asList(lines));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write the file", e);
        }
    }
}
