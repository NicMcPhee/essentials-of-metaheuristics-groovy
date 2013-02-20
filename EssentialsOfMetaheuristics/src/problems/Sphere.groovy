package problems

import java.util.Random;

class Sphere extends VectorProblem{

    protected Random rand = new java.util.Random()
    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -5.12
    Float upperBound = 5.12
    Float halfMutationRange = 0.5

    def quality = { a ->
        ++evalCount
        return -(a.sum({v -> Math.pow(v, 2)}))
    }
}