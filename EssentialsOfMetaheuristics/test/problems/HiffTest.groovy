package problems

import spock.lang.Specification

class HiffTest extends Specification {
	def hiff = new HIFF()
	
	def "eval count should start at 0"() {
		expect:
		hiff.evalCount == 0
	}
	
	def "correctly counts number of ones"() {
		expect:
		hiff.quality(bitstring) == hiffQuality
		hiff.evalCount == 1
		
		where:
		bitstring << [[0, 0, 0, 0], [0, 0, 0, 1], [0, 0, 1, 0], [0, 0, 1, 1],
					  [0, 1, 0, 0], [0, 1, 0, 1], [0, 1, 1, 0], [0, 1, 1, 1],
					  [1, 0, 0, 0], [1, 0, 0, 1], [1, 0, 1, 0], [1, 0, 1, 1],
					  [1, 1, 0, 0], [1, 1, 0, 1], [1, 1, 1, 0], [1, 1, 1, 1]]
		hiffQuality << [12, 6, 6, 8, 6, 4, 4, 6, 6, 4, 4, 6, 8, 6, 6, 12]
	}
	
	def "maximalQuality is correct for various sized HIFF problems"() {
		expect:
		new HIFF(numBits : nBits).maximalQuality() == maxQuality
		
		where:
		nBits << [4, 8, 16, 32, 64]
		maxQuality << [4*3, 8*4, 16*5, 32*6, 64*7]
	}
}
