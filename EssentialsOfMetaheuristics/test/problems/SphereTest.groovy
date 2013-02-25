package problems

import spock.lang.Specification

class SphereTest extends Specification {
    final numValues = 4
    def  sphere = new Sphere()

    def "create takes an argument that specifies starting values"() {
        expect:
        sphere.create(numValues, [1, 2, 3, -4]) == [1, 2, 3, -4]
    }

    def "Quality Function Works Correctly"() {
        expect:
        sphere.quality([1, 2, 3, -4]) == -30
    }
}