package problems

class Rastrigin extends VectorProblem{

    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -5.12
    Float upperBound = 5.12
    Float halfMutationRange = 0.5

    // Minimize f(<x_i>) = 10n + sum { x_i^2 - 10 cos(2 \pi x_i) }, x \in [-5.12, 5.12],
    // so we'll negate the result since everything we have maximizes.
    def quality = { a ->
        ++evalCount
        return -a.inject(10 * numValues) { s, x ->
            s + x*x - 10*Math.cos(2*Math.PI*x)
        }
    }
}
