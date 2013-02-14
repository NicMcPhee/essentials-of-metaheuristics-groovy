package operators

import groovy.transform.ToString
import java.util.Random

class GaussianConvolution {

    float p = 1
    Float variance
    Random r = new Random()

    def mutate(problem, v) {
        def vCopy = v.clone()
        variance = problem.variance()
        vCopy.size().times { i ->
            if (p >= r.nextDouble()) {
                def n = r.nextGaussian() * variance
                while(! ((problem.min() <= vCopy[i] + n) && (vCopy[i]+n <= problem.max())) ){
                    n = r.nextGaussian() * variance
                }
                vCopy[i] += n
            }
        }
        return vCopy
    }
    String toString() {
        "GC_var:_" + variance + "_p:_" + p
    }
}