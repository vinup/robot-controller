package au.com.rea.domain;

/**
 * Domain class for Robot representing it's coordinates and direction.
 */
public class Robot {
    private Coordinates coordinates;
    private DirectionVO directionVO;

    public Robot(Coordinates coordinates, DirectionVO directionVO) {
        this.coordinates = coordinates;
        this.directionVO = directionVO;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public DirectionVO getDirectionVO() {
        return directionVO;
    }

    public void setDirectionVO(DirectionVO directionVO) {
        this.directionVO = directionVO;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(coordinates.getXCoordinate())
            .append(",")
            .append(coordinates.getYCoordinate())
            .append(",")
            .append(directionVO.getDirection())
            .toString();
    }
}
