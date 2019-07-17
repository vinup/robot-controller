package au.com.rea.validator;

import au.com.rea.domain.Coordinates;

/**
 *
 */
public class CoordinatesValidator {
    private static final int MAX_X_COORDINATE = 5;
    private static final int MIN_X_COORDINATE = 0;
    private static final int MAX_Y_COORDINATE = 5;
    private static final int MIN_Y_COORDINATE = 0;

    public static boolean validate(Coordinates coordinates) {
        return validateXCoordinate(coordinates.getXCoordinate())
            && validateYCoordinate(coordinates.getYCoordinate());
    }

    private static boolean validateXCoordinate(int xCoordinate) {
        return xCoordinate >= MIN_X_COORDINATE && xCoordinate <= MAX_X_COORDINATE;

    }

    private static boolean validateYCoordinate(int yCoordinate) {
        return yCoordinate >= MIN_Y_COORDINATE && yCoordinate <= MAX_Y_COORDINATE;
    }

}
