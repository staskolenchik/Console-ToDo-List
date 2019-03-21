package by.kolenchik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    private static boolean isStoped = false;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ToDoList toDoList = new ToDoList();

    public static void main(String[] args) {
        while (!isStoped) {
            try {
                System.out.print("0. Exit program.\n" +
                        "1. Display the to-do list.\n" +
                        "2. Add item to to-do list.\n" +
                        "3. Remove item from to-do list.\n" +
                        "4. Mark task as done.\n" +
                        "5. Save task list.\n" +
                        "-Enter the number of option: ");
                String strChoice = reader.readLine();
                if (strChoice.equals("")) {
                    System.out.println("-Input proposed option\n");
                } else {
                    byte choice = Byte.parseByte(strChoice);
                    switch (choice) {
                        case 0:
                            System.out.print("-You left the program!");
                            isStoped = true;
                            break;
                        case 1:
                            toDoList.displayList();
                            break;
                        case 2:
                            toDoList.addTask();
                            break;
                        case 3:
                            toDoList.removeTask();
                            break;
                        case 4:
                            toDoList.markTaskAsDone();
                            break;
                        case 5:
                            toDoList.save();
                            break;
                        default:
                            System.out.println("Input proposed option");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error! Input proposed option");
            }
        }
    }
}
