package Algorithm31
import java.util.Random;

class StochasticUniversalSampling {
    
    //population size
    def n
    
    //population vector
    def p 
    
    //fitness vector (corresponds to individuals in p)
    def f
    
    //global index
    def index = 0
    
    //global value
    def value
    
    //random number generator
    def randomInt = new Random()
    
    //method for shuffling vectors (shuffles both p and f to preserve order)
    def shuffle(vector1,vector2){
        def j
        def l = vector1.length
        def i
        for (i = l; i >= 2; i--){
            j = randomInt.next(l-i)+i
            def temp1 = vector1.i
            vector1.i = vector1.j
            vector1.j = temp1
            def temp2 = vector2.i
            vector2.i = vector2.j
            vector2.j = temp2
        }
        
    }
    //main Stochastic Universal Sampling method
    def sus(n, p, f){
        for(int b = 0; b < n; b++){
            shuffle(p,f)
            def allZeros = true
            for(int i = 0; i <= f.length; i++){
                if (f[i] != 0){
                    allZeros = false
                } 
            }
            if (allZeros == true){
                for(int x = 0; x < f.length; x++)
                f[x]=1
            }
            for (int a = 1; a < f.length; a++){
                f[a] = f[a] + f[a-1]
            }
            value = randomInt.next(f[f.length-1]/n)
        }
        while (f[index] < value){
            index++
            value = value + f[f.length-1]/n
            return p[index]
        }    
    }  
    
}
