package applications.robocode

import groovy.text.SimpleTemplateEngine

// I'm adding a comment via GitHub - how weird is that?!?

/*
 * This assumes that there is a copy of Robocode in your home directory,
 * and that it has been configured (via the GUI) to be able to load robot
 * files from the evolved_robots directory in this project.
 */
class BattleRunner {
    def userHome = System.getProperty("user.home")
    def robotDirectory = "evolved_robots"
    def robotDirectoryAbsolute = new File(robotDirectory).absolutePath
    def template

    def BattleRunner(String templateFileName) {
        def engine = new SimpleTemplateEngine()
        template = engine.createTemplate(new File(templateFileName))
    }

    def buildBattleFile(id, bestSoFars) {
        File battleFile = new File("${robotDirectory}/evolve.battle")
        battleFile.delete()
        battleFile.createNewFile()
        def otherRobots = ""
        bestSoFars.each { r ->
            otherRobots += ",evolved.Individual_${r['id']}"
        }
        def map = ["id" : id, "otherRobots" : otherRobots]
        def result = template.make(map)
        battleFile << result.toString()
    }

    def runBattle(id) {
        linkJarFile(id)
        File battleFile = new File("${robotDirectory}/evolve.battle")
        def command = "${userHome}/robocode/robocode.sh -battle ${battleFile.absolutePath} -nodisplay"
        def proc = command.execute(null, new File(robotDirectory))
        proc.waitFor()
        assert proc.exitValue() == 0
        assert proc.err.text.equals("")
        def lines = proc.in.text.split("\n")
        def result = -1
        lines.each { line ->
//            def pattern = ~/evolved\.Individual_${id}.+(\d+)\s+\d+\s+\d+\s*&/
//            def pattern = ~/(\d+)\w+:\s+evolved\.Individual_${id}/
//            def pattern = ~/evolved\.Individual_${id}\s+\d+\s+\((\d+)%\)/
            def pattern = ~/evolved\.Individual_${id}\s+\d+\s+\(\d+%\)\s+\d+\s+\d+\s+(\d+)/
            if (line =~ /%/) {
                println line
            }
            def m = (line =~ pattern)
            if (m) {
                result = Integer.parseInt(m[0][1])
//                println "Score = ${result}"
            }
        }
        if (result >= 0) {
            return result
        } else {
            throw new RuntimeException("Didn't find score for evolved robot")
        }
    }
    
    def linkJarFile(id) {
        def robotDir = new File("${userHome}/robocode/robots/")
        def command = "ln -s ${robotDirectoryAbsolute}/Individual_${id}.jar ."
        def proc = command.execute(null, robotDir)
        proc.waitFor()
        assert proc.err.text.equals("")
        assert proc.exitValue() == 0
    }
}
