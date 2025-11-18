import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {

    public static class Item {
        public String name;
        public double price;
        public int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return name + " (R$" + price + ") x" + quantity;
        }
    }

    private final Map<String, Item> table = new LinkedHashMap<>();

    public boolean exists(String name) {
        return table.containsKey(name.toLowerCase());
    }

    public Item get(String name) {
        return table.get(name.toLowerCase());
    }

    public void addItem(String name, double price, int quantity) {
        table.put(name.toLowerCase(), new Item(name, price, quantity));
    }

    public Map<String, Item> getAll() {
        return table;
    }
}

