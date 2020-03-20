package Seminar1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Searializable  {

    public static void serializer (List<Animal> animalList, String file) throws IOException {
        Path path = Paths.get(file);
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(animalList);
        }
    }

    public static List<Animal> deSerializer(String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        List<Animal> animalList;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))){
            animalList = (List<Animal>) inputStream.readObject();
        }

        return animalList;
    }

    public static void hardDeserializer(List<Animal> listAnimals, String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        try (DataOutputStream outputStream =
                     new DataOutputStream(Files.newOutputStream(path))) {

            outputStream.writeInt(listAnimals.size());

            for (Animal animal : listAnimals) {
                outputStream.writeUTF(animal.getName());
                outputStream.writeInt(animal.getAge());
                outputStream.writeUTF(animal.getTypeFood().name());
                outputStream.writeInt(animal.getFoodList().size());

                for (Food food : animal.getFoodList()) {
                    outputStream.writeUTF(food.getName());
                    outputStream.writeInt(food.getCount());
                }
            }
        }
    }

    public static List<Animal> hardDeserializer(String file) throws IOException, ClassNotFoundException {
        Path path = Paths.get(file);
        List<Animal> animalList = new ArrayList<>();
        int CounterOfFood;
        int age;
        int cFood;
        String name;
        String nameOfFood;
        TypeFood typeFood;

        try (DataInputStream inputStream =
                     new DataInputStream(Files.newInputStream(path))) {

            int animalCount = inputStream.readInt();

            for (int i = 0; i < animalCount; i++) {
                name = inputStream.readUTF();
                age = inputStream.readInt();
                typeFood = TypeFood.valueOf(inputStream.readUTF());
                CounterOfFood = inputStream.readInt();
                List<Food> foodList = new ArrayList<>();

                for (int j = 0; j < CounterOfFood; j++) {
                    nameOfFood = inputStream.readUTF();
                    cFood = inputStream.readInt();
                    foodList.add(new Food(nameOfFood, cFood));
                }
                animalList.add(new Animal(name, age, typeFood, foodList));
            }
        }
        return animalList;
    }
}