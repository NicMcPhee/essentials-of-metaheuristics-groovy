package singleStateMethods;

import problems.OnesMax
import spock.lang.Specification;

class RandomSearchTest extends Specification {
	def "Random search returns an answer"() {
		given:
		Integer numberOfBits = 10
		RandomSearch rs = new RandomSearch()
		OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 10)
		
		when:
		def result = rs.maximize(problem)
		
		then:
		result.size() == numberOfBits
	}
}
