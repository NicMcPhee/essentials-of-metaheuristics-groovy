package operators

class Crossovers{
	static random = new Random()
	static swap = { x,y ->  x = y; y = x }
	static onePointCrossover(father, mother, crossoverPoint = random.nextInt(father.size)){
		def f = []
		def m = []
		(f, m) = [father[0..<crossoverPoint] + mother[crossoverPoint..<mother.size], 
				  mother[0..<crossoverPoint] + father[crossoverPoint..<father.size]]				
	}

	static twoPointCrossover(father, mother, 
		crossoverPointMin = random.nextInt(father.size), 
		crossoverPointMax = random.nextInt(mother.size)){
		def f = []
		def m = []
		if(crossoverPointMin > crossoverPointMax){
			(crossoverPointMin, crossoverPointMax) = [crossoverPointMax, crossoverPointMin]
		}

		for(crossoverPointMin; crossoverPointMin<crossoverPointMax-1; crossoverPointMin++){
			def tmp = f[crossoverPointMin]
			f[crossoverPointMin] = m[crossoverPointMin]
			m[crossoverPointMin] = tmp
		}
		[f, m]
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
