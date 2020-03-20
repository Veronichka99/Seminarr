package Seminar1;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Animal implements Serializable {

    private String name;
    private int age;
    private TypeFood typeFood;
    private List<Food> foodList;

    public Animal(String name, int age, TypeFood typeFood, List<Food> foodList){
        this.name = name;
        this.age = age;
        this.typeFood = typeFood;
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TypeFood getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(TypeFood typeFood) {
        this.typeFood = typeFood;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", typeFood=" + typeFood +
                ", foodList=" + foodList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(name, animal.name) &&
                typeFood == animal.typeFood &&
                Objects.equals(foodList, animal.foodList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, typeFood, foodList);
    }
}