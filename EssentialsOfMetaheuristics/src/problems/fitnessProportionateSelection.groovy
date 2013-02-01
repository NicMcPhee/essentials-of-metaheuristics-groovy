package problems
import java.util.Random

class fitnessProportionateSelection{
    def ran = new Random()
    
    def randomPop(popsize){
        def population =[]
        for(i in 0..popsize){
            population[i]= ran.nextInt(10)
        }
    }
    def sumFitness(){
        def sum
        
        
    }
}