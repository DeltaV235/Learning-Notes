public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello Java!");
        String greeting = "Hello";
        String subString = greeting.substring(1, 4);
        System.out.println(subString);
        System.out.println(subString.join(".", "test1", "test2"));
        if ("hello".equals(greeting))
        {
           System.out.println("true");
        }
        else
            System.out.println("false");
    }
}
