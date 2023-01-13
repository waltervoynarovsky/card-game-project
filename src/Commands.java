import java.util.Scanner;

public class Commands {
    private static Scanner scanner = new Scanner(System.in);


    public String getInput() {
        String input = "";
        boolean loop = true;

        while (loop) {
            input = scanner.nextLine();

            if (!input.isEmpty() && !input.equals("snap")) {
                System.out.println("Just press ENTER or type in 'snap', ok?");
            } else {
                loop = false;
            }
        }


        return input;

    }

    public String restartGame() {
        String restarting = "";
        restarting = scanner.nextLine().toLowerCase();
        return restarting;
    }

    public String getUserName(){
        String name = "";
        name = scanner.nextLine();
        return name;
    }
}

