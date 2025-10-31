import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter maze width:");
        int x = scanner.nextInt();
        System.out.println("Enter maze height:");
        maze myMaze = new maze(x, scanner.nextInt());
        myMaze.generatemaze();
        myMaze.printmaze();
        scanner.close();
    }
}