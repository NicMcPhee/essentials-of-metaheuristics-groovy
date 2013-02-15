package populationMethods

import problems.OnesMax
import spock.lang.Specification

class GeneticAlgorithmTest extends Specification {
	def "The GA returns an answer"() {
		given:
		Integer numberOfBits = 32
		GeneticAlgorithm ga = new GeneticAlgorithm(popsize : 20)
		OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 100)

		when:
		def result = ga.maximize(problem)

		then:
		result.size() == numberOfBits
		// This is slightly risky and *could* fail occasionally if we have
		// really bad luck. We probably want to remove it if that starts to
		// be a problem.
		result.sum() > numberOfBits / 2;
	}
}
