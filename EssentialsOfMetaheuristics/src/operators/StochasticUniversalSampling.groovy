package operators
import java.util.Random;

class StochasticUniversalSampling {

    def populationVector = []
    
    def fitnessVector = []
    
    def index
     
    def value
    
    def random = new Random()
    
    def generationCount = 0
   
    def shuffle(vector1, vector2){
        def size = vector1.size()
        for (i in 1..<size){       
           def j = (random.nextInt(i))        
            def temp1 = vector1[i]
            def temp2 = vector2[i]        
            vector1[i] = vector1[j]       
            vector1[j] = temp1  
            vector2[i] = vector2[j]
            vector2[j] = temp2
        }       
    }
     
    def buildVectors(problem, population){
        for(person in population){
            fitnessVector.add(problem.quality(person))
        }
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
        popSize = populationVector.size()-1
        sum = fitnessVector[fitnessVector.size()-1]
        //sets value to random number between 0 and the sum
        value = Math.random()*(sum/popSize)
        generationCount++
        }
    
    def select(problem, population){
        if((generationCount % population.size()) == 0){
             prepareVectors(problem, population)   
        }
         
            while (fitnessVector[index] < value){
            index++
        }
            value = value + (fitnessVector[fitnessVector.size()-1])/(populationVector.size()-1)
            return populationVector[index]
    }
 
/*main for testing
    public static void main(String[] args) {
        
        
        def testVector1 = []
        def testVector2 = []
        for(i in 0 ..10){
            testVector1 += i
            testVector2 += i
        }
        
        def testVector3 = []
        for(i in 0..10){
            testVector3[i] = 0
        }
        
        def testVector4 = []
        for(i in 0..5){
            testVector4 += i
        }
          
        
        StochasticUniversalSampling sus = new StochasticUniversalSampling()
        System.out.println(testVector1)
        System.out.println(testVector2)
        sus.shuffle(testVector1, testVector2)
        System.out.println(testVector1)
        System.out.println(testVector2)
        System.out.println(testVector3)
        
        sus.allZerosCheck(testVector3)
        System.out.println(testVector3)
        
        sus.allZerosCheck(testVector1)
        System.out.println(testVector1)
        
        System.out.println(testVector4)
        sus.convertToCDF(testVector4)
        System.out.println(testVector4)
        
        System.out.println(Math.random()*((testVector4[testVector4.size()-1])/(testVector4.size()-1)))
    }
    */

}


