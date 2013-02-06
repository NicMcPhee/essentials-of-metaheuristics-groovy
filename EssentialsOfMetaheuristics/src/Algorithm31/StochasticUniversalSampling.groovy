package Algorithm31
import java.util.Random;

class StochasticUniversalSampling {

    //population size
    def popSize

    //population vector
    def popVector

    //fitness vector (corresponds to individuals in p)
    def fitVector

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
    def sus(popSize, popVector, fitVector){
        for(int b = 0; b < popSize; b++){
            shuffle(popVector,fitVector)
            def allZeros = true
            for(int i = 0; i <= fitVector.length; i++){
                if (fitVector[i] != 0){
                    allZeros = false
                }
            }
            if (allZeros == true){
                for(int x = 0; x < fitVector.length; x++)
                fitVector[x]=1
            }
            for (int a = 1; a < fitVector.length; a++){
                fitVector[a] = fitVector[a] + fitVector[a-1]
            }
            value = randomInt.next(fitVector[fitVector.length-1]/popSize)
        } 
            while (fitVector[index] < value){
            index++
        }
            value = value + fitVector[fitVector.length-1]/popSize
            return popVector[index]
    }

}
