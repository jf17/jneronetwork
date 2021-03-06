
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
/*
 * @author  Alexandrovich Alexey Borisovich
 */

public class BinaryClassificationNeuron {

    final Map<String, LongAdder> myMap;

    public BinaryClassificationNeuron() {
        myMap = new HashMap<String, LongAdder>();
    }

    public void put(String key, boolean binaryClassificator) {

        if(myMap.containsKey(key)==false){myMap.put(key, new LongAdder());}

        if (myMap.containsKey(key) && binaryClassificator == true) {
            myMap.get(key).increment();
        } else if (myMap.containsKey(key) && binaryClassificator == false) {
            myMap.get(key).decrement();
        }

    }

    public void print() {

        System.out.println(myMap);

    }

    public int getScoreByKey(String key) {

        LongAdder la= myMap.get(key);
        return la.intValue();

    }




}