package problems

class FitnessProportionalSelection {

    public double maximize(problem, population) {
        def sum = 0
        def val
        def decision = Math.random()
        for(int i = 0; i< population.size();i++) {
            sum += problem.quality(population[i])
            if(decision <= sum){
                val = population[i]
                return val
            }
        }
    }
}