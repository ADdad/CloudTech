

public class Test
{

    public static void main(String[] args) {
        System.out.println("Available memory: "+ Runtime.getRuntime().freeMemory());
        long l = System.currentTimeMillis();
        Collection c = new Collection("C:\\dict");
        SPIMI s = new SPIMI(c.getCollection());
        long l2 = System.currentTimeMillis();
        System.out.println("Blocked: "+(l-l2));
      Merge m = new Merge("C:\\blocks");
        long l3 = System.currentTimeMillis();
        System.out.println("Merged: "+(l2-l3));
        System.out.println("Total time: "+(l-l3));

    }
}
