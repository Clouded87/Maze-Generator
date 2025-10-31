import java.util.ArrayList;

public class maze {
    private int width;
    private int height;
    private int[][][] states;
    private int[] location;
    private ArrayList<Integer> previousmoves;

    public maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.states = new int[width][height][3];
        this.location = new int[]{(int) Math.random() * width, (int) Math.random() * height};
        this.previousmoves = new ArrayList<>();
        // in states[x][y][z] if z = 0 then cell, if z = 1 then top wall, if z = 2 then left wall
        // when k=the value of [x][y][0] if k = 0 then unvisited cell, if k = 1 then visited cell
        // when k=the value of [x][y][1] or [x][y][2] if k = -1 then invalid cell, if k = 0 then placed wall, if k = 1 then no wall
        definestates();
    }

    public void definestates() {
        // define all cells as unvisited
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                states[i][j][0] = 0;
            }
        }
        // define outer walls as invalid
        for (int j = 0; j < height; j++) {
            states[0][j][2] = -1;
        }
        // define all top walls as placed
        for (int i = 1; i < width-1; i++) {
            for (int j = 0; j < height; j++) {
                states[i+1][j][0] = 0;
            }
        }
        // define outer walls as invalid
        for (int j = 0; j < width; j++) {
            states[j][0][1] = -1;
        }
        // define all left walls as placed
        for (int i = 1; i < height-1; i++) {
            for (int j = 0; j < width; j++) {
                states[j][i+1][0] = 0;
            }
        }
    }

    public void generatemaze() {
        states[location[0]][location[1]][0] = 1; // mark starting cell as visited
        ArrayList<Integer> validlocations = new ArrayList<>();
        // 0 = left, 1 = right, 2 = down, 3 = up
        if (location[0]-1 >= 0 && states[location[0]-1][location[1]][0] == 0) {
            validlocations.add(0);
        } if (location[0]+1 < width && states[location[0]+1][location[1]][0] == 0) {
            validlocations.add(1);
        } if (location[1]-1 >= 0 && states[location[0]][location[1]-1][0] == 0) {
            validlocations.add(2);
        } if (location[1]+1 < height && states[location[0]][location[1]+1][0] == 0) {
            validlocations.add(3);
        }
        if (validlocations.size() == 0) {
            tracesteps();
        } else {
            int randomindex = (int) (Math.random() * validlocations.size());
            int direction = validlocations.get(randomindex);
            domove(direction);
        }
    }

    public void domove(int direction) {
        previousmoves.add(direction);
        if (direction == 0) {
            // move left
            states[location[0]][location[1]][2] = 1; // remove left wall
            location[0] -= 1;
        } else if (direction == 1) {
            // move right
            states[location[0]+1][location[1]][2] = 1; // remove left wall of right cell
            location[0] += 1;
        } else if (direction == 2) {
            // move up
            states[location[0]][location[1]][1] = 1; // remove top wall
            location[1] -= 1;
        } else if (direction == 3) {
            // move down
            states[location[0]][location[1]+1][1] = 1; // remove top wall of upper cell
            location[1] += 1;
        }
        states[location[0]][location[1]][0] = 1; // mark new cell as visited
        generatemaze();
    }

    public void tracesteps() {
        if (previousmoves.size() == 0) {
            return;
        }
        int lastmove = previousmoves.remove(previousmoves.size() - 1);
        if (lastmove == 0) {
            // move right
            location[0] += 1;
        } else if (lastmove == 1) {
            // move left
            location[0] -= 1;
        } else if (lastmove == 2) {
            // move down
            location[1] += 1;
        } else if (lastmove == 3) {
            // move up
            location[1] -= 1;
        }
        generatemaze();
    }

    public void printmaze() {
        for (int i = 0; i < height; i++) {
            // print top walls
            for (int j = 0; j < width; j++) {
                if (states[j][i][1] == -1) {
                    System.out.print("---");
                } else if (states[j][i][1] == 0) {
                    System.out.print("---");
                } else {
                    System.out.print("   ");
                }
                System.out.print("-");
            }
            System.out.print("-");
            System.out.println();

            // print left walls and cells
            for (int j = 0; j < width; j++) {
                if (states[j][i][2] == -1) {
                    System.out.print("| ");
                } else if (states[j][i][2] == 0) {
                    System.out.print("| ");
                } else {
                    System.out.print("  ");
                }
                System.out.print("n ");
            }
            System.out.print("|");
            System.out.println();
        }
        for (int j = 0; j < width; j++) {
            System.out.print("--");
            System.out.print("--");
        }
        System.out.print("-");
        System.out.println();
    }
}