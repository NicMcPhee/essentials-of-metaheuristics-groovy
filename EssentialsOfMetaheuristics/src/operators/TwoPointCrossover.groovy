package operators

class TwoPointCrossover {
	final def RANDOM = new Random()
	
	def crossover(parent1, parent2) {
		if (parent1.size() == parent2.size()) {
			def c1 = RANDOM.nextInt(parent1.size())
			def c2 = RANDOM.nextInt(parent1.size())
			def new1 = []
			def new2 = []
			
			if (c1 > c2) {
				def temp = c1
				c1 = c2
				c2 = temp
			}
			
			if (c1 > 0) {
				new1 += parent1[0..c1 - 1]
				new2 += parent2[0..c1 - 1]
			}
			if (c2 > c1) {
				new1 += parent2[c1..c2 - 1]
				new2 += parent1[c1..c2 - 1]
			}
			new1 += parent1[c2..parent1.size()-1]
			new2 += parent2[c2..parent2.size()-1]
			
			System.out.println("c1: " + c1 + "\nc2: " + c2 + "\n output: " + [new1, new2]);
			
			return [new1, new2]
		} else {
			throw new Error("Both parents need to be the same size!");
		}
	}
}