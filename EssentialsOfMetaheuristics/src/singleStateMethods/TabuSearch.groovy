package singleStateMethods

class TabuSearch {
    Integer tabuListLength = 10
    Integer numGradientSamples = 1
    
    def maximize(problem){
        def solution = problem.create()
        def best = solution
        def tabuList = [] as Queue
        tabuList[0] = solution
        
        def solutionQuality = problem.quality(solution)
        def bestQuality = solutionQuality
        
        while (!problem.terminate(solution, solutionQuality)) {
            
            if(tabuList.size() == tabuListLength) {
                tabuList.poll()
            }
            assert tabuList.size() < tabuListLength
            
            def r = problem.tweak(problem.copy(solution))
            def rQuality = problem.quality(r)
            
            numGradientSamples.times {
                def w = problem.tweak(problem.copy(solution))
                def wQuality = problem.quality(w)
                
                if( !(tabuList.contains(w)) &&
                    ((wQuality > rQuality) || (tabuList.contains(r))) ){
                    r = w
                    rQuality = wQuality
                }
            }
            
            if( !(tabuList.contains(r)) && (rQuality > solutionQuality)){
                solution = r
                solutionQuality = rQuality
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
