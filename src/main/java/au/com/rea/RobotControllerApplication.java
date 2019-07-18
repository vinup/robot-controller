package au.com.rea;

import au.com.rea.exception.RobotControllerException;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotControllerApplication {
    private static CommandFileProcessor commandFileProcessor;
    private static Logger logger = LoggerFactory.getLogger(RobotControllerApplication.class);
    private static String USAGE_GRADLE = "<bash> gradlew run --args= <input file location>";
    private static String USAGE_JAR = "java -jar  robot-controller-1.0.jar <input file location>";


    public static void main(String[] args) {
        if (!ArrayUtils.isEmpty(args) && args.length == 1) {
            if (commandFileProcessor == null) {
                commandFileProcessor = new CommandFileProcessor();
            }
            try {
                commandFileProcessor.processCommandFile(args[0]);

            } catch (RobotControllerException rcEx) {
                System.out.println(rcEx.getMessage());
            }
        } else {
            System.out.println("Invalid arguments for the ROBOT Controller, please run the application as below!");
            System.out.println("Running through Gradle: " + USAGE_GRADLE);
            System.out.println("Running through embedded jar: " + USAGE_JAR);
        }
    }

    protected static void setCommandFileProcessor(CommandFileProcessor commandFileProcessor) {
        RobotControllerApplication.commandFileProcessor = commandFileProcessor;
    }
}
