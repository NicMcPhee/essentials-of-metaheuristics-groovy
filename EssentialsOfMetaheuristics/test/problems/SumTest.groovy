package problems

import spock.lang.Specification

class SumTest extends Specification {
    def sum = new Sum()

    def "Quality Function Works Correctly"() {
        expect:
        sum.quality([1, 2, 3, -4]) == -2
    }
}
