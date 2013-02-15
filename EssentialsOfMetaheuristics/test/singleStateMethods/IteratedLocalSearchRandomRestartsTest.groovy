package singleStateMethods

import problems.HIFF
import problems.OnesMax
import spock.lang.Specification

class IteratedLocalSearchRandomRestartsTest extends Specification {
	def "ILS with Random Restarts returns an answer"() {
		given:
		Integer numberOfBits = 10
		IteratedLocalSearchRandomRestarts ilsRR =
			new IteratedLocalSearchRandomRestarts(timeDistribution : { [2, 3, 5] })
		OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 100)
		
		when:
		def result = ilsRR.maximize(problem)
		
		then:
		result.size() == numberOfBits
	}
}
