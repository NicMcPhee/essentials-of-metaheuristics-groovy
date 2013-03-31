package applications.robocode

import groovy.text.SimpleTemplateEngine

class RobotBuilder {
    def template
    
    def RobotBuilder(String templateFileName) {
        def engine = new SimpleTemplateEngine()
        template = engine.createTemplate(new File(templateFileName))
    }
    
    def buildJavaFile(values) {
        File javaFile = createFile(values['id'])
        writeFile(javaFile, values)
    }

    private File createFile(id) {
        new File('evolved_robots').mkdir()
        def filename = "evolved_robots/Individual_${id}.java"
        File javaFile = new File(filename)
        assert !javaFile.exists()
        javaFile.createNewFile()
        return javaFile
    }

    private writeFile(javaFile, values) {
        def result = template.make(values)
        javaFile << result.toString()
//        javaFile << "public class Individual_${id}\n"
//        javaFile << "eval += (${enemy_energy})\n"
//        javaFile << "eval += (${my_energy})\n"
//        javaFile << "eval += (${angle_diff})\n"
//        javaFile << "eval += (${distance})\n"
    }

}
