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
        for (int i = 0; i < a.size() - 1; i++) {
            output += Math.pow(1-a[i], 2) + 100*Math.pow(a[i+1] - Math.pow(a[i], 2), 2)
        }
        return output
    }
}