package gradientAscent

class Main {

	static main(args) {
		def ascender = new GradientAscender()
		def f = { x ->
			// f(x) = (x-5)*(2x+6) = -(2x^2 - 4x - 30)
			// f'(x) = -4x + 4
			// f'(x) = 0 == x = 1
			// f(1) = -(2*1 - 4*1 - 30) = 32
			-(2 * x * x - 4 * x - 30)
		}
		System.out.println(ascender.findMaximum(f, -1000000)); 
	}

}
