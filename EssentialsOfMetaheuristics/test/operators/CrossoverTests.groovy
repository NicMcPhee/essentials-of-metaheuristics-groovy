package operators

import static org.junit.Assert.*
import spock.lang.Specification
import operators.Crossovers

class CrossoverTests extends Specification {
    Crossovers crossovers = new Crossovers()
	def countOnesAndZeroes(arr){
       def ones = arr.findAll { i -> i == 1}
       def zeroes = arr.findAll { i -> i == 0}    
       ones + zeroes
    }
    def father = [1, 0, 1, 0, 1, 0, 1, 0]
    def mother = [0, 1, 0, 1, 0, 1, 0, 1]
	
	def "Testing onepoint swap"(){
		given:
		def zeroes = [0, 0, 0, 0, 0]
		def onez = [1, 1, 1, 1, 1]
		when:
		def children = crossovers.onePointCrossover(zeroes, onez, 1)
		
		then:
		children == [[0, 1, 1, 1, 1], [1, 0, 0, 0, 0]]
		
	}
	def "One point crossover shouldn't change either parent"() {
		given:
		def fatherClone = father.clone()
		def motherClone = mother.clone()
		
		when:
		def children = crossovers.onePointCrossover(father, mother)
		
		then:
		father == fatherClone
		mother == motherClone
	}
    
	def "Two point crossover shouldn't change either parent"() {
		given:
		def fatherClone = father.clone()
		def motherClone = mother.clone()
		
		when:
		def children = crossovers.twoPointCrossover(father, mother)
		
		then:
		father == fatherClone
		mother == motherClone
	}
    
	def "Uniform point crossover shouldn't change either parent"() {
		given:
		def fatherClone = father.clone()
		def motherClone = mother.clone()
		
		when:
		def children = crossovers.uniformCrossover(father, mother)
		
		then:
		father == fatherClone
		mother == motherClone
	}
        
    def"One Point Crossovers don't change the total number of ones and zeroes"(){
       def children = crossovers.onePointCrossover(father, mother)
       def fchild = children[0]
       def mchild = children[1]       
       expect:
       countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)            
    }
    def"Two Point Crossovers don't change the total number of ones and zeroes"(){
        def children = crossovers.twoPointCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)
     }
    def"Uniform Crossovers don't change the total number of ones and zeroes"(){
        def children = crossovers.uniformCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)
     }
	def "Checking for off by one errors"(){
		given:
		def zeroes = [0, 0, 0, 0, 0]
		def onez = [1, 1, 1, 1, 1]
		when:
		def onePointChildren0 = crossovers.onePointCrossover(zeroes, onez, 0)
		def onePointChildren1 = crossovers.onePointCrossover(zeroes, onez, onez.size)
		def twoPointChildren0 = crossovers.twoPointCrossover(zeroes, onez, 0, 0)
		def twoPointChildren1 = crossovers.twoPointCrossover(zeroes, onez, onez.size, zeroes.size)
		def uniformChildren0 = crossovers.uniformCrossover(zeroes, onez, 1)
		then:
		onePointChildren0 == [[1, 1, 1, 1, 1], [0, 0, 0, 0, 0]]
		onePointChildren1 == [[0, 0, 0, 0, 0], [1, 1, 1, 1, 1]]
		twoPointChildren0 == [[0, 0, 0, 0, 0], [1, 1, 1, 1, 1]]
		twoPointChildren1 == [[0, 0, 0, 0, 0], [1, 1, 1, 1, 1]]
		uniformChildren0 == [[1, 1, 1, 1, 1], [0, 0, 0, 0, 0]]
	}
    
}
