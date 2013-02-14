package operators

import java.math.MathContext;

import spock.lang.Specification

class UniformCrossoverTest extends Specification {
	def uniformCrossover = new UniformCrossover()
	
	def "Size of the output is the same as the input"() {
		given:
			def bitstring1 = [0, 0, 0, 0]
			def bitstring2 = [1, 1, 1, 1]
		when:
			def output = uniformCrossover.crossover(bitstring1, bitstring2)
		then:
			output[0].size() == output[1].size()
			output[0].size() == bitstring1.size()
	}
	
	def "Number of 1s and 0s in the output is correct"() {
		given:
			def bitstring1 = [0, 0, 0, 0]
			def bitstring2 = [1, 1, 1, 1]
		when:
			def output = uniformCrossover.crossover(bitstring1, bitstring2)
		then:
			output[0].count(0) == output[1].count(1)
			output[0].count(1) == output[1].count(0)
			output[1].count(0) == output[0].count(1)
			output[1].count(1) == output[0].count(0)
	}
	
	def "Output strings are mirror images of each other"() {
		given:
			def bitstring1 = [0, 0, 0, 0]
			def bitstring2 = [1, 1, 1, 1]
		when:
			def output = uniformCrossover.crossover(bitstring1, bitstring2)
		then:
			for (i in 0 ..< bitstring1.size()) {
				if (output[0][i] == 0) {
					output[1][i] == 1
				} else {
					output[1][i] == 0
				}
			}
	}
}