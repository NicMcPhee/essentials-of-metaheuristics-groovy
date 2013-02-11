package problems

class Rastrigin {
	protected Random rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer numValues = 8
	Float lowerBound = -5.12
	Float upperBound = 5.12
	Float halfMutationRange = 0.5

	def create = { n = numValues ->
		// Makes an array of n random floats between lowerBound and upperBound
		def result = []
		n.times {
			result << rand.nextFloat() * (upperBound - lowerBound) + lowerBound
		}
		return result
	}
	
	def copy = { a -> a.clone() }

	def tweak = { a ->
		a.collect { v ->
			bound(v + (2 * rand.nextFloat() - 1) * halfMutationRange)
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
		return -a.inject(10 * numValues) { s, x ->
			s + x*x - 10*Math.cos(2*Math.PI*x)
		}
	}
	
	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations
	}
	
	String toString() {
		this.class.name.split("\\.")[-1] + "_" + numValues + "_" + maxIterations
	}
}
