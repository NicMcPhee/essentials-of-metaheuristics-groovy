package problems

class LeadingOnes extends BitStringProblem {

	def quality = { a ->
		++evalCount
		Integer numOnes = 0
		while (a[numOnes] == 1) {
			++numOnes
		}
		return numOnes
	}
}
