package problems

import spock.lang.Specification

class OnesMaxTest extends Specification {
	final NUM_BITS = 10
	def onesMax = new OnesMax(numBits : NUM_BITS)
	
	def "eval count should start at 0"() {
		expect:
		onesMax.evalCount == 0
	}
	
	def "correctly counts number of ones"() {
		expect:
		onesMax.quality(bitstring) == numOnes
		onesMax.evalCount == 1
		
		where:
		bitstring << [[], [0], [1], [0, 1, 0], [1, 1, 0, 1, 1, 0, 0, 1]]
		numOnes << [0, 0, 1, 1, 5]
	}
	
	def "correctly updates eval count"() {
		expect:
		onesMax.quality([0, 0, 0, 1, 1, 0, 1, 0]) == 3
		onesMax.evalCount == 1
		onesMax.quality([1, 0, 1, 0, 0, 0, 0, 1]) == 3
		onesMax.evalCount == 2
	}

	def "create returns an array of zeros"() {
		expect:
		onesMax.create() == [0] * NUM_BITS
	}
	
	def "create takes an argument that specifies how many zeros"() {
		expect:
		onesMax.create(5) == [0] * 5
	}
	
	def "copy does in fact clone"() {
		setup:
		def orig = [1, 0, 1, 0, 0, 0, 0, 1]
		
		when:
		def dup = onesMax.copy(orig)
		
		then:
		dup == orig
		
		when: "we change an element in the copy"
		dup[0] = 0
		
		then: "the original is not changed (i.e., we cloned)"
		dup[0] == 0
		orig[0] == 1
	}
	
	def "terminates when all ones"() {
		expect:
		onesMax.terminate([1] * NUM_BITS)
	}
	
	def "terminates when exceeds max evals, quality computed by terminate"() {
		when:
		onesMax.evalCount = onesMax.maxIterations - 2
		
		then:
		!onesMax.terminate([0]*100)
		
		when:
		onesMax.evalCount = onesMax.maxIterations - 1
		
		then:
		onesMax.terminate([0]*100)

		when:
		onesMax.evalCount = onesMax.maxIterations
		
		then:
		onesMax.terminate([0]*100)
	}
	
	def "terminates when exceeds max evals, quality handed to terminate"() {
		when:
		onesMax.evalCount = onesMax.maxIterations - 1
		
		then:
		!onesMax.terminate([0]*100, 0)
		
		when:
		onesMax.evalCount = onesMax.maxIterations
		
		then:
		onesMax.terminate([0]*100, 0)

		when:
		onesMax.evalCount = onesMax.maxIterations + 1
		
		then:
		onesMax.terminate([0]*100, 0)
	}
	
	def "tweak returns an array of bits of the same length"() {
		given:
		def a = [1, 0, 1, 0, 0, 0, 0, 1]
		
		when:
		def mutatedBits = onesMax.tweak(a)
		
		then:
		mutatedBits.size() == a.size()
		mutatedBits.every { it == 0 || it == 1 }
	}
	
	def "tweak mutates correctly and only when appropriate"() {
		given:
		def a = [1, 0, 1, 0, 0, 0, 0, 1]
		def shouldMutate = [false, false, false, true, true, false, true, false]
		
		when:
		def mutatedBits = onesMax.tweak(a, 1, shouldMutate)
		
		then:
		[a, mutatedBits, shouldMutate].transpose().every { aBit, mBit, s ->
			(aBit == mBit && !s) || (aBit != mBit && s)
		}
	}
	
	def "don't terminate when there's a zero"() {
		expect:
		!onesMax.terminate([1, 1, 1, 1, 1, 0, 1, 1])
	}
	
	def "terminate when we max out evals"() {
		final MAX_ITERATIONS = 10
		def a = [1, 1, 1, 1, 1, 0, 1, 1]
		onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : MAX_ITERATIONS)
		
		expect:
		(MAX_ITERATIONS-1).times {
			assert !onesMax.terminate([1, 1, 1, 1, 1, 0, 1, 1])
		}
		onesMax.terminate([1, 1, 1, 1, 1, 0, 1, 1])
	}
}
