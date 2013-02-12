package operators

import groovy.transform.ToString
import java.util.Random

class GaussianConvolution {

    float p = 1
    Integer var
    Random r = new Random()

    def mutate(problem, v) {
        vCopy = v.clone()
        var = problem.variance()
        vCopy.size().times { i ->
            if (p >= r.nextDouble()) {
                n = r.nextGaussian() * var
                while(! ((problem.min() <= vCopy[i] + n) && (vCopy[i]+n <= problem.max())) ){
                    n = r.nextGaussian() * var
                }
                vCopy[i] += n
            }
        }
        return vCopy
    }
    String toString() {
        "GC_var:_" + var + "_p:_" + p
    }
}