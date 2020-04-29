import java.util.concurrent.*;

public class Elfish {
    public static void main(String[]args) {
        ElfTask t = new ElfTask("dont");
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(t));
    }
}

class ElfTask extends RecursiveTask<Boolean> {
    private String str;
    private int[] elf;
    private int low, high;

    ElfTask(String s) {
        str = s;
        low = 0;
        high = s.length()-1;
        int[] a = {0,0,0};
        elf = a;
    }

    ElfTask(String s, int lo, int hi, int[]a) {
        str = s;
        low = lo;
        high = hi;
        elf = a;
    }

    public Boolean compute(){
        if (low == high) {
            if(str.charAt(low) == 'e')
                elf[0]++;
            else if(str.charAt(low) == 'l')
                elf[1]++;
            else if(str.charAt(low) == 'f')
                elf[2]++;

            return (elf[0] > 0) && (elf[1] > 0) && (elf[2] > 0);
        } else {
            ElfTask left = new ElfTask(str, low, low + (high-low)/2, elf);
            ElfTask right = new ElfTask(str, low + (high-low)/2 + 1, high, elf);

            left.fork();
            right.fork();

            return left.join() || right.join();
        }
    }
}

