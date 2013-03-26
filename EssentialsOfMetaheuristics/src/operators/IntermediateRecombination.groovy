package operators

import java.util.Random;


class IntermediateRecombination {
    Random rand = new Random()


    //assume inbounds returns a boolean
    //p is how far the child can be located and p>0
    //father size should equal mother size
    def recombAlg = {father, mother, p, inBounds ->

        def t,s

        father.size().times(){
            def val1 = randVal(p)
            def val2 = randVal(p)
            t = (val1*father[it]) + ((1-(val1))*mother[it])
            s = (val2*mother[it]) + ((1-(val2))*father[it])

            while(!inBounds(s) || !inBounds(t)){
                val1 = randVal(p)
                val2 = randVal(p)
                t= (val1*father[it]) + ((1-(val1))*mother[it])
                s= (val2*mother[it]) + ((1-(val2))*father[it])
                
            }
            father[it] = t
            mother[it] = s
        }
        [father, mother]}

    def randVal = {p->

        def val = rand.nextInt(p + 1)
        if(rand.nextInt(1)==0){
            val *= -1 
        }

    }

}
