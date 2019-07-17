package au.com.rea.factory;

import java.util.HashMap;
import java.util.Map;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;

/**
 *
 */
public class DirectionFactory {
    private Map<Direction, DirectionVO> directions;

    public DirectionFactory() {
        Coordinates northCoordinates = new Coordinates(0,1);
        Coordinates southCoordinates = new Coordinates(0,-1);
        Coordinates eastCoordinates = new Coordinates(1,0);
        Coordinates westCoordinates = new Coordinates(-1,0);
        directions = new HashMap<>();
        directions.put(Direction.NORTH, new DirectionVO(Direction.NORTH, Direction.EAST, Direction.WEST, northCoordinates));
        directions.put(Direction.SOUTH, new DirectionVO(Direction.SOUTH, Direction.WEST, Direction.EAST, southCoordinates));
        directions.put(Direction.EAST, new DirectionVO(Direction.EAST, Direction.SOUTH, Direction.NORTH, eastCoordinates));
        directions.put(Direction.WEST, new DirectionVO(Direction.WEST, Direction.NORTH, Direction.SOUTH, westCoordinates));
    }

    public Map<Direction, DirectionVO> getDirections() {
        return directions;
    }
}
