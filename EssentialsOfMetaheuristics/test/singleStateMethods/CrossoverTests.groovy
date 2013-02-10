package singleStateMethods

import static org.junit.Assert.*
import spock.lang.Specification
import singleStateMethods.Crossovers

class CrossoverTests extends Specification {
    def frequencies(arr){
       def ones = arr.findAll { i -> i == 1}
       def zeroes = arr.findAll { i -> i == 0}
       
       ones + zeroes
    }
    def father = [1, 0, 1, 0, 1, 0, 1, 0]
    def mother = [0, 1, 0, 1, 0, 1, 0, 1]
	
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
        frequencies(father) == frequencies(mother)
    }
    
    def"One Point Crossovers don't change the total number of ones and zeroes"(){
       def crossovers = new Crossovers()
       def children = crossovers.onePointCrossover(father, mother)
       def fchild = children[0]
       def mchild = children[1]       
       expect:
       frequencies(fchild+mchild) == frequencies(father+mother)            
    }
    def"Two Point Crossovers don't change the total number of ones and zeroes"(){
        def crossovers = new Crossovers()
        def children = crossovers.twoPointCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        frequencies(fchild+mchild) == frequencies(father+mother)
     }
    def"Uniform Crossovers don't change the total number of ones and zeroes"(){
        def crossovers = new Crossovers()
        def children = crossovers.uniformCrossover(father, mother)
        def fchild = children[0]
        def mchild = children[1]
        expect:
        frequencies(fchild+mchild) == frequencies(father+mother)
     }
    
}
