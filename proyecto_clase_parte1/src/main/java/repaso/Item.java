package repaso;

public class Item {
    private final String name;
    private final Double price;
    public Item(final String name, final Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item: {name: " + name + ", price: " + price + "}";
    }

}
