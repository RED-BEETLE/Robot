import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeTest {

    Scanner in;
    {
        try {
            in = new Scanner(new File("src/maze.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    Maze m = new Maze(in);


    @Test
    public void shouldBeWall() {
        Assertions.assertFalse((m.isMovable(new Position(2,0))));
    }

    @Test
    public void shouldBeMovable() {
        Assertions.assertTrue(m.isMovable(new Position(3,1)));
    }

    @Test
    public void shouldBeOutsideMaze(){
        Assertions.assertFalse(m.isMovable(new Position(2,-1)));
    }

    @Test
    public void shouldNotBeGoal(){
        Position p = new Position(2,3);
        Assertions.assertFalse(m.isGoal(p));
    }

    @Test
    public void shouldBeGoal(){
        Position p = new Position(8,6);
        Assertions.assertTrue(m.isGoal(p));
    }

    @Test
    public void shouldBeStart(){
        Position p = new Position(3,0);
        Assertions.assertTrue(p.equals(m.getStart()));
    }

    @Test
    public void getColumns(){
        Assertions.assertEquals(15, m.getNumColumns());
    }

    @Test
    public void getRows(){
        Assertions.assertEquals(8, m.getNumRows());
    }
}