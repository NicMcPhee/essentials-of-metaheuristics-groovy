package problems

import groovy.transform.ToString


class OnesMax extends BitStringProblem {
	def maximalQuality = { numBits }

	def quality = { a ->
		++evalCount
		++intermediateEvalCount
		a.count(1)
	}
}
