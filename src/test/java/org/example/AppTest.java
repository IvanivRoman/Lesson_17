package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AppTest {
    private static CSVReader reader;
    private final static String csvFile = "output/result.csv";
    private static List<Person> humans;

    @BeforeAll
    static void initialize(){
        humans = List.of(new Person("Teodor", 16, Sex.MAN),
                new Person("Piter", 23, Sex.MAN),
                new Person("Olena", 42, Sex.WOMAN),
                new Person("Ivan Ivanovich", 69, Sex.MAN),
                new Person("Vasuluna", 17, Sex.WOMAN),
                new Person("Christine", 25, Sex.WOMAN),
                new Person("Nazar", 16, Sex.MAN),
                new Person("Oleg", 37, Sex.MAN),
                new Person("Gordon", 27, Sex.MAN));
        try {
            reader = new CSVReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConvertToCSV() {
        try {
            List<String[]> actual = App.convertToCSV(humans);
            List<String[]> expected = reader.readAll();
            for (int i = 0; i < expected.size(); i++) {
                assertThat(expected.get(i)).isEqualTo(actual.get(i));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testImageHeightAndWidth() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(image.getHeight()).isEqualTo(531);
        assertThat(image.getWidth()).isEqualTo(558);
    }

    @Test
    void testImageIsDownloaded() {
        assertThat(Files.exists(Paths.get("image.jpg"))).isTrue();
    }

}
