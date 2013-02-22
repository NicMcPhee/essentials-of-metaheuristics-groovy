package problems

import spock.lang.Specification
import static spock.util.matcher.HamcrestMatchers.closeTo

class GriewankTest extends Specification {
    final numValues = 1
    def griewank = new Griewank()

    def "create takes an argument that specifies starting values"() {
        expect:
        griewank.create(numValues, [Math.PI*Math.PI/4]) == [Math.PI*Math.PI/4]
    }

    def "Quality Function Works Correctly"() {
        expect:
        //Since floats are prone to roundoff error, here instead of using == 0 we used a range
        griewank.quality([Math.PI*Math.PI/4]) < -0.22031012493691826 + 0.00000001 && griewank.quality([Math.PI*Math.PI]) > -0.22031012493691826 - 0.00000001
    }
}

