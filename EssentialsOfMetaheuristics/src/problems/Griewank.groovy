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
        def sumPart = 0
        def productPart = 1
        a.size().times { v ->
            sumPart += Math.pow(a[v], 2)
            productPart = productPart * Math.cos(a[v] / (v+1)**(1/2))
        }
        return -(1 + sumPart/4000 + productPart)
    }
}