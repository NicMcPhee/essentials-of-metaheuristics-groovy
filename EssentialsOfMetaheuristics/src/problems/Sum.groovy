package problems

class Sum extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -5.12
    Float upperBound = 5.12
    Float halfMutationRange = 0.5

    // Minimize f(<x_i>) = sum { x_i }, x \in [-5.12, 5.12],
    // so we'll negate the result since everything we have maximizes.
    def quality = { a ->
        ++evalCount
        return -a.sum()
    }
}
