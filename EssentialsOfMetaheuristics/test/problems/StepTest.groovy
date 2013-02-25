package problems

import spock.lang.Specification

class StepTest extends Specification {
    final numValues = 4
    def  step = new Step()

    def "create takes an argument that specifies starting values"() {
        expect:
        step.create(numValues, [1.5, 1.3, 1.7, -1.0]) == [1.5, 1.3, 1.7, -1.0]
    }

    def "Sum of the floors should be 26"() {
        expect:
        step.quality([1.5, 1.3, 1.7, -1.0]) == -26
    }
}