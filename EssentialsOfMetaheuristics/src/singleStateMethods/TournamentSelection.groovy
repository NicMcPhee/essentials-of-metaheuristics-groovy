package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString
import java.util.Random

class TournamentSelection{
    Integer t = 2
    Random r = new Random()
    def maximize(problem, population){
        s = population[r.nextInt(population.size())]
        sQuality = problem.quality(s)
        for(i in 2..t){
            n = population[r.nextInt(population.size())]
            if(problem.quality(n) > sQuality){
                s = n
                sQuality = problem.quality(n)
            }
        }
        return s
    }
    String toString() {
        "TS_" + t
    }
}