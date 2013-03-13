package operators

import groovy.transform.ToString
import utility.RandomGenerator

class TournamentSelection{
    Integer tournamentSize = 2
    def r = RandomGenerator.generator
    def select(problem, population){
        def s = population[r.nextInt(population.size())]
        def sQuality = problem.quality(s)
        for(i in 2..tournamentSize) {
            def n = population[r.nextInt(population.size())]
            def nQuality = problem.quality(n)
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