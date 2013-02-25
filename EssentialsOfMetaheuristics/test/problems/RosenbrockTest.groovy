package problems

import spock.lang.Specification

class RosenbrockTest extends Specification {
    final numValues = 4
    def rosenbrock = new Rosenbrock()

    def "create takes an argument that specifies starting values"() {
        expect:
        rosenbrock.create(numValues, [1, 2, 3, -4]) == [1, 2, 3, -4]
    }

    def "Quality Function Works Correctly"() {
        expect:
        rosenbrock.quality([1, 2, 3, -4]) == -17105
    }
}