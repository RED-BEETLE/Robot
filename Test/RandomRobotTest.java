import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class RandomRobotTest {
    Scanner in;
    {
        try {
            in = new Scanner(new File("src/maze.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    Maze m = new Maze(in);

    RandomRobot robot = new RandomRobot(m);

    @Test
    public void getRightPosition(){
        Position s = new Position(3,0);
        Assertions.assertTrue(s.equals(robot.getPosition()));
    }

    @Test
    public void robotIsAtStart(){
        Position r = robot.getPosition();
        Position s = m.getStart();

        Assertions.assertTrue(r.equals(s));
    }

    @Test
    public void shouldMove(){
        Position s = m.getStart();
        robot.move();
        Position r = robot.getPosition();

        Assertions.assertFalse(r.equals(s));
    }

    @Test
    public void shouldBeAtNextPos(){
        robot.move();
        Position next = new Position(3,1);
        Assertions.assertTrue(next.equals(robot.getPosition()));
    }

}