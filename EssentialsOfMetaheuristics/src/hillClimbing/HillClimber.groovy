package hillClimbing

import problems.OnesMax

class HillClimber {	
	def problem
	
	// Happily this ended up being an almost direct copy from Sean's book.
	def maximize() {
		def s = problem.create()
		while (!problem.terminate(s)) {
			def r = problem.tweak(problem.copy(s))
			if (problem.quality(r) > problem.quality(s)) {
				s = r
			}
		}
		return s
	}
	
	// I should move all this into a OnesMax class rather than plop it here.
	public static void main(String[] args) {
		def onesMax = new OnesMax()
		def climber = new HillClimber(problem : onesMax)
		def result = climber.maximize()
		System.out.println(result);
		System.out.println(onesMax.quality(result));
		System.out.println(onesMax.count);
	}
}
