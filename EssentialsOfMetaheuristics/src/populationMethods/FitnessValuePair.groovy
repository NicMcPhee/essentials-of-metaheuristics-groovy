package populationMethods
import problems.OnesMax
import ArrayList

class FitnessValuePair {
	def value
	public int fitness
	public FitnessValuePair(newValue, newFitness){
		value = newValue
		fitness = newFitness
	}
	String toString(){
		"["+value+":"+fitness+"]"
	}
	
	def getFit(){
		fitness
	}
	def getVal(){
		value
	}
	
	public int compareTo(pair2) {
		if(this.fitness > pair2.getFit()){
			1
		}
		else if (this.fitness < pair2.getFit()){
			-1
		}
		else{0}
	}
	
	
}
