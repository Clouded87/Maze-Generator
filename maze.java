import java.util.ArrayList;

public class maze {
    private int width;
    private int height;
    private int[] location;
    private ArrayList<Integer> squares = new ArrayList<>();
    private int numofsquares;
    private ArrayList<Integer> validmoves = new ArrayList<>();

    public maze(int width, int height) {
        this.width = width;
        this.height = height;
        location = new int[]{(int)(Math.random() * this.width), (int)(Math.random() * this.height)};
        numofsquares = this.width * this.height;
        for (int i = 0; i < this.width*this.height; i++) {
            squares.add(0);
        }
        squares.set(getsquarenum(location), 0);
    }

    public void generatepath() {
        // Placeholder implementation — generation logic to be implemented later.
        for (int i = 0; i < numofsquares; i++) {
            //0 left, 1 right, 2 up, 3 down
            if (squares.get(getsquarenum(new int[] {location[0]-1, location[1]})) == 0) {
                validmoves.set(0, 1);
            } else {
                validmoves.set(0, 0);
            } if (squares.get(getsquarenum(new int[] {location[0]+1, location[1]})) == 0) {
                validmoves.set(1, 1);
            } else {
                validmoves.set(1, 0);
            } if (squares.get(getsquarenum(new int[] {location[0], location[1]-1})) == 0) {
                validmoves.set(2, 1);
            } else {
                validmoves.set(2, 0);
            } if (squares.get(getsquarenum(new int[] {location[0], location[1]+1})) == 0) {
                validmoves.set(3, 1);
            } else {
                validmoves.set(3, 0);
            }
        }
    }

    public int getsquarenum(int[] givenlocation) {
        System.out.println("Current square number: " + ((givenlocation[1] * this.width) + givenlocation[0]));
        return (givenlocation[1] * this.width) + givenlocation[0];
    }

    public void printmaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(squares.get(i * width + j) + " ");
            }
            System.out.println();
        }
    }
}