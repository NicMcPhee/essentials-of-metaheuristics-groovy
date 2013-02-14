package problems

/*
 * For more on the HIFF problem see, e.g., "A theoretical analysis of the HIFF problem"
 * (http://dl.acm.org/citation.cfm?id=1068201).
 */
class HIFF extends BitStringProblem {
	def create = { n = numBits ->
		// Makes an array of random bits.
		def result = []
		n.times {
			result << rand.nextInt(2)
		}
		return result
	}
	
	def quality = { a ->
		++evalCount
		evalHiff(a)
	}
	
	private evalHiff(a) {
		if (a.size() == 1) {
			return 1
		}
		Integer midPoint = a.size() / 2
		Integer result = evalHiff(a[0..<midPoint]) + evalHiff(a[midPoint..-1])
		Integer numZeros = a.count(0)
		if (numZeros == 0 || numZeros == a.size()) {
			result += a.size()
		}
		return result
	}
	
	def maximalQuality = {
		numBits * ((int) (Math.log(numBits)/Math.log(2)) + 1)
	}
}
