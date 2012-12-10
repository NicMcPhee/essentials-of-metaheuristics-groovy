package problems

class OnesMax {
	private rand = new java.util.Random()
	private count = 0
	def maxIterations = 1000
	def numBits = 1000

	def quality = { a -> a.count(1) }
	
	// I bet there's a way I can do this as a "one-liner" using some
	// nifty Groovy tool.
	private arrayOfZeros(int n) {
		def result = []
		for (int i=0; i<n; ++i) {
			result << 0
		}
		return result
	}
	
	def create = { arrayOfZeros(numBits) }
	
	def copy = { a -> a.clone() }

	def tweak = { a ->
		a.collect { bit ->
			// I'm using the common 1/N rule for mutation, i.e.,
			// have the mutation probability be 1/N where N is the
			// length of the bistring.
			if (rand.nextInt(a.size()) == 0) {
				1-bit
			} else {
				bit
			}
		}
	}
	
	def terminate = { a ->
		++count
		count >= maxIterations || a.every { it == 1 }
	}
}
