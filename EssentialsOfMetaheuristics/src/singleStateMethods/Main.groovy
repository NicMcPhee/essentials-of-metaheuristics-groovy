package singleStateMethods

import problems.OnesMax

/*
 * TODO: MAKE THIS A LOOP TO RUN 30 TIMES AND PRINT RESULTS IN R-STYLE OUTPUT
 * SO WE CAN COMPARE APPROACHES FOR STATISTICALLY SIGNIFICANT DIFFERENCES!
 */
class Main {
	static main(args) {
		def onesMax = new OnesMax(numBits : 100, maxIterations : 250)
		def climber = new SteepestAscentHillClimberWithReplacement(problem : onesMax, numGradientSamples : 10)
		def result = climber.maximize()
		println "Best string: $result"
		println "Best fitness: ${onesMax.quality(result)}"
		println "Number of evaluations: ${onesMax.evalCount}"
	}
}
