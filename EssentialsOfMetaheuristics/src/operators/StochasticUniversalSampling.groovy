package operators
import java.util.Random;

class StochasticUniversalSampling {

    def populationVector = []

    def fitnessVector = []

    def index

    def value

    def random = new Random()

    def selectionCount = 0

    def shuffle(vector1, vector2){
        def size = vector1.size()
        (size-1).downto(1) { i ->
            def j = random.nextInt(i)
            def temp1 = vector1[i]
            vector1[i] = vector1[j]
            vector1[j] = temp1
            def temp2 = vector2[i]
            vector2[i] = vector2[j]
            vector2[j] = temp2
        }
    }

    def buildVectors(problem, population){
        fitnessVector = []
        for(person in population){
            fitnessVector.add(problem.quality(person))
        }
        populationVector = []
        for(person in population){
            populationVector.add(person)
        }
    }

    def convertToCDF(vector){
        for (int i = 1; i <= vector.size()-1; i++){
            vector[i] = vector[i] + vector[i-1]
        }
    }

    def allZerosCheck(vector){
        def allZeros = true
        for(i in 0..<vector.size()){
            if (vector[i] != 0){
                allZeros = false
            }
        }
        if (allZeros == true){
            for(j in 0..<vector.size())
                vector[j]=1
        }
    }

    //runs once per generation
    def prepareVectors(problem, population){
        def popSize
        def sum
        buildVectors(problem, population)
        shuffle(fitnessVector, populationVector)
        allZerosCheck(fitnessVector)
        convertToCDF(fitnessVector)
        popSize = populationVector.size()
        sum = fitnessVector[fitnessVector.size()-1]
        //sets value to random number between 0 and the sum
        value = Math.random()*(sum/popSize)
        index = 0
    }

    def select(problem, population){
        if((selectionCount % population.size()) == 0){
            prepareVectors(problem, population)
        }
        selectionCount++
        while (fitnessVector[index] < value){
            index++
        }
        value = value + (fitnessVector[fitnessVector.size()-1])/(populationVector.size())
        return populationVector[index]
    }
}


