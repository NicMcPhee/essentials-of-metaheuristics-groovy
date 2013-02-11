package operators

import static org.junit.Assert.*
import spock.lang.Specification
import operators.Crossovers

class CrossoverTests extends Specification {
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
		def ones = [1, 1, 1, 1, 1]
		when:
		def children = Crossovers.onePointCrossover(zeroes, ones, 1)
		
		then:
		children == [[0, 1, 1, 1, 1], [1, 0, 0, 0, 0]]
		
	}
	def "One point crossover shouldn't change either parent"() {
		given:
		def fatherClone = father.clone()
		def motherClone = mother.clone()
		def crossovers = new Crossovers()
		
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
		def crossovers = new Crossovers()
		
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
		def crossovers = new Crossovers()
		
		when:
		def children = crossovers.uniformCrossover(father, mother)
		
		then:
		father == fatherClone
		mother == motherClone
	}
    
    def"total number of ones and zeroes should not change"(){
    
        expect:
        countOnesAndZeroes(father) == countOnesAndZeroes(mother)
    }
    
    def"One Point Crossovers don't change the total number of ones and zeroes"(){
       def crossovers = new Crossovers()
       def children = crossovers.onePointCrossover(father, mother)
       def fchild = children[0]
       def mchild = children[1]       
       expect:
       countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)            
    }
    def"Two Point Crossovers don't change the total number of ones and zeroes"(){
        def crossovers = new Crossovers()
        def children = crossovers.twoPointCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)
     }
    def"Uniform Crossovers don't change the total number of ones and zeroes"(){
        def crossovers = new Crossovers()
        def children = crossovers.uniformCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        countOnesAndZeroes(fchild+mchild) == countOnesAndZeroes(father+mother)
     }
    
}
