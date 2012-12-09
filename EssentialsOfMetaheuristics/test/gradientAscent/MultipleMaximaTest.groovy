package gradientAscent

import static org.hamcrest.core.AnyOf.anyOf
import static org.junit.Assert.*
import static spock.util.matcher.HamcrestMatchers.closeTo
import spock.lang.Specification

class MultipleMaximaTest extends Specification {
	static final EPSILON = 1E-5
	def f = { x ->
		// f(x) = -(x+1)(x-1)(x-2)(x+1/2) = -x^4 + (3/2)x^3 + 2x^2 - (3/2)x - 1
		// Local maxima are at (approximately)
		//   -0.778446 (local)
		//   1.60291 (global)
		-x*x*x*x + (3/2)*x*x*x + 2*x*x - (3/2)*x - 1
	}
	static final LOCAL_MAX = -0.778446
	static final GLOBAL_MAX = 1.60291
	def ascender = new GradientAscender()

	def "Basic call with just the objective function"() {
		expect:
		(ascender.findMaximum(f)) anyOf([LOCAL_MAX, GLOBAL_MAX].collect 
			{ target -> closeTo(target, EPSILON) })
	}

	def "Calls from several starting points"() {
		expect:
		(ascender.findMaximum(f, start)) anyOf([LOCAL_MAX, GLOBAL_MAX].collect 
			{ target -> closeTo(target, EPSILON) })

		where:
		start << [-2, 0, 1, 2]
	}
}
