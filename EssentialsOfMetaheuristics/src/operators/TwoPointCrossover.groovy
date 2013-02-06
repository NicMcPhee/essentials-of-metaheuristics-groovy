package operators

class TwoPointCrossover {
	final def RANDOM = new Random()

	def crossover(parent1, parent2,
		c1 = RANDOM.nextInt(parent1.size() + 1),
		c2 = RANDOM.nextInt(parent1.size() + 1)) {
		assert parent1.size() == parent2.size(), 'parents are the wrong size'

		def new1 = []
		def new2 = []

		if (c1 > c2) {
			def temp = c1
			c1 = c2
			c2 = temp
		}

		new1 += parent1[0 ..< c1]
		new2 += parent2[0 ..< c1]
		new1 += parent2[c1 ..< c2]
		new2 += parent1[c1 ..< c2]
		new1 += parent1[c2 ..< parent1.size()]
		new2 += parent2[c2 ..< parent2.size()]

		return [new1, new2]
	}
		
	String toString() {
		"TPC"
	}
}