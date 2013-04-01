package applications.robocode

import groovy.text.SimpleTemplateEngine

class RobotBuilder {
    def template
    def robotDirectory = "evolved_robots"
	def robotPackage = "evolved"
    
    def RobotBuilder(String templateFileName) {
        def engine = new SimpleTemplateEngine()
        template = engine.createTemplate(new File(templateFileName))
    }
    
    def buildClassFile(values) {
        def javaFileName = buildJavaFile(values)
        def command = "javac -cp ../lib/robocode.jar ${robotPackage}/${javaFileName}"
        def proc = command.execute(null, new File(robotDirectory))
        proc.waitFor()
//        println "return code: ${proc.exitValue()}"
//        println "stderr: ${proc.err.text}"
//        println "stdout: ${proc.in.text}"
    }
    
    def buildJavaFile(values) {
        def javaFileName = makeJavaFileName(values)
        File javaFile = createFile(javaFileName)
        writeFile(javaFile, values)
        return javaFileName
    }
    
    def makeJavaFileName(values) {
        def id = values['id']
        def filename = "Individual_${id}.java"
    }

    private File createFile(javaFileName) {
        new File(robotDirectory).mkdir()
		new File("${robotDirectory}/${robotPackage}").mkdir()
        File javaFile = new File("${robotDirectory}/${robotPackage}/${javaFileName}")
        assert !javaFile.exists()
        javaFile.createNewFile()
        return javaFile
    }

    private writeFile(javaFile, values) {
        def result = template.make(values)
        javaFile << result.toString()
    }

}
