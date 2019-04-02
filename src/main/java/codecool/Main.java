package codecool;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        WordChecker wordChecker = new WordChecker();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input text: ");
        String line = scanner.nextLine();
        System.out.println();
        wordChecker.spellchecking(line);

    }
}
