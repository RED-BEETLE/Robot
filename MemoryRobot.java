import java.util.Stack;

/**
 * A MemoryRobot contains a maze, position, an array
 * of visited positions and a stack containing the
 * path of the robot. The robot traverses the maze
 * by moving to a positions that is currently unvisited.
 * If no unvisited position can be moved to the robot
 * takes a step back in its path using the stack. It
 * continues to move until it reaches the goal or
 * has visited all available positions. It then stops
 * at the start position.
 *
 * @author Emilia Östensson dv21eon
 */
 
public class MemoryRobot implements Robot{
    private final Maze maze;
    protected Position position;
    private final boolean[][] visitedPositions;
    private final Stack<Position> path;

    /**
     * Creates a MemoryRobot using a maze. It is placed
     * at the start position and adds it to the stack.
     * All positions except start are marked as unvisited.
     *
     * @param m The maze for the robot to traverse.
     */
    public MemoryRobot(Maze m) {
        position = m.getStart();
        maze = m;

        visitedPositions = new boolean[m.getNumColumns()][m.getNumRows()];
        for(int i = 0 ; i < m.getNumColumns() ; i++) {
            for(int j = 0 ; j < maze.getNumRows() ; j++) {
                visitedPositions[i][j] = false;  // TODO kolla om detta behövs
            }
        }
        int x = position.getX();
        int y = position.getY();

        visitedPositions[x][y] = true;
        path = new Stack<>();
        path.push(position);
    }

    /**
     * The MemoryRobot moves in an available and unvisited
     * direction. If no such direction exists the robot takes
     * a step back in its path. If it has backed up all the way
     * to start it stops.
     */
    @Override
    public void move() {
        boolean moved = false;

        Position north = position.getPosToNorth();
        if(maze.isMovable(north) && !isVisited(north)) {
            setPosition(north);
            moved = true;
        }
        Position east = position.getPosToEast();
        if(!moved && maze.isMovable(east) && !isVisited(east)) {
            setPosition(east);
            moved = true;
        }
        Position south = position.getPosToSouth();
        if(!moved && maze.isMovable(south) && !isVisited(south)) {
            setPosition(south);
            moved = true;
        }
        Position west = position.getPosToWest();
        if(!moved && maze.isMovable(west) && !isVisited(west)) {
            setPosition(west);
            moved = true;
        }

        if(!moved && !path.isEmpty()) {
            path.pop();
            if(!path.isEmpty()){
                this.position = path.peek();
            } else {
                System.err.println("There is no path to the goal. The robot will stop moving");
            }
        }
    }

    /**
     * Checks if the position p is visited.
     * @param p The position to check.
     * @return True if it is visited, otherwise false.
     */
    private boolean isVisited(Position p) {
        int x = p.getX();
        int y = p.getY();

        return visitedPositions[x][y];
    }

    /**
     * Moves the robot to the position p and adds the
     * position to the path and marks it as visited.
     * @param p The position to move to.
     */
    private void setPosition(Position p) {
        if(maze.isMovable(p)) {
            int x = p.getX();
            int y = p.getY();

            visitedPositions[x][y] = true;
            path.push(p);
            position = p;
        }

    }

    /**
     * Gets the current position.
     * @return The current position.
     */
    @Override
    public Position getPosition()
    {
        return this.position;
    }

    /**
     * Checks if the robot has reached the goal.
     * @return True if it has, otherwise false.
     */
    @Override
    public boolean hasReachedGoal()
    {
        return maze.isGoal(position);
    }

}


