package problems

import java.util.Random;

class Griewank extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -600
    Float upperBound = 600
    Float halfMutationRange = 0.5
    
    def quality = { a ->
        ++evalCount
        def output1 = 0
        def output2 = 1
        for (int i = 0; i < a.size(); i++) {
            output1 += Math.pow(a[i], 2)
            output2 = output2 * Math.cos(a[i] / Math.pow(i+1, 1/2))
        }
        return 1 + output1/4000 + output2
    }
}