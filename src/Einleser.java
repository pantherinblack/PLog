import java.time.LocalDate;
import java.util.Scanner;

public class Einleser {

    public Scanner scanner = new Scanner(System.in);

    public int readInt() {
        int number=-1;
        while (number<0){
            while (!scanner.hasNextInt()) {
                System.out.print("Bitte geben sie ein Int (+) ein: ");
                scanner.next();
            }
            number = scanner.nextInt();
        }
        return number;
    }

    public double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Bitte geben sie eine Double ein: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public String readString() {
        String string = scanner.nextLine();
        return string;
    }

    public char readChar() {
        return scanner.nextLine().charAt(0);
    }

    public boolean readBoolean(){
        while (!scanner.hasNextBoolean()){
            System.out.println("Bitte geben sie ein Boolean ein: ");
            scanner.nextLine();
        }
        return scanner.nextBoolean();
    }

    public boolean readOtherBoolean() {
        String input = null;
        while (input.equalsIgnoreCase("ja") || input.equalsIgnoreCase("nein")) {
            System.out.print("Bitte geben sie ja/nein ein: ");
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("ja")){
            return true;
        } else {
            return false;
        }
    }


    public LocalDate readLocalDate() {
        int year;
        int month;
        int day;
        LocalDate date;

        year = readInt();
        month = readInt();
        day = readInt();
        date = LocalDate.of(year,month,day);

        return date;
    }
}
