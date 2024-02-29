/**
 * A test program for the classes Position, maze.maze and RandomRobot.
 * The test program creates a robot using a maze given in the
 * command line and simulates the robot moving in the maze.
 * This is done until the robot reached the goal.
 * 
 * @author Emilia Östensson dv21eon
 */ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RobotTest {
    public static void main(String[] args){
        try {
            Scanner in = new Scanner(new File(args[0]));
            Maze m = new Maze(in);
//            RandomRobot robot = new RandomRobot(m);
//            MemoryRobot robot = new MemoryRobot(m);
            RightHandRuleRobot robot = new RightHandRuleRobot(m);

            while(!robot.hasReachedGoal()) {
                String maze = m.toString(robot.getPosition());
                System.out.println(maze);
                robot.move();
            }
            String maze = m.toString(robot.getPosition());
            System.out.println(maze);

        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: Error when creating the scanner.");
        }

    }
}
