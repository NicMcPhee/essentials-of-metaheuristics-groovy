package hillClimbing

class HillClimber {
	// Happily this ended up being an almost direct copy from Sean's book.
	def maximize(quality, create, copy, tweak, terminate) {
		def s = create()
		while (!terminate(s)) {
			def r = tweak(copy(s))
			if (quality(r) > quality(s)) {
				s = r
			}
		}
		return s
	}
	
	// I bet there's a way I can do this as a "one-liner" using some
	// nifty Groovy tool.
	static arrayOfZeros(int n) {
		def result = []
		for (int i=0; i<n; ++i) {
			result << 0
		}
		return result
	}
	
	// I should move all this into a OnesMax class rather than plop it here.
	public static void main(String[] args) {
		def rand = new java.util.Random()
		def count = 0
		def maxIterations = 1000

		def quality = { a -> a.count(1) }
		def create = { arrayOfZeros(100) }
		def copy = { a -> a.clone() }
		def tweak = { a -> 
			a.collect {
				// I'm using the common 1/N rule for mutation, i.e.,
				// have the mutation probability be 1/N where N is the
				// length of the bistring.
				if (rand.nextInt(a.size()) == 0) {
					1-it
				} else {
					it
				}
			}
		}
		def terminate = {
			++count
			count >= maxIterations
		}
		
		def climber = new HillClimber()
		def result = climber.maximize(quality, create, copy, tweak, terminate)
		System.out.println(result);
		System.out.println(quality(result));
	}
}
