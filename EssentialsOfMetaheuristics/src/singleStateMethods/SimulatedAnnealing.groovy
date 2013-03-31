package singleStateMethods

class SimulatedAnnealing{

    def annealingAlg = {t, decT, problemSize, problem ->
       
       def s = problem.create() //initial solution
       def best = problem.copy(s)          //set best to s
       Random rand = new Random()
   
       while((t>0) && (problem.quality(best)<problemSize)){
           def r = problem.tweak(s)
           Double num = rand.nextDouble()
           double alg = Math.pow(Math.E,((problem.quality(r)-problem.quality(s))/t))
           if(problem.quality(r)>problem.quality(s) || num<alg){
               s=problem.copy(r)
               t=decT(t)
           }
           if(problem.quality(s)>problem.quality(best)){
               best=problem.copy(s)
           }
       }
       
       best
    }
 
}
