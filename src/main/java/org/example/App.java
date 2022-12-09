package org.example;

import com.opencsv.CSVWriter;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void createDirectory(String dirName) {
        try {
            Files.createDirectory(Paths.get(dirName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String fileName) {
        try {
            Files.createFile(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> convertToCSV(List<Person> list) {
        List<String[]> personsCSV = new ArrayList<>();
        for (Person p: list) {
            personsCSV.add(new String[]{p.name(), String.valueOf(p.age()) , p.sex() + ""});
        }
        return personsCSV;
    }

    public static void writeCSVFile(String name, List<String[]> list) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(name))) {
            writer.writeAll(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadPictureFromURL(String urlAddress) {
        try(InputStream in = new URL(urlAddress).openStream()){
            Files.copy(in, Paths.get("image.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Person> humans = List.of(new Person("Teodor", 16, Sex.MAN),
                new Person("Piter", 23, Sex.MAN),
                new Person("Olena", 42, Sex.WOMAN),
                new Person("Ivan Ivanovich", 69, Sex.MAN),
                new Person("Vasuluna", 17, Sex.WOMAN),
                new Person("Christine", 25, Sex.WOMAN),
                new Person("Nazar", 16, Sex.MAN),
                new Person("Oleg", 37, Sex.MAN),
                new Person("Gordon", 27, Sex.MAN));

        List<String[]> persons = convertToCSV(humans);
        createDirectory("output");
        createFile("output/result.csv");
        writeCSVFile("output/result.csv", persons);

        downloadPictureFromURL("https://cdn.fansshare.com/photos/futurama/bender-futurama-by-moonlightwatcher-bender-635216775.jpg");

    }
}
