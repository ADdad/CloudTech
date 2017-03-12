import java.nio.ByteBuffer;
import java.util.*;

import static java.lang.StrictMath.log;

/**
 * Created by rokde on 12.03.2017.
 */
public class CompressIndex {
    SortedMap<String, ArrayList<Integer>> sortedMap;
    HashMap<String, byte[]> comIndex;


    CompressIndex(HashMap<String, ArrayList<Integer>> index){
        sortedMap = new TreeMap();
        sortedMap.putAll(index);
        comIndex = new HashMap<>();
    }

    void encodeArray(){
        for(Map.Entry<String,ArrayList<Integer>> entry : sortedMap.entrySet()) {
            ArrayList<Integer> temp = entry.getValue();
            ArrayList<Integer> dec = new ArrayList<>();
            dec.add(temp.get(0));
            for (int i = 1; i < temp.size(); i++) {
                dec.add(temp.get(i)-dec.get(i-1));
            }
            comIndex.put(entry.getKey(),encode(dec));
        }
    }



     private static byte[] encodeNumber(int n) {
        if (n == 0) {
            return new byte[]{0};
        }
        int i = (int) (log(n) / log(128)) + 1;
        byte[] rv = new byte[i];
        int j = i - 1;
        do {
            rv[j--] = (byte) (n % 128);
            n /= 128;
        } while (j >= 0);
        rv[i - 1] += 128;
        return rv;
    }

    public static byte[] encode(ArrayList<Integer> numbers) {
        ByteBuffer buf = ByteBuffer.allocate(numbers.size() * (Integer.SIZE / Byte.SIZE));
        for (Integer number : numbers) {
            buf.put(encodeNumber(number));
        }
        buf.flip();
        byte[] rv = new byte[buf.limit()];
        buf.get(rv);
        return rv;
    }

    public static ArrayList<Integer> decode(byte[] byteStream) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        int n = 0;
        for (byte b : byteStream) {
            if ((b & 0xff) < 128) {
                n = 128 * n + b;
            } else {
                int num = (128 * n + ((b - 128) & 0xff));
                numbers.add(num);
                n = 0;
            }
        }
        return numbers;
    }
}
