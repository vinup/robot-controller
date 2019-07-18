# Robot Controller
This application has been developed in Java8 for REA. it has been built using Gradle as the build tool. 

To run the application, follow the instructions below. 
1. Clone the application to your local machine. 
2. Go to the application directory.
### Running through Gradle 
1. Run the below commands based on your operating system.


        Linux/MAC:   bash gradlew run --args=distributions/test-data/valid-input-file.txt 
        
        Windows:     gradlew run --args=distributions/test-data/valid-input-file.txt 
        
### Running through embedded jar 
A fat jar has been added to the repository in the distributions directory. 
1. From the project directory run the below command
   
   
           java -jar distributions/robot-controller-1.0.jar <input-file-location>
           Example: java -jar distributions/robot-controller-1.0.jar distributions/test-data/valid-input-file.txt
           
        
## Assumptions
1. Robot state is refreshed for each run and only one file is processed per run. 

## Test Coverage 

Test coverage can be found under distributions/jacoco-reports.
index.html can be open with any browser and then eventually navigated packages and individual classes.  




