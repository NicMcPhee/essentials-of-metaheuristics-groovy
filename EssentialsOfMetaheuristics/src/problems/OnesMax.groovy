package problems

class OnesMax {
	private rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer numBits = 1000

	def quality = { a ->
		++evalCount
		a.count(1)
	}
	
	// I bet there's a way I can do this as a "one-liner" using some
	// nifty Groovy tool.
	private arrayOfZeros(int n) {
		def result = []
		for (int i=0; i<n; ++i) {
			result << 0
		}
		return result
	}
	
	def create = { n = numBits ->
		arrayOfZeros(n) 
	}
	
	def copy = { a -> a.clone() }

	def tweak = { a, randomBits = null ->
		if (randomBits == null) {
			randomBits = (0..<a.size()).collect {
				// I'm using the common 1/N rule for mutation, i.e.,
				// have the mutation probability be 1/N where N is the
				// length of the bit string.
				rand.nextInt(a.size()) == 0
			}
		}
		(0..<a.size()).collect { i ->
			if (randomBits[i]) {
				1-a[i]
			} else {
				a[i]
			}
		}
	}
	
	def terminate = { a ->
		evalCount >= maxIterations || a.every { it == 1 }
	}
}
