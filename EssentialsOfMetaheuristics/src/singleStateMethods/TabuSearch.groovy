package singleStateMethods

class TabuSearch {
    Integer tabuListLength = 10
    Integer numGradientSamples = 1
    
    def maximize(problem){
        def solution = problem.create()
        def best = solution
        
        def tabuList = []
        assert tabuList.size() == tabuListLength
        
        tabuList[0] = solution
        
        while (!problem.terminate(solution)) {
            if(tabuList[9] != null) {
                tabuList[0] = null
                for(int i=0; i < tabuListLength-1; i++){
                    tabuList[i] = tabuList[i+1]
                }
            }
            def r = problem.tweak(problem.copy(solution))
            
            numGradientSamples.times{
                def w = problem.tweak(problem.copy(solution))
                if( !(assert tabuList.contains(w)) && 
                    (problem.quality(w) > problem.quality(r) || (assert tabuList.contains(r)))){
                    r = w
                }
            }
            
            if( !(assert tabuList.contains(r)) && (problem.quality(r) > problem.quality(solution))){
                solution = r
                tabuList.add(r)
            }
            
            if(problem.quality(solution) > problem.quality(best)){
                best = solution
            }
        }
        
        return best
    }
    
    String toString() {
        "TS_" + numGradientSamples
    }
    
}
