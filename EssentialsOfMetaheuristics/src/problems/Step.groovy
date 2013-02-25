package problems

import java.util.Random;

class Step extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -5.12
    Float upperBound = 5.12
    Float halfMutationRange = 0.5

    //We are trying to maximize the sum of the floor of all of the vector elements
    //plus 6 * vectorSize
    def quality = { a ->
        ++evalCount
        return -(a.size()*6 + a.sum({v -> Math.floor(v)}))
    }
}