package populationMethods
import java.util.Random;

class StochasticUniversalSampling {

    def populationVector = []
    
    
    def fitnessVector = []
    
    def index
    
    def individualsProduced = 0
    
    def value
    
    def random = new Random()
    
    def generationCount = 0

    //method for shuffling vectors 
    def shuffle(vector){

        def size = vector.size()

        for (def i = size; i >= 2; i--){
            def j = (random.nextInt(i-1)+1)
            def temp = vector[i]
            vector[i] = vector[j]
            vector[j] = temp
        }
    }
    
    def buildFitnessVector(problem, population){
        for(person in population){
            fitnessVector.add(problem.quality(person))
            
        }
    }
    
    def convertToCDF(vector){
        for (int i = 2; i <= vector.size(); i++){
            vector[i] = vector[i] + vector[i-1]
            }
        }
    
    def allZerosCheck(vector){
        def allZeros
        for(fitness in vector){
            if (fitness != 0){
                allZeros = false
            }
        }
        if (allZeros == true){
            for(fitness in vector)
            fitness=1
        }
    }
    
    
    //runs once per generation
    def prepareVectors(problem, population){
        def popSize = population.size()
        buildFitnessVector(problem, population)
        shuffle(fitnessVector)
        allZerosCheck(fitnessVector)
        convertToCDF(fitnessVector)
        //sets value to random number between 0 and the sum
        value = Math.random()*(fitnessVector[fitnessVector.size()]/popSize)
        generationCount++
        }
    
    def select(problem, population){
        if((population.size() % generationCount) == 0){
             prepareVectors(problem, population)   
        }
         
            while (fitnessVector[index] < value){
            index++
        }
            value = value + fitnessVector[fitnessVector.size()]/population.size()
            return populationVector[index]
    }

}
