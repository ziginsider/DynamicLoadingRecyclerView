package io.github.ziginsider.dynamicloadingrecyclerview.Model;

/**
 * Created by zigin on 11.10.2017.
 */

public class Item {
    private String name;
    private int lenght;

    public Item(String name, int lenght) {
        this.name = name;
        this.lenght = lenght;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
}
