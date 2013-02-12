package populationMethods

@Immutable class FitnessValuePair implements Comparable{
	protected def value
	protected def fitness

	//This is set so that default sort methods will return the pairs sorted in descending order by fitness
	public int compareTo(pair2) {
		if(this.fitness > pair2.fitness){
			-1
		}
		else if (this.fitness < pair2.fitness){
			1
		}
		else{0}
	}


}
