package problems

import groovy.transform.ToString


class OnesMax extends BitStringProblem {
	def quality = { a ->
		++evalCount
		a.count(1)
	}
}
