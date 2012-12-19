package gradientAscent

/**
 * A very simple implementation of Gradient Ascent as described in Chapter 1 of
 * Essentials of Metaheuristics by Sean Luke.
 * 
 * This could be made considerably more sophisticated. I'm assuming, for example,
 * that our objective function is a function of one variable, and I'm not extending
 * this to Newton's method (which would include the second derivative). Given that
 * this is just a simple example of a non-stochastic and non-evolutionary approach,
 * however, it doesn't seem to make sense to spend a lot of time polishing this.
 * 
 * @author mcphee
 */
class GradientAscender {
	final alpha = 0.001
	final epsilon = 1e-10
	
	/**
	 * Apply gradient ascent on the given function f. You can also
	 * specify an option starting value of x (which defaults to 0)
	 * and a maximum number of iterations (which defaults to 10,000).
	 * 
	 * @param f
	 * 	The function we're performing gradient ascent on
	 * @return
	 *  The value of x that "maximizes" f. This won't always actually
	 *  be the maximum value because gradient ascent isn't guaranteed
	 *  to always yield a global maximum.
	 */
	def findMaximum(f, start=0, maxIterations=10000) {
		// I needed to use a double here or Groovy's use of BigDecimal
		// would run off into the void, probably flailing in some huge
		// exact rational number arithmetic.
		double x = start
		for (int i=0; i<maxIterations; ++i) {
			def delta = derivative(f, x)
			def new_x = x + alpha * delta
			// A simple "early out" method that stops the loop if two
			// consecutive values are "quite close", where I'm using
			// epsilon for "quite close".
			if (Math.abs(new_x - x) < epsilon) {
				break
			}
			x = new_x
		}
		return x
	}
	
	def derivative(f, x) {
		return (f(x+epsilon) - f(x))/epsilon
	}
}
