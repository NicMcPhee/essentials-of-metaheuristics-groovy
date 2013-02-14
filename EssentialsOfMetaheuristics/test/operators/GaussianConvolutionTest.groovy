package operators;

import spock.lang.Specification;

class GaussianConvolutionTest extends Specification {
	
	class MockProblem {
		def variance() { return 0.5 }
		def min() { return 0 }
		def max() { return 1 }
	}

	def "mutate returns a vector of the correct length in right range and doesn't change original vector"() {
		given:
		def problem = new MockProblem()
		GaussianConvolution gc = new GaussianConvolution(variance : 0.5)
		def vector = [0.96, 0.83, 0.94, 0.05, 0.78, 0.78, 0.04, 0.53, 0.32, 0.97]
		def origVector = vector.clone()
		
		when:
		def w = gc.mutate(problem, vector)
		
		then:
		w.size() == vector.size()
		w.every { x -> problem.min() <= x && x <= problem.max() }
		(0..<vector.size()).every { i ->
			vector[i] == origVector[i]
			w[i] != vector[i]
		}
	}
	
	def "mutate with p=0 doesn't change vector"() {
		given:
		def problem = new MockProblem()
		GaussianConvolution gc = new GaussianConvolution(p : 0, variance : 0.5)
		def vector = [0.96, 0.83, 0.94, 0.05, 0.78, 0.78, 0.04, 0.53, 0.32, 0.97]
		
		when:
		def w = gc.mutate(problem, vector)
		
		then:
		w.size() == vector.size()
		(0..<vector.size()).every { i ->
			w[i] == vector[i]
		}
	}
	
	def "toString() builds a result with default p"() {
		given:
		GaussianConvolution gc = new GaussianConvolution(variance : 0.5)
		
		when:
		String s = gc.toString()
		
		then:
		// This is a quite lame test, but I don't want to create a really
		// rigid test that will break just because someone wants to modify
		// the toString output.
		s.length() > 0
	}

	def "toString() builds a result"() {
		given:
		GaussianConvolution gc = new GaussianConvolution(p : 0.75, variance : 0.5)
		
		when:
		String s = gc.toString()
		
		then:
		// This is a quite lame test, but I don't want to create a really
		// rigid test that will break just because someone wants to modify
		// the toString output.
		s.length() > 0
	}

}
