public class maze {
    private int width;
    private int height;
    private int[][][] states;

    public maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.states = new int[width][height][3];
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

    }

    public void printmaze() {
        for (int i = 0; i < height; i++) {
            // print top walls
            for (int j = 0; j < width; j++) {
                if (states[j][i][1] == -1) {
                    System.out.print("--");
                } else if (states[j][i][1] == 0) {
                    System.out.print("--");
                } else {
                    System.out.print(" ");
                }
                System.out.print("--");
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
                    System.out.print(" ");
                }
                System.out.print("  ");
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