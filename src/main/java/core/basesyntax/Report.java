package core.basesyntax;

public class Report {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int VALUES_COUNT = 2;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private final int supply;
    private final int buy;

    private Report(int supply, int buy) {
        this.supply = supply;
        this.buy = buy;
    }

    public int getSupply() {
        return supply;
    }

    public int getBuy() {
        return buy;
    }

    private static Report prepare(int[][] data) {
        int supply = 0;
        int buy = 0;
        for (int[] record : data) {
            supply += record[SUPPLY_INDEX];
            buy += record[BUY_INDEX];
        }
        return new Report(supply, buy);
    }

    public static Report prepare(String[][] data) {
        int[][] values = new int[data.length][VALUES_COUNT];
        int i = 0;
        for (String[] record : data) {
            String operation = record[WorkWithFile.OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(record[WorkWithFile.AMOUNT_INDEX]);
            values[i][SUPPLY_INDEX] = operation.equals(SUPPLY_OPERATION) ? amount : 0;
            values[i++][BUY_INDEX] = operation.equals(BUY_OPERATION) ? amount : 0;
        }
        return prepare(values);
    }
}
