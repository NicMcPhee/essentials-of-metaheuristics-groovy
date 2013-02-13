package populationMethods
import java.util.Random;

class StochasticUniversalSampling {


    //global index
    def index = 0

    //global value
    def value

    //random number generator
    def randomInt = new Random()

    //method for shuffling vectors (shuffles both p and f to preserve order)
    def shuffle(vector1, vector2){
        def i

        def j

        //Length of vector1 and vector2 should be the same since each individual has a corresponding fitness.
        def vectLength = vector1.length

        for (i = vectLength; i >= 2; i--){
            j = randomInt.next(vectLength-i)+i
            def temp1 = vector1.i
            vector1.i = vector1.j
            vector1.j = temp1
            def temp2 = vector2.i
            vector2.i = vector2.j
            vector2.j = temp2
        }

    }
    //main Stochastic Universal Sampling method
    def sus(populationSize, populationVector, fitnessVector){
        for(int b = 0; b < populationSize; b++){
            shuffle(populationVector,fitnessVector)
            def allZeros = true
            for(int i = 0; i <= fitnessVector.length; i++){
                if (fitnessVector[i] != 0){
                    allZeros = false
                }
            }
            if (allZeros == true){
                for(int x = 0; x < fitnessVector.length; x++)
                fitnessVector[x]=1
            }
            for (int a = 1; a < fitnessVector.length; a++){
                fitnessVector[a] = fitnessVector[a] + fitnessVector[a-1]
            }
            value = randomInt.next(fitnessVector[fitnessVector.length-1]/populationSize)
        } 
            while (fitnessVector[index] < value){
            index++
        }
            value = value + fitnessVector[fitnessVector.length-1]/populationSize
            return populationVector[index]
    }

}
