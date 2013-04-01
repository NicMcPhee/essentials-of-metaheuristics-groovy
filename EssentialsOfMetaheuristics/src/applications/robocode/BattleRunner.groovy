package applications.robocode

import groovy.text.SimpleTemplateEngine


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

    def buildBattleFile(id) {
        File battleFile = new File("${robotDirectory}/evolve.battle")
        battleFile.delete()
        battleFile.createNewFile()
        def result = template.make(["id" : id])
        battleFile << result.toString()
    }

    def runBattle(id) {
        linkClassFiles(id)
        File battleFile = new File("${robotDirectory}/evolve.battle")
        def command = "${userHome}/robocode/robocode.sh -battle ${battleFile.absolutePath} -nodisplay"
        def proc = command.execute(null, new File(robotDirectory))
        proc.waitFor()
        assert proc.exitValue() == 0
        assert proc.err.text.equals("")
        def lines = proc.in.text.split("\n")
        lines.each { line ->
            def m = line =~ /evolved\.Individual_${id}\s+(\d+)/
            if (m) {
                return m[0][1]
            }
        }
    }
    
    def linkClassFiles(id) {
        def robotDir = new File("${userHome}/robocode/robots/evolved/")
        robotDir.mkdir()
        def command = "ln -s ${robotDirectoryAbsolute}/evolved/Individual_${id}.class ${robotDirectoryAbsolute}/evolved/Individual_${id}\$MicroEnemy.class ."
        def proc = command.execute(null, robotDir)
        proc.waitFor()
        assert proc.exitValue() == 0
        assert proc.err.text.equals("")
    }
}
