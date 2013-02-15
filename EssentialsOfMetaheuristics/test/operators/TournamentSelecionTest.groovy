package operators;

import problems.OnesMax
import spock.lang.Specification;

/*
 * Using such large tournament sizes is a fairly blunt instrument, but it
 * (nearly) guarantees that we sample the "best" individual at least once
 * when running the tournament.
 */
class TournamentSelecionTest extends Specification {
	def "select returns the 'better' option on binary tournaments with pop size 2"() {
		given:
		TournamentSelection ts = new TournamentSelection(tournamentSize : 10)
		def problem = new OnesMax()
		def better = [1, 1, 1, 1]
		def worser = [0, 0, 0, 0]
		def population = [worser, better]
		
		when:
		def choice = ts.select(problem, population)
		
		then:
		choice == better
	}

	def "select returns the 'better' option on tournaments of size 10 with pop size 3"() {
		given:
		TournamentSelection ts = new TournamentSelection(tournamentSize : 20)
		def problem = new OnesMax()
		def better = [1, 1, 1, 1]
		def middle = [1, 0, 1, 0]
		def worser = [0, 0, 0, 0]
		def population = [worser, better, middle]
		
		when:
		def choice = ts.select(problem, population)
		
		then:
		choice == better
	}
}
