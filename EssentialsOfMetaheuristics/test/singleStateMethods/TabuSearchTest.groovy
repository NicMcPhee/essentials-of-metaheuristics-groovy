package singleStateMethods

import problems.OnesMax
import spock.lang.Specification

class TabuSearchTest extends Specification {
	def "Tabu search returns an answer"() {
		given:
		Integer numberOfBits = 10
		TabuSearch tabuSearch = new TabuSearch()
		OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 10)
		
		when:
		def result = tabuSearch.maximize(problem)
		
		then:
		result.size() == numberOfBits
	}
}
