package problems

import java.util.Random;

class Schwefel extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -512.03
    Float upperBound = 511.97
    Float halfMutationRange = 0.5

    def quality = { a ->
        ++evalCount
        return -(a.sum({v -> -v*Math.sin(Math.pow(Math.abs(v), 0.5))}))
    }
}