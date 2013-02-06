package problems

import java.util.Random;

class Schwefel {

    protected Random rand = new java.util.Random()
    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -5.12
    Float upperBound = 5.12
    Float halfMutationRange = 0.5

    def create = { n = numValues, values = null ->
        // Makes an array of n random floats between lowerBound and upperBound
        numValues = n
        if (values != null) return values
        def result = []
        for (i in 0..<n) {
            result << rand.nextFloat() * (upperBound - lowerBound) + lowerBound
        }
        return result
    }
    
    def random = create

    def copy = { a -> a.clone() }

    def tweak = { a ->
        a.collect { v ->
            bound(v + (2 * rand.nextFloat() - 1) * halfMutationRange)
        }
    }

    private bound(x) {
        if (x < lowerBound) {
            lowerBound
        } else if (x > upperBound) {
            upperBound
        } else {
            x
        }
    }

    def quality = { a ->
        ++evalCount
        return a.sum({i -> -i*Math.sin(Math.pow(Math.abs(i), 0.5))})
    }

    def terminate = { a, q = quality(a) ->
        evalCount >= maxIterations
    }

    String toString() {
        this.class.name.split("\\.")[-1] + "_" + numValues + "_" + maxIterations
    }




}