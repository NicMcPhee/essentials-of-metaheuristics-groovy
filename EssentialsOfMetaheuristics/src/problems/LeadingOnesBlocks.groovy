package problems

/*
 * This is the same as LeadingOnes, but we divide by a block size b
 * and then take the floor of the result. This means that the system
 * has to find/solve an entire block before it receives any kind of
 * reward via an increase in fitness.
 */
class LeadingOnesBlocks extends BitStringProblem {
	Integer blockSize
	def maximalQuality = { (int) (numBits / blockSize) }
	
	def quality = { a ->
		++evalCount
		Integer numOnes = 0
		while (a[numOnes] == 1) {
			++numOnes
		}
		return (int) (numOnes / blockSize)
	}
	
	String toString() {
		super.toString() + "_" + blockSize
	}
}
