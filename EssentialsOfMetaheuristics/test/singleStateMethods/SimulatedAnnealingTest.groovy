package singleStateMethods;

import problems.OnesMax
import static org.junit.Assert.*;
import spock.lang.Specification;

class SimulatedAnnealingTest extends Specification{
    
    
    def "test output size" (){
        given:
        def NUM_BITS = 10
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 10
        
        
        expect:
        simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax).size() == 10
        true
    }
    
    def "OnesMax Size0" (){
        given:
        def NUM_BITS = 0
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 10
        
        
        expect:
        simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax) == []
        true    
    }
    
    def "OnesMax size 3"(){
        given:
        def NUM_BITS = 3
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 1000
        
        expect:
        simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax) == [1]*NUM_BITS
    }
    
    def "OnesMax size10 Large t"(){
        given:
        def NUM_BITS = 10
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 10000
        
        expect:
        simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax) == [1]*NUM_BITS
    }
    
    def "OnesMax size10 small t"(){
        given:
        def NUM_BITS = 10
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 5
        
        expect:
        simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax) != [1]*NUM_BITS
    }
    
    //Since SimulatedAnnealing is not a very good solution for OnesMax
    // It is really hard for it to find the optimal solution on large problems.
    def "OnesMax LargeProblem" (){
        given:
        def NUM_BITS = 100
        def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
        def simAnneal = new SimulatedAnnealing()
        def t = 1000
        def result=simAnneal.annealingAlg(t, {x-> x-1}, NUM_BITS, onesMax)
        
        expect:
        result != [1]*NUM_BITS
    }
        
    
}
