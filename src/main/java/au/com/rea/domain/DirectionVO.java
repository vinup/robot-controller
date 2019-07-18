package au.com.rea.domain;

/**
 * Direction Value Object holding the direction, coordinates and link to the next directions.
 */
public class DirectionVO {
    private Direction direction;
    private Direction clockwiseNext;
    private Direction antiClockwiseNext;
    private Coordinates coordinates;

    public DirectionVO(Direction direction, Direction clockwiseNext, Direction antiClockwiseNext, Coordinates coordinates) {
        this.direction = direction;
        this.clockwiseNext = clockwiseNext;
        this.antiClockwiseNext = antiClockwiseNext;
        this.coordinates = coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getClockwiseNext() {
        return clockwiseNext;
    }

    public Direction getAntiClockwiseNext() {
        return antiClockwiseNext;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
