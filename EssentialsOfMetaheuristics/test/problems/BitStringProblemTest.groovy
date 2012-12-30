package problems

import spock.lang.Specification

class BitStringProblemTest extends Specification {
	final NUM_BITS = 16
	def bsp = new BitStringProblem(numBits : NUM_BITS)

	def "generateRandomBits returns the right length"() {
		when:
		def bits = bsp.generateRandomBits(NUM_BITS)
		
		then:
		bits.size() == NUM_BITS
	}
	
	def "generateRandomBits returns bits"() {
		when:
		def bits = bsp.generateRandomBits(NUM_BITS)
		
		then:
		bits.every() { it.class == Boolean }
	}
	
	/*
	 * Since we're using the 1/n bit flip rule, the most common number
	 * of "true"s in an array of random bits should be 1.
	 */
	def "generateRandomBits generates a reasonable distribution"() {
		final Integer NUM_TRIALS = 100
		
		given:
		def counts = [:].withDefault { 0 }
		
		when:
		for (i in 0..<NUM_TRIALS) {
			def bits = bsp.generateRandomBits(NUM_BITS)
			Integer numTrues = bits.count(true)
			++counts[numTrues]
		}
		def maxCount = counts.values().max()
		
		then:
		maxCount == counts[0] || maxCount == counts[1]
	}
}
