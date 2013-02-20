package operators

import problems.OnesMax
import spock.lang.Specification

class StochasticUniversalSamplingTest extends Specification {
    def "select returns the 'better' option with pop size 2"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better]
        
        when:
        def choice = selector.select(problem, population)
        
        then:
        choice == better
    }

    def "select returns the 'better' option pop size 3"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better, worser]
        
        when:
        def choice = selector.select(problem, population)
        
        then:
        choice == better
    }
    
    def "repeated select eventually returns the best option"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        def middle = [0, 1, 1, 0]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better, middle]
        
        when:
        def choices = (0..<3).collect { selector.select(problem, population) }
        
        then:
        choices.count(better) == 2
        choices.count(middle) == 1
        choices.count(worser) == 0
    }
    
    def "repeated select multiple gens returns the best option several times"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def problem = new OnesMax()
        def better = [1, 1, 1, 1]
        def middle = [0, 1, 1, 0]
        // Since the fitness of all 0's is 0, this can *never* be
        // chosen by fitness proportionate selection.
        def worser = [0, 0, 0, 0]
        def population = [worser, better, middle]
        
        when:
        def choices = (0..<6).collect { selector.select(problem, population) }
        
        then:
        choices.count(better) == 4
        choices.count(middle) == 2
        choices.count(worser) == 0
    }
    
    def "shuffle keeps vectors in sync"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def xs = (0..<10).collect { it }
        def ys = (10..<20).collect { it }
        
        when:
        selector.shuffle(xs, ys)
        
        then:
        10.times { i->
            assert 10 + xs[i] == ys[i]
        }
    }
    
    def "all zeros check converts all zeros to all 1s"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def xs = [0]*10
        
        when:
        selector.allZerosCheck(xs)
        
        then:
        xs == [1]*10
    }
    
    def "all zeros check vector with non-zero values alone"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def xs = [0]*10
        xs[4] = 2
        def ys = xs.clone()
        
        when:
        selector.allZerosCheck(xs)
        
        then:
        xs == ys
    }
    
    def "CDF sums correctly"() {
        given:
        StochasticUniversalSampling selector = new StochasticUniversalSampling()
        def xs = (1..5).collect { it }
        
        when:
        selector.convertToCDF(xs)
        
        then:
        xs == [1, 3, 6, 10, 15]
    }
}
