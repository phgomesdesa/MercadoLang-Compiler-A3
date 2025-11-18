public class Ast {

    public interface Stmt {}

    public static class AddItem implements Stmt {
        public final String name;
        public final double price;
        public final int stock;
        public AddItem(String name, double price, int stock) { this.name = name; this.price = price; this.stock = stock; }
        public String toString() { return "AddItem(" + name + ", " + price + ", " + stock + ")"; }
    }

    public static class SellItem implements Stmt {
        public final String name;
        public final int quantity;
        public SellItem(String name, int quantity) { this.name = name; this.quantity = quantity; }
        public String toString() { return "SellItem(" + name + ", " + quantity + ")"; }
    }

    public static class ShowStock implements Stmt {
        public String toString() { return "ShowStock()"; }
    }

    public static class ShowRevenue implements Stmt {
        public String toString() { return "ShowRevenue()"; }
    }

    public static class UpdatePrice implements Stmt {
        public final String name;
        public final double newPrice;
        public UpdatePrice(String name, double newPrice) { this.name = name; this.newPrice = newPrice; }
        public String toString() { return "UpdatePrice(" + name + ", " + newPrice + ")"; }
    }

    public static class RemoveItem implements Stmt {
        public final String name;
        public RemoveItem(String name) { this.name = name; }
        public String toString() { return "RemoveItem(" + name + ")"; }
    }

    public static class ListItems implements Stmt {
        public String toString() { return "ListItems()"; }
    }
}