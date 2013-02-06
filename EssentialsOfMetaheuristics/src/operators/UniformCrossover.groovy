package operators

class UniformCrossover {
	final def RANDOM = new Random()

	def crossover(parent1, parent2, p = 1.0 / parent1.size()) {
		assert parent1.size() == parent2.size(), 'parents are the wrong size'

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

		return [new1, new2]
	}
	
	String toString() {
		"UC"
	}
}