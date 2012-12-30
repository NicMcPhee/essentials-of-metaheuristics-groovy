package problems

class LeadingOnes extends BitStringProblem {
	def maximalQuality = { numBits }

	/*
	 * The fitness here is the length of the longest prefix of all ones.
	 */
	def quality = { a ->
		++evalCount
		Integer numOnes = 0
		while (a[numOnes] == 1) {
			++numOnes
		}
		return numOnes
	}
}
