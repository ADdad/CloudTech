import java.util.*;

/**
 * Created by rokde on 12.03.2017.
 */
public class DictCompress {
    int block_size = 4;
    SortedMap<String, ArrayList<Integer>> sortedMap;
    ArrayList<String> compressed;

    DictCompress(HashMap<String, ArrayList<Integer>> dict){
        sortedMap = new TreeMap();
        sortedMap.putAll(dict);
    }

    void compressDict(){
        compressed = new ArrayList<>();
        String block = "";
        int sz = 0;
        for(Map.Entry<String,ArrayList<Integer>> entry : sortedMap.entrySet()) {
            block += entry.getKey()+ " ";
            sz++;
            if(sz >=block_size){
                addBlock(block);
                sz=0;
                block = "";
            }
        }
    }

    void addBlock(String s){
        String[] bl = s.split(" ");
    }

}
