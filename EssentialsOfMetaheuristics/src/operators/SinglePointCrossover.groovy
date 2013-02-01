package operators

class SinglePointCrossover {
	final def RANDOM = new Random();
	
	def crossover(parent1, parent2) {
		if (parent1.size() == parent2.size()) {
			def c = RANDOM.nextInt(parent1.size()+1)
			//System.out.println(c)
			def start1 = []
			def start2 = []
			def end1 = []
			def end2 = []
			def new1 = []
			def new2 = []
			
			if (c > 0) {
				start1 = parent1[0..c-1]
				start2 = parent2[0..c-1]
			}
			if (c < parent1.size()) {
				end1 = parent1[c..parent1.size()-1]
				end2 = parent2[c..parent2.size()-1]
			}
			
			new1 = start1 + end2
			new2 = start2 + end1
			
			return [new1, new2]
			
			//output.addAll(parent1[0..c])
			//output.addAll(parent2[c..parent2.size()-1])
		} else {
			//ERROR
		}
	}
}