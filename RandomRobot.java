import java.util.Random;

/**
 * A RandomRobot contains a maze and two positions, one for the
 * current position and one for the previous one. The
 * RandomRobot traverses a maze by always taking a step in
 * a random direction.
 *
 * @author Emilia Östensson dv21eon
 */

public class RandomRobot implements Robot {
    private final Maze maze;
    private Position position;
    private Position previousPosition;

    /**
     * Constructs a robot using a maze. The robot is
     * placed at the start position with the previous
     * position also at start.
     * @param m The maze for the robot to traverse.
     */
    public RandomRobot(Maze m)
    {
        Position start = m.getStart();
        position = new Position(start.getX(), start.getY());
        maze = m;
        this.previousPosition = new Position(start.getX(), start.getY());

    }

    /**
     * Moves the robot in a random direction. The robot first checks what
     * directions are movable and then chooses one on random. If no
     * positions are movable it goes back to the previous position.
     */
    @Override
    public void move()
    {
        int movable = 0;
        int north = 0;
        int east = 0;
        int south = 0;
        int west = 0;

        Position northPos = position.getPosToNorth();
        if(maze.isMovable(northPos) && !northPos.equals(previousPosition)) {
            movable++;
            north = movable;
        }
        Position eastPos = position.getPosToEast();
        if(maze.isMovable(eastPos) && !eastPos.equals(previousPosition)) {
            movable++;
            east = movable;
        }
        Position southPos = position.getPosToSouth();
        if (maze.isMovable(southPos) && !southPos.equals(previousPosition)) {
            movable++;
            south = movable;
        }
        Position westPos = position.getPosToWest();
        if (maze.isMovable(westPos) && !westPos.equals(previousPosition)) {
            movable++;
            west = movable;
        }

        if(movable == 0) {
            Position temp = position;
            position = previousPosition;
            previousPosition = temp;
        }else {
            Random rand = new Random();
            int direction = rand.nextInt(movable) + 1;

            if(direction == north){
                setPosition(northPos);
            }
            if(direction == east) {
                setPosition(eastPos);
            }
            if(direction == south) {
                setPosition(southPos);
            }
            if(direction == west) {
                setPosition(westPos);
            }
        }

    }
    /**
     * Sets the previous position to the current one
     * and the current position to p.
     * @param p The position to move to.
     */
    private void setPosition(Position p)
    {
        if(maze.isMovable(p)){
            this.previousPosition = this.position;
            this.position = p;
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

