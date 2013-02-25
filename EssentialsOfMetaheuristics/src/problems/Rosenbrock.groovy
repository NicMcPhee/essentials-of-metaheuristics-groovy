package problems

import java.util.Random;

class Rosenbrock extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -2.048
    Float upperBound = 2.048
    Float halfMutationRange = 0.5
    
    def quality = { a ->
        ++evalCount
        def output = 0
        (a.size()-1).times { i->
            output += (1-a[i])**2 + 100*(a[i+1] - a[i]**2)**2
        }
        return -output
    }
}