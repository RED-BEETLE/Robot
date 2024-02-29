import java.util.Objects;

/**
 * A position has two values, x and y, that describe the
 * position in a field.
 *
 * @author Emilia Östensson dv21eon
 */

public class Position
{
    private final int x;
    private final int y;

    /**
     * Constructs a position with set x and y values.
     * @param x The initial x value.
     * @param y The initial y value.
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the current x value.
     * @return The x value.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Gets the current y value.
     * @return The y value.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Gets the position below the current one.
     * @return The position below.
     */
    public Position getPosToSouth()
    {
        return new Position(this.x, this.y + 1);
    }

    /**
     * Gets the position above the current one.
     * @return The position above
     */
    public Position getPosToNorth()
    {
        return new Position(this.x, this.y - 1);
    }

    /**
     * Gets the position to the left of the current one.
     * @return The position to the left.
     */
    public Position getPosToWest()
    {
        return new Position(this.x - 1, this.y);
    }

    /**
     * Gets the position to the right of the current one.
     * @return The position on the right.
     */
    public Position getPosToEast()
    {
        return new Position(this.x + 1, this.y);
    }

    /**
     * Checks if the current position is equal to the position p.
     * This is done using the function hashCode.
     * @param pos The position to be compared with.
     * @return True if equal, otherwise false.
     */
    public boolean equals(Position pos)
    {
        return this.hashCode() == pos.hashCode();
    }

    /**
     * Creates a hash code for the position.
     * @return The integer value of the hash code.
     */
    public int hashCode()
    {
        return Objects.hash(this.x, this.y);
    }
}
