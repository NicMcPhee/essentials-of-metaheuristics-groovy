package populationMethods


//consider using @Immutable and removing the private fields
class FitnessValuePair implements Comparable{
	private def value
	private def fitness
	public FitnessValuePair(newValue, newFitness){
		value = newValue
		fitness = newFitness
	}
	
	def getFit(){
		fitness
	}
	def getVal(){
		value
	}
	
	
	//This is set so that default sort methods will return the pairs sorted in descending order by fitness
	public int compareTo(pair2) {
		if(this.fitness > pair2.getFit()){
			-1
		}
		else if (this.fitness < pair2.getFit()){
			1
		}
		else{0}
	}
	
	
}
