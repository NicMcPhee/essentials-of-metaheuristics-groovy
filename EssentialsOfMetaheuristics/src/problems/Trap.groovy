package problems

import groovy.transform.ToString


class Trap extends BitStringProblem {
	def maximalQuality = { numBits + 1 }

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
		def numZeros = a.count(0)
		// If everything is a 1 (i.e., there are no zeros)
		// then the fitness is n+1. Otherwise it's the number
		// of zeros.
		if (numZeros == 0) {
			return a.size() + 1
		} else {
			return numZeros
		}
	}	
}
