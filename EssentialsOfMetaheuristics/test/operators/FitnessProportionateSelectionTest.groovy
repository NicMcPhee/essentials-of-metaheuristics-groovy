package operators

import problems.OnesMax
import spock.lang.Specification

class FitnessProportionateSelectionTest extends Specification {
    def "select returns the 'better' option with pop size 2"() {
        given:
        FitnessProportionateSelection ts = new FitnessProportionateSelection()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better]
        
        when:
        def choice = ts.select(problem, population)
        
        then:
        choice == better
    }

    def "select returns the 'better' option pop size 3"() {
        given:
        FitnessProportionateSelection ts = new FitnessProportionateSelection()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better, worser]
        
        when:
        def choice = ts.select(problem, population)
        
        then:
        choice == better
    }
}
