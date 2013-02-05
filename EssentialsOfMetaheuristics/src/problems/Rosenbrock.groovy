package problems

import java.util.Random;

class Rosenbrock {

    protected Random rand = new java.util.Random()
    Integer evalCount = 0
    Integer maxIterations = 1000
    Integer numValues = 8
    Float lowerBound = -2.048
    Float upperBound = 2.048
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

    //We are trying to maximize the sum of the floor of all of the vector elements
    //plus 6 * vectorSize
    def quality = { a ->
        ++evalCount
        def output = 0
        for (int i = 0; i < a.size() - 1; i++) {
            output += Math.pow(1-a[i], 2) + 100*Math.pow(a[i+1] - Math.pow(a[i], 2), 2)
        }
        return output
    }

    def terminate = { a, q = quality(a) ->
        evalCount >= maxIterations
    }

    String toString() {
        this.class.name.split("\\.")[-1] + "_" + numValues + "_" + maxIterations
    }




}