import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PositionTest {
    Position p1 = new Position(0,0);
    Position p2 = new Position(0,0);
    Position p3 = new Position(2,3);

    @Test
      public void shouldGetSameX(){
        Assertions.assertEquals(2, p3.getX());
    }

    @Test
    public void shouldGetSameY(){
        Assertions.assertEquals(3, p3.getY());
    }

    @Test
    public void shouldGetDifferentX(){
        Assertions.assertNotEquals(3,p3.getX());
    }

    @Test
    public void shouldBeEqual(){
        Assertions.assertTrue(p1.equals(p2));
    }

    @Test
    public void shouldNotBeEqual(){
        Assertions.assertFalse(p1.equals(p3));
    }

    @Test
    public void getNorth(){
        Position north = new Position(2,2);
        Position n = p3.getPosToNorth();

        Assertions.assertTrue(n.equals(north));
    }

    @Test
    public void getWest(){
        Position west = new Position(1,3);
        Position w = p3.getPosToWest();

        Assertions.assertTrue(w.equals(west));
    }
}