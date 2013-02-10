package singleStateMethods

class Crossovers{
    static random = new Random()
	static swap = { x,y ->  x = y; y = x }
    static onePointCrossover(father, mother){
        int crossoverPoint = father.size//random.nextInt(father.size)
        def f = father.clone()
        def m = mother.clone()
        for(crossoverPoint; crossoverPoint<f.size; crossoverPoint++){
          swap(f[crossoverPoint, m[crossoverPoint]])
		  
        }
        [f, m]
    }
    
    def twoPointCrossover(father, mother){
        int crossoverPointMin = random.nextInt(father.size)
        int crossoverPointMax = random.nextInt(mother.size)
        def f = father.clone()
        def m = mother.clone()
        if(crossoverPointMin > crossoverPointMax){
            def tmp = crossoverPointMin
            crossoverPointMax = crossoverPointMin
            crossoverPointMin = tmp
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
        for(i in 1..f.size){
            if(probability >= random.nextFloat()){
                def tmp = f[i]
                f[i] = m[i]
                m[i] = tmp
            }
        }
        [f, m]
    }
}
