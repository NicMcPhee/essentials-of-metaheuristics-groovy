package problems

//import java.util.Random;

class VectorProblem {

    protected Random rand = new java.util.Random()

    def create = { n = numValues, values = null ->
        // Makes an array of n random floats between lowerBound and upperBound
        numValues = n
        if (values != null) return values
        def result = []
        n.times {
            result << rand.nextFloat() * (upperBound - lowerBound) + lowerBound
        }
        return result
    }
    
    def random = create

    def copy = { a -> a.clone() }

    def tweak = {a, perturbMultiplier = 1 ->
        a.collect { v ->
            bound((v + (2 * rand.nextFloat() - 1) * halfMutationRange * perturbMultiplier))
        }
    }

    def perturb = {a, perturbMultiplier = 5 ->
        tweak(a, perturbMultiplier)
    }
    
    
    def bound(x) {
        if (x < lowerBound) {
            lowerBound
        } else if (x > upperBound) {
            upperBound
        } else {
            x
        }
    }

    def terminate = { a, q = quality(a) ->
        evalCount >= maxIterations
    }

    String toString() {
        //this.class.name.split("\\.")[-1] + "_" + numValues + "_" + maxIterations
        this.class.name.split("\\.")[-1] + "_MR" + halfMutationRange
    }




}