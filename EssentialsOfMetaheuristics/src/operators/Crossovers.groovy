package operators

class Crossovers{
	static random = new Random()

	static onePointCrossover(father, mother, crossoverPoint = random.nextInt(father.size)){
		def f = []
		def m = []
		(f, m) = [
			father[0..<crossoverPoint] + mother[crossoverPoint..<mother.size],
			mother[0..<crossoverPoint] + father[crossoverPoint..<father.size]]
	}

	static twoPointCrossover(father, mother,
			xoMin = random.nextInt(father.size),
			xoMax = random.nextInt(mother.size)){
		def f = []
		def m = []
		if(xoMin > xoMax){
			(xoMin, xoMax) = [xoMax, xoMin]
		}
		(f, m) =
				[
					father[0..<xoMin] + mother[xoMin..<xoMax] + father[xoMax..<father.size],
					mother[0..<xoMin] + father[xoMin..<xoMax] + mother[xoMax..<father.size]
				]
	}

	def uniformCrossover(father, mother){
		def probability = 1/father.size
		def f = father.clone()
		def m = mother.clone()
		for(i in 1..f.size-1){
			if(probability >= random.nextFloat()){
				def tmp = f[i]
				f[i] = m[i]
				m[i] = tmp
			}
		}
		[f, m]
	}
}
