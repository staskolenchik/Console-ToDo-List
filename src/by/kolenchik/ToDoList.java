package by.kolenchik;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> taskList;
    private static final String PATH_TO_PATH_FILE = "C:\\Users\\" +
            "ded\\IdeaProjects\\TODOList.Console\\src\\resources\\path.txt";

    public ToDoList() {
        taskList = new ArrayList<>();
        loadListFromFile();
    }

    void displayList() {
        if (taskList.size() == 0) {
            System.out.print("\n-To-do list is empty!\n");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.print((i + 1) + ". " + taskList.get(i) + "\n");
            }
        }
        System.out.println();
    }

    void addTask() {
        try {
            System.out.print("-Enter a task: ");
            taskList.add(Main.reader.readLine());
            System.out.println();
        } catch (IOException e) {
            System.out.println("-Your input is incorrect. Please input correct information");
        }
    }

    void removeTask() {
        try {
            System.out.print("-Which task do you want to remove? Enter the number of task: ");
            byte strToRemove = Byte.parseByte(Main.reader.readLine());
            taskList.remove(strToRemove - 1);
            System.out.println();
        } catch (Exception e) {
            System.out.println("-Your input is incorrect. Please input correct inf to remove from list\n");
        }
    }

    void save() {
        try {
            /**
             * creating text file and saving task list
             * */

            System.out.print("-Enter the path and file name to save: ");
            String filePath = Main.reader.readLine();

            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(PATH_TO_PATH_FILE));
            bufferedOutputStream.write(filePath.getBytes(), 0, filePath.getBytes().length);
            bufferedOutputStream.close();

            Path file = Paths.get(filePath);
            Files.write(file, taskList); //without charset file - path of file
            //or Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
            System.out.println("-File saved!\n\n");


        } catch (Exception e) {
            System.out.println("-Enter the right file path!");
            System.err.println(e);
        }

    }

    void markTaskAsDone() {
        try {
            System.out.print("-Which task have you done? Enter the number: ");
            byte taskNum = Byte.parseByte(Main.reader.readLine());
            StringBuffer strBuff = new StringBuffer(taskList.get(taskNum - 1));
            /**
             * random encouraging of done work
             * */
            byte encoutagingOption = (byte) (Math.random() * 100);
            if (encoutagingOption >= 0 && encoutagingOption <= 25) {
                strBuff.append(" - Done! Good JOB!");
            } else if (encoutagingOption > 25 && encoutagingOption <= 50) {
                strBuff.append(" - Done! You so good, Maaaan!");
            } else if (encoutagingOption > 50 && encoutagingOption <= 75) {
                strBuff.append(" - Done! Common, I know you can better!");
            } else if (encoutagingOption > 75 && encoutagingOption < 100) {
                strBuff.append(" - Done! Great work!");
            }
            taskList.set(taskNum - 1, strBuff.toString());
            System.out.println();

        } catch (IOException e) {
            System.out.println("-You did smth wrong, but I don't know what :(");
        }
    }

    void loadListFromFile() {
        try {
            FileInputStream fileReader = new FileInputStream(PATH_TO_PATH_FILE);
            StringBuilder savedFilePath = new StringBuilder(""); //gets the path of the saved file

            /**
             * check if it works with empty file
             * */
            if (fileReader.available() > 5) {
                while (fileReader.available() > 0) {
                    int count = fileReader.read();
                    savedFilePath.append((char) count);
                }
            } else {
                fileReader.close();
                throw new Exception();

            }
            fileReader.close();
            FileInputStream fileInputStream =
                    new FileInputStream(savedFilePath.toString()); // open stream to read saved file

            StringBuilder savedToDoList = new StringBuilder("");//contains the whole todolist as string
            while (fileInputStream.available() > 0) {
                int count = fileInputStream.read();
                savedToDoList.append((char) count);
            }
            String[] strArray = savedToDoList.toString().split("\r\n");
            for (int i = 0; i < strArray.length; i++) {
                taskList.add(strArray[i]);
            }
            System.out.println("To-Do List successfully has been loaded");
            fileInputStream.close();

        } catch (Exception e) {
            System.out.println("New To-Do List created");
        }
    }
}
