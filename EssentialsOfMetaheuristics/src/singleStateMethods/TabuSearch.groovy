package singleStateMethods

class TabuSearch {
    Integer tabuListLength = 10
    
    def maximize(problem, numGradientSamples = 1){
        def solution = problem.create()
        def best = solution
        def tabuList = [] as Queue
        tabuList[0] = solution
        
        def solutionQuality = problem.quality(solution)
        def bestQuality = solutionQuality
        
        while (!problem.terminate(solution, solutionQuality)) {

            if(tabuList[tabuListLength - 1] != null) {
                tabuList.poll()
            }
            
            def r = problem.tweak(problem.copy(solution))
            def rQuality = problem.quality(r)
            
            numGradientSamples.times {
                def w = problem.tweak(problem.copy(solution))
                def wQuality = problem.quality(w)
                
                if( !(tabuList.contains(w)) &&
                    ((wQuality > rQuality) || (tabuList.contains(r)) )){
                    r = w
                    rQuality = wQuality
                }
            }
            
            if( !(tabuList.contains(r)) && (rQuality > solutionQuality)){
                solution = r
                tabuList.add(r)
            }
            
            if(solutionQuality > bestQuality){
                best = solution
                bestQuality = solutionQuality
            }
        }
        
        return best
    }
    
    String toString() {
        "TS_" + numGradientSamples
    }
    
}
