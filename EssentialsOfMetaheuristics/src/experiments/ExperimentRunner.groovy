package experiments

import problems.HIFF
import problems.LeadingOnes
import problems.LeadingOnesBlocks
import problems.OnesMax
import problems.Trap
import singleStateMethods.HillClimber
import singleStateMethods.SteepestAscentHillClimber
import singleStateMethods.SteepestAscentHillClimberWithReplacement

class ExperimentRunner {
	
	static runExperiment(searchers, problems, numRuns = 30) {
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
			new HillClimber(),
			new SteepestAscentHillClimber(numGradientSamples : 1),
			new SteepestAscentHillClimber(numGradientSamples : 2),
			new SteepestAscentHillClimber(numGradientSamples : 4),
			new SteepestAscentHillClimberWithReplacement(numGradientSamples : 1),
			new SteepestAscentHillClimberWithReplacement(numGradientSamples : 2),
			new SteepestAscentHillClimberWithReplacement(numGradientSamples : 4),
			new SteepestAscentHillClimberWithReplacement(numGradientSamples : 8),
			new SteepestAscentHillClimberWithReplacement(numGradientSamples : 16)
		]
		def problems = [
//			new OnesMax(numBits : 100, maxIterations : 250),
//			new LeadingOnes(numBits : 100, maxIterations : 1000),
//			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 1), 
//			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 2), 
//			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 4),
//			new Trap(numBits : 4, maxIterations : 1000), 
//			new Trap(numBits : 8, maxIterations : 1000), 
//			new Trap(numBits : 16, maxIterations : 1000), 
			new HIFF(numBits : 4, maxIterations : 1000),
			new HIFF(numBits : 8, maxIterations : 1000),
			new HIFF(numBits : 16, maxIterations : 1000),
			new HIFF(numBits : 32, maxIterations : 1000)
		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}

}
