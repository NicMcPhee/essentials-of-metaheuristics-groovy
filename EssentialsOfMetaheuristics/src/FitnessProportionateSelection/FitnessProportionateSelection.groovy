package FitnessProportionateSelection

class FitnessProportionateSelection {

    public double select(problem, population) {
        def sum = 0
        def decision = Math.random()
        for(int i = 0; i< population.size();i++) {
            sum += problem.quality(population[i])
            if(decision <= sum){
               return population[i]
            }
        }
    }
}