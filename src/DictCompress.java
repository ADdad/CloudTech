import java.util.*;

/**
 * Created by rokde on 12.03.2017.
 */
public class DictCompress {
    int block_size = 4;
    SortedMap<String, ArrayList<Integer>> sortedMap;
    ArrayList<String> compressed;
    char key = 126;

    DictCompress(HashMap<String, ArrayList<Integer>> dict){
        sortedMap = new TreeMap();
        sortedMap.putAll(dict);
    }
    DictCompress(){
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
        int pref = findPref(bl);
        String res = bl[0].substring(0, pref)+"*"+bl[0].substring(pref);
        for (int i = 1; i < bl.length; i++) {
            res+=(bl[i].length()-pref);
            res+=key+bl[i].substring(pref);
        }
        compressed.add(res);
    }

    int findPref(String[] s) {
        int min_size = s[0].length();
        for (int j = 1; j < s.length; j++) {
            if(s[j].length()<min_size)min_size=s[j].length();
        }
        for (int i = 0; i < min_size; i++) {
            boolean same = false;
            for (int j = 1; j < s.length; j++) {
                if(s[0].charAt(i)!=s[j].charAt(i))same=!same;
            }
            if (same)return (i);
        }
        return 0;
    }

}
