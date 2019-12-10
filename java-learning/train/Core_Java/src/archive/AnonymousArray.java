package archive;

public class AnonymousArray {
    public static void main(String[] args) {
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            System.out.printf("%-4d\n",i);
        }
    }
}
