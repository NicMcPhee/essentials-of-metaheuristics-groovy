package operators

import groovy.transform.ToString
import java.util.Random

class TournamentSelection{
    Integer tournamentSize = 2
    Random r = new Random()
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