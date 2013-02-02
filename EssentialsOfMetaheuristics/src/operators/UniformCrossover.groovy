package operators

class UniformCrossover {
	final def RANDOM = new Random()
	
	def crossover(parent1, parent2) { // user provides no probability
		def p = 1.0 / parent1.size();
		
		return doCrossover(parent1, parent2, p);
	}
	
	def crossover(parent1, parent2, p) { // user provides probability
		return doCrossover(parent1, parent2, p);
	}
	
	private def doCrossover(parent1, parent2, p) {
		if (parent1.size() == parent2.size()) {
			def new1 = []
			def new2 = []
			
			for (i in 0 ..< parent1.size()) {
				if (p >= RANDOM.nextFloat()) {
					new1 += parent2[i]
					new2 += parent1[i]
				} else {
					new1 += parent1[i]
					new2 += parent2[i]
				}
			}
			
			System.out.println([new1, new2])
			
			return [new1, new2]
		} else {
			throw new Error("Both parents need to be the same size!");
		}
	}
}