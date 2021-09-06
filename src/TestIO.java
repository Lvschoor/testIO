import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestIO {

    public static void main(String[] args) {

// part 1 of the task: create relative paths (Path), create directories and files (+ check if they exist) using Files

        // create a relative path to the 2 given paths
        Path pathOfMessage = Path.of("../../FromLucToPearl/message.txt");
        Path pathOfAnimal = Path.of("../../FromLucToPearl/animal.txt");

        System.out.println();

        try {
            // if directory FromLucToPearl does not exist, create the necessary directories
            if (Files.isDirectory(pathOfMessage.getParent())) {
                System.out.println("Directory FromLucToPearl already exists");
            } else {
                Files.createDirectories(pathOfMessage.getParent());
                System.out.println("Directory FromLucToPearl created.");
            }

            // if file message.txt does not exist, create it
            if (Files.notExists(pathOfMessage)) {
                Files.createFile(pathOfMessage);
                System.out.println("File message.txt has been created");
            } else {
                System.out.println("File message.txt already exists");
            }

            // if file animal.txt does not exist, create it
            if (Files.notExists(pathOfAnimal)) {
                Files.createFile(pathOfAnimal);
                System.out.println("File animal.txt has been created");
            } else {
                System.out.println("File animal.txt already exists");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

// part 2 of the task: write a text to message.text and read it from the file using FileReader and FileWriter

        // String holding text to be written to message.txt
        String joke = "Two students are leaving a Java seminar\n" +
                "The guy turns to the girl and says \"So... how much do you weigh?\"\n" +
                "The girl says, \"I'm not telling you! That's private!\"\n" +
                "Taken aback, the guy says \"But I thought we were in the same class!!\"";

        // write String joke to message.txt using FileWriter
        try (FileWriter fileWriter = new FileWriter(pathOfMessage.toFile())) {
            fileWriter.write(joke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read text from message.txt and print in console
        try (FileReader fileReader = new FileReader(pathOfMessage.toFile())) {
            int character;
            System.out.println("______________________________");
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
            System.out.println("\n______________________________");

        } catch (IOException e) {
            e.printStackTrace();
        }

// part 3 of the task: create an instance of a class and write/read it to the file animal.txt.

        // create instance of class Dog
        Dog myDog = new Dog("Pepper", false);

        // create ObjectOutputStream, using FileOutputStream to write instance of Dog (myDog) to file animal.txt
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathOfAnimal.toFile());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);){
            objectOutputStream.writeObject(myDog);
            System.out.println("myDog (instance of Dog) has been written to animal.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create ObjectInputStream, using FileInputStream to read instance of Dog (myDog) to file animal.txt
        try (FileInputStream fileInputStream = new FileInputStream(pathOfAnimal.toFile());
             ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);){
            myDog = (Dog) objectInputStream.readObject();
            System.out.println("______________________________");
            System.out.println(myDog);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
