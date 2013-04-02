package applications.robocode

import java.util.jar.JarFile
import spock.lang.Specification

class TestClassCreation extends Specification {
    /* 
     * id : an id used in the generation of the name of the class.
     * enemy_energy : the coefficient for the enemy's energy
     * my_energy : the coefficient for our energy
     * angle_diff : the coefficient for the different in angles between us and the point and then and the point
     * distance : the coefficient for the distance between the point and the enemy
     */
    def id
    def enemy_energy
    def my_energy
    def angle_diff
    def distance
    def robotBuilder

    def setup() {
        Random random = new Random()
        id = random.nextInt(1000000)
        enemy_energy = random.nextFloat() * 100
        my_energy = random.nextFloat() * 100
        angle_diff = random.nextFloat() * 100
        distance = random.nextFloat() * 100
        robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
    }

    /*
     * Here we want to make sure that given some data, we can
     * use the templating tools to create a file containing
     * the Java source for a robot.
     */
    def "Confirm that we can create a Java source file for an individual"() {
        given:
        def values = ["id" : id, "enemy_energy" : enemy_energy, "my_energy" : my_energy, "angle_diff" : angle_diff, "distance" : distance]

        when:
        robotBuilder.buildJavaFile(values)

        then:
        confirmJavaFileExists()
        
        cleanup:
        removeJavaFile()
    }

    /*
     * Make sure that given some data, we can create
     * a file containing the Java source for a robot, which we can then
     * compile into a Java class file.
     */
    def "Confirm that we can create a Java class file for an individual"() {
        given:
        def values = ["id" : id, "enemy_energy" : enemy_energy, "my_energy" : my_energy, "angle_diff" : angle_diff, "distance" : distance]

        when:
        robotBuilder.buildClassFile(values)

        then:
        confirmJavaFileExists()
        confirmClassFileExists()
        
        cleanup:
        removeJavaFile()
        removeClassFile()
    }

    /*
     * Make sure that given some data, we can create
     * a jar file containing the Java source and the 
     * compiled Java class file(s) for a robot.
     */
    def "Confirm that we can create a jar file for an individual"() {
        given:
        def values = ["id" : id, "enemy_energy" : enemy_energy, "my_energy" : my_energy, "angle_diff" : angle_diff, "distance" : distance]

        when:
        robotBuilder.buildJarFile(values)

        then:
        confirmJavaFileExists()
        confirmClassFileExists()
        confirmJarFileExists()
        
        cleanup:
        removeJavaFile()
        removeClassFile()
        removePropertiesFile()
    }

    def confirmJavaFileExists() {
        File file = new File("evolved_robots/evolved/Individual_${id}.java")
        def contents = file.readLines()
        def interestingLines = contents.findAll { line ->
            (line.indexOf("public class") >= 0) || (line.indexOf("eval += ") >= 0)
        }
        // There's actually a sixth matching line because of the MicroEnemy inner class
        assert interestingLines.size() == 6
        assert interestingLines[0].indexOf("Individual_${id}") >= 0
        assert interestingLines[1].indexOf("eval += (${enemy_energy})") >= 0
        assert interestingLines[2].indexOf("eval += (${my_energy})") >= 0
        assert interestingLines[3].indexOf("eval += (${angle_diff})") >= 0
        assert interestingLines[4].indexOf("eval += (${distance})") >= 0
        return true
    }

    def confirmClassFileExists() {
        File file = new File("evolved_robots/evolved/Individual_${id}.class")
        assert file.exists()
        return true
    }
    
    def confirmJarFileExists() {
        File file = new File("evolved_robots/Individual_${id}.jar")
        assert file.exists()
        def entryNames = new JarFile(file).entries().collect { it.name }
        def targets = ["evolved/Individual_${id}.class", 
            "evolved/Individual${id}\$MicroEnemy.class", 
            "evolved/Individual_${id}.java",
            "evolved/Individual_${id}.properties"]
        entryNames.containsAll(targets)
        return true
    }
    
    def removeJavaFile() {
        new File("evolved_robots/evolved/Individual_${id}.java").delete()
    }

    def removeClassFile() {
        new File("evolved_robots/evolved/Individual_${id}.class").delete()
        new File("evolved_robots/evolved/Individual_${id}\$MicroEnemy.class").delete()
    }
    
    def removePropertiesFile() {
        new File("evolved_robots/evolved/Individual_${id}.properties").delete()
    }
}
