package experiments

import applications.robocode.RoboCodeProblem
import populationMethods.MuPlusLambdaES
import problems.HIFF
import problems.LeadingOnes
import problems.LeadingOnesBlocks
import problems.OnesMax
import problems.Trap
import singleStateMethods.HillClimber
import singleStateMethods.SteepestAscentHillClimber
import singleStateMethods.SteepestAscentHillClimberWithReplacement

class RoboCodeExperimentRunner {

    static runExperiment(searchers, problems, numRuns = 1) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0..<numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
                }
            }
        }
    }

    static main(args) {
        def searchers = [
            new MuPlusLambdaES(numParents: 10, numChildren: 20)
        ]
        def problems = [
            new RoboCodeProblem(maxEvalCount: 500)
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}
