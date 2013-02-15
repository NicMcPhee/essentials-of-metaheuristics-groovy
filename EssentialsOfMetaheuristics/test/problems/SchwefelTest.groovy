package problems

import spock.lang.Specification

class SchwefelTest extends Specification {
    final numValues = 1
    def schwefel = new Schwefel()

    def "create takes an argument that specifies starting values"() {
        expect:
        schwefel.create(numValues, [Math.PI*Math.PI]) == [Math.PI*Math.PI]
    }

    def "Quality Function Works Correctly"() {
        expect:
        //Since floats are prone to roundoff error, here instead of using == 0 we used a range
        schwefel.quality([Math.PI*Math.PI]) < 0.00000000001 && schwefel.quality([Math.PI*Math.PI]) > -0.00000000001
    }
}