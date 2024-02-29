/**
 * A RightHandRuleRobot contains a maze, position and a
 * direction from 0 to 4 where 1 is north, 2 is east exc.
 * The direction is used to know which way the robot is facing
 * and giving us what direction is to the right. The robot
 * moves around a maze by holding it's right "hand" on the
 * wall and moving around the maze. When the robot is created
 * it has the direction 0. The robot first needs to "grab" the
 * wall before it can continue moving.
 *
 * @author Emilia Östensson dv21eon
 */

public class RightHandRuleRobot implements Robot{
    private final Maze maze;
    protected Position position;
    private int direction;

    /**
     * Creates a RightHandRuleRobot using a maze. The robot
     * is placed at start and it's direction set to 0.
     * @param m The maze for the robot to traverse.
     */
    public RightHandRuleRobot(Maze m)
    {
        position =  m.getStart();
        maze = m;
        direction = 0;
    }

    /**
     * The robot moves while holding its right "hand"
     * on the wall. It knows what direction to move in
     * based on the direction it is facing. It always tries to
     * move to the right and if unsuccessful it turns to
     * the left and tries again.
     */
    @Override
    public void move()
    {
        boolean moved = false;

        if(touchesWall()) {
            while(!moved) {

                switch (direction) {
                    case 0:
                        grabWall();
                        moved = true;
                        break;
                    case 1 :
                        if(maze.isMovable(position.getPosToEast())) {
                            position = position.getPosToEast();
                            direction = 2;
                            moved = true;
                        } else{
                            direction = 4;
                        }
                        break;

                    case 2 :
                        if(maze.isMovable(position.getPosToSouth())) {
                            position = position.getPosToSouth();
                            direction = 3;
                            moved = true;
                        } else{
                            direction--;
                        }
                        break;

                    case 3 :
                        if(maze.isMovable(position.getPosToWest())) {
                            position = position.getPosToWest();
                            direction = 4;
                            moved = true;
                        } else{
                            direction--;
                        }
                        break;

                    case 4 :
                        if(maze.isMovable(position.getPosToNorth())) {
                            position = position.getPosToNorth();
                            direction = 1;
                            moved = true;
                        } else{
                            direction--;
                        }
                        break;
                }
            }
        } else {
            System.err.println("The robot has no wall to grab onto and won't move.");
        }
    }

    /**
     * Checks if there is any wall adjacent to the robot
     * in any direction, including diagonal.
     * @return True if it touches any wall, otherwise false.
     */
    private boolean touchesWall()
    {
        int x = position.getX();
        int y = position.getY();
        if(!maze.isMovable(position.getPosToNorth()) || !maze.isMovable(position.getPosToEast())
           || !maze.isMovable(position.getPosToSouth()) || !maze.isMovable(position.getPosToWest())) {
            return true;
        }
        return !maze.isMovable(new Position(x + 1, y - 1)) || !maze.isMovable(new Position(x - 1, y - 1))
            || !maze.isMovable(new Position(x + 1, y + 1)) || !maze.isMovable(new Position(x - 1, y + 1));
    }

    /**
     * Grabs a wall when starting up a robot. The robot
     * wants to have its right "hand" on the wall when
     * beginning to move around the maze. This function
     * Finds the right direction for the robot to start
     * moving in and takes the first step.
     */
    private void grabWall()
    {
        if(!maze.isMovable(position.getPosToNorth()) || !maze.isMovable(new Position(position.getX() - 1, position.getY() - 1))) {
            direction = 3;
        }
        if(!maze.isMovable(position.getPosToWest()) || !maze.isMovable(new Position(position.getX() - 1, position.getY() + 1))) {
            direction = 2;
        }
        if(!maze.isMovable(position.getPosToSouth()) || !maze.isMovable(new Position(position.getX() + 1, position.getY() + 1))) {
            direction = 1;
        }
        if(!maze.isMovable(position.getPosToEast()) || !maze.isMovable(new Position(position.getX() + 1, position.getY() - 1))) {
            direction = 4;
        }
        move();
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
