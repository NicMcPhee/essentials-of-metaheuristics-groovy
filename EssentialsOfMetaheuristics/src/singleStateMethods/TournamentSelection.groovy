package singleStateMethods

import groovy.transform.ToString
import java.util.Random

class TournamentSelection{
    Integer tournamentSize = 2
    Random r = new Random()
    def select(problem, population){
        s = population[r.nextInt(population.size())]
        sQuality = problem.quality(s)
        for(i in 2..tournamentSize){
            n = population[r.nextInt(population.size())]
            nQuality = problem.quality(n)
            if(nQuality > sQuality){
                s = n
                sQuality = nQuality
            }
        }
        return s
    }
    String toString() {
        "TS_" + tournamentSize
    }
}