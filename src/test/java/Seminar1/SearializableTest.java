package Seminar1;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

class SearializableTest {

    List<Animal> animalList = Arrays.asList(
            new Animal("Lion", 20, TypeFood.FISH, Arrays.asList(new Food("Zolotaya ribka", 1))),
            new Animal("Lion2", 30, TypeFood.CHICKEN, Arrays.asList(new Food("kokoko", 1))));

    @Test
    public void serializer() throws IOException, ClassNotFoundException {
        Searializable.serializer(animalList, "animalFile");
        assertEquals(animalList, Searializable.deSerializer("animalFile"));
    }

    @Test
    public void serializerEmpty() throws IOException, ClassNotFoundException {
        Searializable.serializer(Collections.emptyList(), "emptyFile");
        assertEquals(Collections.emptyList(), Searializable.deSerializer("emptyFile"));
    }

    @Test
    public void serializerNotEquals() throws IOException, ClassNotFoundException {
        List<Animal> testAnimalList = Arrays.asList(
                new Animal("Shark", 40, TypeFood.GRASS, Arrays.asList(new Food("Grass", 100))),
                new Animal("Vegan", 500, TypeFood.MEAT, Arrays.asList(new Food("meat", 50))));
        Searializable.serializer(animalList, "animalFile");
        Searializable.serializer(testAnimalList,"testFile");
        assertEquals(animalList, Searializable.deSerializer("animalFile"));
        assertNotEquals(testAnimalList,Searializable.deSerializer("animalFile"));
        assertEquals(testAnimalList, Searializable.deSerializer("testFile"));
        assertNotEquals(animalList,Searializable.deSerializer("testFile"));
    }

    @Test
    public void serializerException() throws ClassNotFoundException {
        try {
            Searializable.deSerializer("nooooooooooo");
            fail();
        } catch (NoSuchFileException e) {

        } catch (IOException e) {
            fail();
        }
    }

}