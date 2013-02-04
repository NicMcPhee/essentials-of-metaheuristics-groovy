package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString


class Crossovers{
    Random random = new Random()
    def onePointCrossover(father, mother){
        int crossoverPoint = random.nextInt(father.size)
        def f = father
        def m = mother
        for(crossoverPoint; crossoverPoint<f.size; crossoverPoint++){
            def tmp = f[crossoverPoint]
            f[crossoverPoint] = m[crossoverPoint]
            m[crossoverPoint] = tmp
        }
        [f, m]
    }
    
    def twoPointCrossover(father, mother){
        int crossoverPointMin = random.nextInt(father.size)
        int crossoverPointMax = random.nextInt(mother.size)
        def f = father
        def m = mother
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
        def f = father
        def m = mother
        for(i in 1..f.size){
            if(probability >= random.nextInt(1)){
                def tmp = f[i]
                f[i] = m[i]
                m[i] = tmp
            }
        }
        [f, m]
    }
}
