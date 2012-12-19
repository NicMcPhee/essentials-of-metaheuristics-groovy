package singleStateMethods

import problems.OnesMax

class Main {
	static main(args) {
		def onesMax = new OnesMax()
		def climber = new HillClimber(problem : onesMax)
		def result = climber.maximize()
		System.out.println(result);
		System.out.println(onesMax.quality(result));
		System.out.println(onesMax.evalCount);
	}
}
