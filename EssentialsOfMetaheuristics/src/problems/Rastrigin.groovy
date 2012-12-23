package problems

class Rastrigin {
	protected Random rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer numValues = 8
	Float lowerBound = -5.12
	Float upperBound = 5.12

	def create = { n = numValues ->
		// Makes an array of n random floats between lowerBound and upperBound
		def result = []
		for (i in 0..<n) {
			result << rand.nextFloat() * (upperBound - lowerBound) + lowerBound
		}
		return result
	}
	
	def copy = { a -> a.clone() }

	/*
	 * I need to look into how Sean handles tweak on floats. For the moment
	 * I'll just add a random value between -0.5 and +0.5.
	 */
	def tweak = { a ->
		(0..<a.size()).collect { i ->
			bound(a[i] + rand.nextFloat() - 0.5)
		}
	}

	private bound(x) {
		if (x < lowerBound) {
			lowerBound
		} else if (x > upperBound) {
			upperBound
		} else {
			x
		}
	}

	// Minimize f(<x_i>) = 10n + sum { x_i^2 - 10 cos(2 \pi x_i) }, x \in [-5.12, 5.12],
	// so we'll negate the result since everything we have maximizes.
	def quality = { a ->
		++evalCount
		Float result = 10 * numValues;
		// I bet I can do this with something like collect or inject.
		for (x in a) {
			result += x*x - 10 * Math.cos(2 * Math.PI * x)
		}
		return -result
	}
	
	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations
	}
	
	String toString() {
		this.class.name.split("\\.")[-1] + "_" + numValues + "_" + maxIterations
	}
}
