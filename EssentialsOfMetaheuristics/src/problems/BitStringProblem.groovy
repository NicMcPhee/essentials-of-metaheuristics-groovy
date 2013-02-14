package problems

class BitStringProblem {
	protected rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer numBits = 1000

	def create = { n = numBits ->
		// Makes an array of n zeros.
		[0]*n
	}
	
	def random = { n = numBits -> 
		(0..<numBits).collect {
				rand.nextInt(2)
			}
	}
	
	def copy = { a -> a.clone() }

	/*
	 * Having this take an option array of bits works, but probably isn't
	 * super efficient, especially for large bit strings, as we need to allocate
	 * memory for and construct the full set of bits, which is really not
	 * necessary. An alternative would be to send in a closure that will return
	 * a 0 or 1 every time it's called. The default closure could return random
	 * bits, while for testing we could send in a closure that advances through
	 * an fixed array of bits. For now, however, this works, so I'm going to leave
	 * it alone and move on.
	 */
	def tweak = { a, randomBits = null ->
		if (randomBits == null) {
			randomBits = generateRandomBits(a.size())
		}
		(0..<a.size()).collect { i ->
			if (randomBits[i]) {
				1-a[i]
			} else {
				a[i]
			}
		}
	}

	def generateRandomBits(Integer numBits) {
		def randomBits = (0..<numBits).collect {
				// I'm using the common 1/N rule for mutation, i.e.,
				// have the mutation probability be 1/N where N is the
				// length of the bit string.
				rand.nextInt(numBits) == 0
			}
		return randomBits
	}
	
	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations || q == maximalQuality()
	}
	
	String toString() {
		this.class.name.split("\\.")[-1] + "_" + numBits + "_" + maxIterations
	}
}
