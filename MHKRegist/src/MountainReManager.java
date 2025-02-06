/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyPC
 */
import core.MountainList;
import core.StRegistrations;
import java.util.Scanner;

public class MountainReManager {
    public static void main(String[] args) {
        String mFileName; // file chứa danh sách núi
        mFileName = "CSDL/MountainList.csv";
        String rFileName = "CSDL/Registration.txt"; // file chứa phiếu đăng ký
        boolean dataChanged = false;
        
        MountainList mL = new MountainList();
        mL.loadFromFile(mFileName);
        StRegistrations rList = new StRegistrations(mL);
        rList.loadFromFile(rFileName);
        int choice;
        do {
            choice = intMenu("Add regist", "Update", "Print all", "Delete", 
                             "Print By Student Name", "Print By Campus", 
                             "Print By Location", "Save File", "Quit");

            switch (choice) {
                case 1:
                    rList.addRegist();
                    dataChanged = true;
                    break;
                case 2:
                    rList.update();
                    dataChanged = true;
                    break;
                case 3:
                    rList.printList();
                    break;
                case 4:
                    rList.delete();
                    dataChanged = true;
                    break;
                case 5:
                    rList.printByName();
                    break;
                case 6:
                    rList.printByCampus();
                    break;
                case 7:
                    rList.printByLocation();
                    break;
                case 8:
                    rList.saveFile(rFileName);
                    dataChanged = false;
                    break;
                default:
                    if (dataChanged) {
                        if (getBoolean("Do you want to save changes?")) {
                            rList.saveFile(rFileName);
                        }
                    }
                    System.out.println("Bye!");
                    break;
            }
        } while (choice < 9);
    }
    public static int intMenu(String... options) {
        Scanner scanner = new Scanner(System.in);

        // In bảng header
        System.out.printf("+----+-------------------------+\n");
        System.out.printf("| No | Options                 |\n");
        System.out.printf("+----+-------------------------+\n");

        // In các option
        for (int i = 0; i < options.length; i++) {
            System.out.printf("| %-2d | %-23s |\n", (i + 1), options[i]);
        }

        System.out.printf("+----+-------------------------+\n");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    public static boolean getBoolean(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + " (true/false): ");
        return scanner.nextBoolean();
    }
}