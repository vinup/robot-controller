package au.com.rea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotControllerApplicationTest {
    @Mock
    private CommandFileProcessor commandFileProcessor;


    @Test
    public void testRobotController() throws Exception {
        String inputFile = "src/test/resources/testInputCommands1.txt";
        RobotControllerApplication.setCommandFileProcessor(commandFileProcessor);
        RobotControllerApplication.main(new String[] {inputFile});
        verify(commandFileProcessor).processCommandFile(inputFile);
    }
}