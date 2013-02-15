package FitnessProportionateSelection

class FitnessProportionateSelection {

    public double select(problem, population) {
        def sum = 0
        def fitIndex = 0
         def decision = Math.random()
        // build a population fitness array
        def fitnessArray = normalizeFitness(problem,population)
        // normalize fitness array
        fitnessArray = normalize(fitnessArray)
        for(person in population) {
            sum += fitnessArray[fitIndex]
            fitIndex++
            if(decision <= sum){
                return person
            }
        }
    }
    //
    // Builds population fitness array
    //
    private buildPopFitness(problem,population){
        def fitnessArray= []
        for(person in population){
            fitnessArray.add(problem.quality(person))
        }
        fitnessArray
    }
    //
    // Builds a Normalized fitness array
    //
    private normalizeFitness(fitnessArr){
        def normFits = []
        def sum = normalizedSum(fitnessArr)
        for(fitness in fitnessArr){
            normFits.add(fitness*sum)
        }
        normFits
    }
    //
    // derives normalization ratio
    //
    private normalizedSum(fitnessArr){
        def sum =0
        for(fitness in fitnessArr){
            sum+=fitness
        }
        return new BigDecimal(1/sum)
    }
}