package problems

import spock.lang.Specification

class RastriginTest extends Specification {
    final numValues = 4
    def rastrigin = new Rastrigin()

    def "create takes an argument that specifies starting values"() {
        expect:
        rastrigin.create(numValues, [1, 2, 3, -4]) == [1, 2, 3, -4]
    }

    def "Quality Function Works Correctly"() {
        expect:
        rastrigin.quality([1, 2, 3, -4]) == -70
    }

}
