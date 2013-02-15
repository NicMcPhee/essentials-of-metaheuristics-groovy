package populationMethods

import problems.OnesMax
import spock.lang.Specification

class MuCommaLambdaESTest extends Specification {
	def "Mu,Lambda ES returns an answer"() {
		given:
		Integer numberOfBits = 32
		MuCommaLambdaES muPlusLambdaES = new MuCommaLambdaES(numChildren : 10)
		OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 100)

		when:
		def result = muPlusLambdaES.maximize(problem)

		then:
		result.size() == numberOfBits
		// This is slightly risky and *could* fail occassionally if we have
		// really bad luck. We probably want to remove it if that starts to
		// be a problem.
		result.sum() > numberOfBits / 2;
	}
}
