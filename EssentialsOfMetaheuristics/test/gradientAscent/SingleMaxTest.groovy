package gradientAscent;

import static org.junit.Assert.*
import static spock.util.matcher.HamcrestMatchers.closeTo
import spock.lang.Specification

/**
 * Tests for a function with a single maximum at x=1.
 * 
 * @author mcphee
 */
class SingleMaxTest extends Specification {
	static final EPSILON = 1E-5
	def f = { x ->
		// f(x) = -(x-5)*(2x+6) = -(2x^2 - 4x - 30)
		// f'(x) = -4x + 4
		// f'(x) = 0 == x = 1
		// f(1) = -(2*1 - 4*1 - 30) = 32
		// So the expected maximum is at x=1
		-(2 * x * x - 4 * x - 30)
	}
	def ascender = new GradientAscender()

	def "Basic call with just the objective function"() {
		expect:
		(ascender.findMaximum(f)) closeTo(1, EPSILON)
	}

	def "Calls from several starting points"() {
		expect:
		(ascender.findMaximum(f, start)) closeTo(1, EPSILON)

		where:
		start << [-100, 0, 1, 1000]
	}
}
