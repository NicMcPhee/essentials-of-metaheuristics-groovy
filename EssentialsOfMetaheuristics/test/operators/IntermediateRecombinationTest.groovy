package operators;
import Java.util.*


import static org.junit.Assert.*;
import spock.lang.*

class IntermediateRecombinationTest extends Specification {
    
    
    def "test output size is the same"() {
        given:
        def bitstring1 = [1, 1, 1, 1]
        def bitstring2 = [1, 1, 1, 1]
        def p = 2
        def IR = new IntermediateRecombination()
        def inBounds = {val ->
            def toReturn = true
            if(val>p || val<(p*-1)){
                toReturn=false
            }
            toReturn
            }
             
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        output[0].size() == output[1].size()
        output[0].size() == bitstring1.size()
    }
    
    
    
    def "test all 1's"() {
        given:
        def bitstring1 = [1, 1, 1, 1, 1, 1]
        def bitstring2 = [1, 1, 1, 1, 1, 1]
        def p = 2
        def IR = new IntermediateRecombination()
        
        def inBounds = {val ->
            def toReturn = true
            if(val>(p*99) || val<(p*-99)){
                toReturn=false
            }
            toReturn
            }
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        output[0] == [1]*6
        output[1] == [1]*6
    }
    
    def "test all 0's"() {
        given:
        def bitstring1 = [0, 0, 0, 0, 0, 0]
        def bitstring2 = [0, 0, 0, 0, 0, 0]
        def p = 2
        def IR = new IntermediateRecombination()
        def inBounds = {val ->
            def toReturn = true
            if(val>(p*99) || val<(p*-99)){
                toReturn=false
            }
            toReturn
            }
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        output[0] == [0]*6
        output[1] == [0]*6
    }
    
    
    def "test 1's and 0's output range with unrealistic inbounds"(){
        given:
        def bitstring1 = [0, 1, 0, 1, 0, 1]
        def bitstring2 = [1, 1, 0, 0, 1, 0]
        def p = 2
        def IR = new IntermediateRecombination()
        def inBounds = {val ->
            def toReturn = true
            if(val>(p*99) || val<(p*-99)){
                toReturn=false
            }
            toReturn
            }
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        def bool = true
        6.times{
            if(!(output[0][it]>=-2 && output[0][it]<=3)){
                println "false"
                bool = false
            }
            if(!(output[1][it]>=(-2*p) && output[1][it]<=3)){
                println "false"
                bool = false
            }
        }
        bool
    }
    
    def "test 1's and 0's output range with inbounds"(){
        given:
        def bitstring1 = [0, 1, 0, 1, 0, 1]
        def bitstring2 = [1, 1, 0, 0, 1, 0]
        def p = 2
        def IR = new IntermediateRecombination()
        def inBounds = {val ->
            def toReturn = true
            if(val>(p*1) || val<(p*-1)){
                toReturn=false
            }
            toReturn
            }
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        def bool = true
        6.times{
            if(!(output[0][it]>=(-2) && output[0][it]<=2)){
                println "false"
                bool = false
            }
            if(!(output[1][it]>=(-2) && output[1][it]<=2)){
                println "false"
                bool = false
            }
        }
        bool
    }
    
    def "test output range with random bounds 10to40"() {
        given:
        def rand = new Random()
        def bitstring1 = [3, 2, 6, 2, 1, 0, 4, 9, 4, 8, 1]
        def bitstring2 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        def p = 2
        def IR = new IntermediateRecombination()
        def r = rand.nextInt(16) + 5
        def inBounds = {val ->
            def toReturn = true
            if(val>(p*r) || val<(p*(-1*r))){
                toReturn=false
            }
            toReturn
            }
        when:
        def output = IR.recombAlg(bitstring1, bitstring2, 2,inBounds )
        then:
        def bool = true
        11.times{
            if(!(output[0][it]>=(-1*p*r) && output[0][it]<=p*r)){
                println "false"
                bool = false
            }
            if(!(output[1][it]>=(-1*p*r) && output[1][it]<=p*r)){
                println "false"
                bool = false
            }
        }
        bool
    }
    
    
}
