package interfacePackage.interfaceTest;

import java.util.Arrays;
import java.util.Comparator;

public class SortUesComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

    private static String print(String[] strings) {
        StringBuilder sb = new StringBuilder("[");
        for (String s : strings) {
            sb.append("\"").append(s).append("\",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        SortUesComparator sort = new SortUesComparator();
        String[] s1 = {"gotoSomeWhere", "WY", "Alien", "Ken", "Paul"};

        System.out.println(print(s1));
        Arrays.sort(s1, sort);
        System.out.println(print(s1));
    }
}
