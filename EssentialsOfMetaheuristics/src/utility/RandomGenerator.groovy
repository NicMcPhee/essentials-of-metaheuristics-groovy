package utility
import java.util.Random

class RandomGenerator{
	static def generator = new Random()
	
	static def nextInt(range = 1){
		generator.nextInt(range)
	}
	static def nextDouble(range = 1){
		generator.nextDouble()
	}
	static def nextFloat(range = 1){
		generator.nextFloat()
	}
	static def nextBoolean(){
		generator.nextBoolean()
	}
}
