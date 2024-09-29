import vn.edu.tdtu.ArrayOutput;
import vn.edu.tdtu.ArrayHandler;

public class Ex2 {
    public static void main(String[] args) {
        int a[] = {12, 2, 34, 5, 6};
        int b[] = {38 ,3, 7, 1, 43, 45};
        ArrayOutput.print(a);
        ArrayOutput.print(b);

        int c[] = ArrayHandler.merge(a, b);
        ArrayOutput.print(c);

        ArrayHandler.sort(c);
        ArrayOutput.print(c);
        ArrayOutput.write(c, "output.txt");
    }
}