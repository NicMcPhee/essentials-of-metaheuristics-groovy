package singleStateMethods

import problems.OnesMax
import spock.lang.Specification

class HillClimberWithRandomRestartsTest extends Specification {
    def "Hill climber with random restarts returns an answer"() {
        given:
        Integer numberOfBits = 10
        HillClimberWithRandomRestarts hcrr = new HillClimberWithRandomRestarts()
        OnesMax problem = new OnesMax(numBits : numberOfBits, maxIterations : 100)
        
        when:
        def result = hcrr.maximize(problem)
        
        then:
        result.size() == numberOfBits
    }
}
