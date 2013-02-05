package singleStateMethods

class TabuSearch {
    Integer tabuListLength = 10
    Integer numGradientSamples = 1
    
    def maximize(problem){
        def solution = problem.create()
        def solutionQuality = problem.quality(solution)
        def best = solution
        def bestQuality = solutionQuality
        
        def tabuList = []
        
        tabuList[0] = solution
        
        while (!problem.terminate(solution, solutionQuality)) {
            def bestSample
            def bestSampleQuality = Integer.MIN_VALUE
            
            if(tabuList[tabuListLength - 1] != null) {
                tabuList.remove(0)
            }
            
            numGradientSamples.times{
                def r = problem.tweak(problem.copy(solution))
                def rQuality = problem.quality(r)
                
                if( !(tabuList.contains(rQuality)) && 
                    (rQuality > bestSampleQuality || (tabuList.contains(r)))){
                    bestSample = r
                    bestSampleQuality = rQuality
                }
            }
            
            
            
            if( !(tabuList.contains(bestSample)) && (bestSampleQuality > solutionQuality)){
                solution = bestSample
                tabuList.add(bestSample)
            }
            
            if(solutionQuality > bestQuality){
                best = solution
                bestQuality = solutionQuality
            }
        }
        
        return solution
    }
    
    String toString() {
        "TS_" + numGradientSamples
    }
    
}
