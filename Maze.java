import java.util.ArrayList;

/**
 * A maze contains an array list of an array list that
 * contains the maze. A position containing the start
 * position. The max amount of rows and columns. And an
 * array list containing the amount of columns for each
 * row.
 *
 * @author Emilia Östensson dv21eon
 */
public class Maze {

    private final ArrayList<ArrayList<Character>> maze;
    private Position start;
    private final int columns;
    private final int rows;
    private final ArrayList<Integer> colsForEachRow;

    /**
     * Constructs a maze by reading from a file.
     * An exception is thrown is the maze lacks vital
     * parts such as a start position and at least
     * one goal.
     * @param inFile The file containing the maze
     */
    public Maze(java.util.Scanner inFile)
    {
        this.maze = new ArrayList<>();
        this.colsForEachRow = new ArrayList<>();
        int row = 0;
        int cols = 0;
        boolean hasStart = false;
        boolean hasGoal = false;

        while(inFile.hasNextLine()) {
            if(maze.size() <= row) {
                this.maze.add(new ArrayList<>());
            }
            String input = inFile.nextLine();
            int size = input.length();
            this.colsForEachRow.add(size);

            if(size > cols) {
                cols = size;
            }

            for(int i = 0 ; i < size ; i ++) {
                char character = input.charAt(i);
                this.maze.get(row).add(character);
                if(character == 'S') {
                    start = new Position(i, row);
                    hasStart = true;
                }
                if(character == 'G') {
                    hasGoal = true;
                }
            }
            row++;

        }
        this.columns = cols;
        this.rows = row;

        try {
            if(!hasGoal || !hasStart) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.err.println("The maze file is missing necessary parts, such as a goal and a start.");
        }

    }

    /**
     * Checks if it is possible to move to the position p.
     * @param p The position to check.
     * @return True if the move is possible, otherwise false.
     */
    public boolean isMovable(Position p)
    {
        int x = p.getX();
        int y = p.getY();
        if(x < 0 || y < 0 || x >= this.colsForEachRow.get(y) || y >= getNumRows()){
            return false;
        }
        char c = maze.get(y).get(x);
        return c == ' ' || c == 'G' || c == 'S';
    }

    /**
     * Checks if the position is at the goal of the maze.
     * @param p The position to be checked.
     * @return True if p is at the goal, otherwise false.
     */
    public boolean isGoal(Position p)
    {
        int x = p.getX();
        int y = p.getY();
        char c = maze.get(y).get(x);
        return c == 'G';
    }

    /**
     * Gets the position for the start of the maze.
     * @return The start position.
     */
    public Position getStart()
    {
        return start;
    }

    /**
     * Gets the max amount of columns in the maze.
     * @return The max amount of columns.
     */
    public int getNumColumns()
    {
        return columns;
    }

    /**
     * Gets the max amount of rows in the maze.
     * @return The max amount of rows.
     */
    public int getNumRows() { return rows; }

    /**
     * Turns the maze into a string. This method is used for
     * testing the maze.
     * @param p The current position for the robot.
     * @return A string containing the maze.
     */
    public String toString(Position p)
    {
        int col;
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < rows ; i++) {
            col = this.colsForEachRow.get(i);
            for(int j = 0 ; j < col ; j++) {
                if(p.equals(new Position(j, i))) {
                    s.append('R');
                } else {
                    s.append(this.maze.get(i).get(j));
                }
            }
            s.append('\n');
        }
        return s.toString();

    }
}
