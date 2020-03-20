package Seminar1;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Food(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return count == food.count &&
                Objects.equals(name, food.name);
    }

}