package archive;

public class String_builder {
    public static void main(String[] args) {
        StringBuilder build = new StringBuilder();
        build.append("testString");
        build.append("String2");
        build.append(" hahah");
        System.out.println(build.toString() + "build length: " + build.length());
    }
}
