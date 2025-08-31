package processes;

import valid.Validator;

import java.util.Scanner;

import static lists.VerificationDictionary.verificationDictionary;
import static lists.standartWords.*;

public class Interface {
    private static String firstFileName;
    private static String secondFileName;
    private static int key;
    static Processes programRunning = new Processes();
    static Validator validator = new Validator();




    public void processStarting() {
        helloPart();
        Scanner scanner = new Scanner(System.in);
        choseOne(scanner.nextLine());

    }

    public void choseOne(String scanner){
        switch (scanner) {
            case "1":
                answerOne();
                break;
            case "2":
                answerTwo();
                break;
            case "3":
                answerThree();
                break;
            case "4":
                answerFour();
                break;
            case "exit":
                break;
            default:
                System.err.println("Некорректное значение");
        }
    }


    public void helloPart() {
        System.out.print(GREETINGS);
    }

    public void answerOne() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(FIRST_FILE_PATH_encr);
            firstFileName = scanner.nextLine();
            validator.validateReadingFile(firstFileName);
            System.out.print(SECOND_FILE_PATH_encr);
            secondFileName = scanner.nextLine();
            validator.validateWritingFile(secondFileName);
            System.out.print(KEY);
            String c = scanner.nextLine();
            key = Integer.parseInt(c);
            validator.validateKey(key);
            programRunning.encrypt(firstFileName, secondFileName, key);
        }
    }

    public void answerTwo() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(FIRST_FILE_PATH_decr);
            firstFileName = scanner.nextLine();
            validator.validateReadingFile(firstFileName);
            System.out.print(SECOND_FILE_PATH_decr);
            secondFileName = scanner.nextLine();
            validator.validateWritingFile(secondFileName);
            System.out.print(KEY);
            String bufKey = scanner.nextLine();
            key = Integer.parseInt(bufKey);
            validator.validateKey(key);
            programRunning.decrypt(firstFileName, secondFileName, key);
        }
    }

    public static void answerThree() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(FIRST_FILE_PATH_decr);
            firstFileName = scanner.nextLine();
            validator.validateReadingFile(firstFileName);
            System.out.print(SECOND_FILE_PATH_decr);
            secondFileName = scanner.nextLine();
            validator.validateWritingFile(secondFileName);
            programRunning.decryptByBruteForce(firstFileName, secondFileName, verificationDictionary);
        }
    }

    public static void answerFour() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(FIRST_FILE_PATH_decr);
            firstFileName = scanner.nextLine();
            validator.validateReadingFile(firstFileName);
            System.out.print(SECOND_FILE_PATH_decr);
            secondFileName = scanner.nextLine();
            validator.validateWritingFile(secondFileName);
            programRunning.findMostUsableSymbol(firstFileName, secondFileName);
        }
    }

}
