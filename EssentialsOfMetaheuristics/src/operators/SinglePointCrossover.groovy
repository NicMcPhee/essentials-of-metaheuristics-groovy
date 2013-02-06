package operators

class SinglePointCrossover {
	final def RANDOM = new Random()

	def crossover(parent1, parent2,
		c = RANDOM.nextInt(parent1.size() + 1)) {
		assert parent1.size() == parent2.size(), 'parents are the wrong size'

		def new1 = []
		def new2 = []

		new1 += parent1[0 ..< c]
		new2 += parent2[0 ..< c]
		new1 += parent2[c ..< parent1.size()]
		new2 += parent1[c ..< parent2.size()]

		return [new1, new2]
	}
		
	String toString() {
		"SPC"
	}
}