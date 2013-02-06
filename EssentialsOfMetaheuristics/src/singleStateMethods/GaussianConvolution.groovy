package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString
import java.util.Random

class GaussianConvolution {

    Integer p = 1
    Integer var = problem.variance()
    Random r = new Random()

    def maximize(problem){
        v = problem.create()

        for(int i = 1; v.size(); i++){
            if (p >= r.nextDouble()) {
                n = r.nextGaussian() * var
                while(! ((problem.min() <= v[i] + n) || (v[i]+n <= problem.max())) ){
                    n = r.nextGaussian() * var
                }
                v[i] += n
            }
        }
        return v
    }
    String toString() {
        "GC var: " + var + " p: " + p
    }
}