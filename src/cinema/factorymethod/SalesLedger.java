package cinema.factorymethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Журнал продажів: сюди процес продажу записує кожен проданий квиток. */
public class SalesLedger {
    private final List<String> records = new ArrayList<>();

    void add(String channel, String seat, int price) {
        records.add(channel + " | місце " + seat + " | " + price + " грн");
    }

    public List<String> records() {
        return Collections.unmodifiableList(records);
    }

    public int total() {
        return records.size();
    }
}
