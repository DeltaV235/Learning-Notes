# Stream

## chain

返回类型为本类，从而实现方法的链式调用。

**Lombok** 可以通过 **@Accessor** 注解实现。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Book {
    private double price;
    private String bookName;
    private String author;
}
```

call:

```java
public class ChainDemo {
    public static void main(String[] args) {
        Book book = new Book();
        book.setAuthor("deltaV").setBookName("fuck the world").setPrice(998);
        System.out.println("book.toString() = " + book.toString());
    }
}
```

等价于

```java
public class Book {
    private double price;
    private String bookName;
    private String author;

    public Book(double price, String bookName, String author) {
        this.price = price;
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {
    }

    public double getPrice() {
        return price;
    }

    public Book setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
```

## JDK 的函数式接口

|         函数式接口         | 参数类型 | 返回类型 |                             用途                             |
| :------------------------: | :------: | :------: | :----------------------------------------------------------: |
|  Consumer\<T> 消费型接口   |    T     |   void   |    对类型为 T 的对象应用操作，包括方法：void accept(T t)     |
|  Supplier\<T> 供给型接口   |    无    |    T     |            返回类型为 T 的对象，包含方法：T get()            |
| Function\<T, R> 函数型接口 |    T     |    R     | 对象类型为 T 的对象应用操作，并返回结果。结果是 R 类型的对象。包含方法：R apply(T t) |
|  Predicate\<T> 断定型接口  |    T     | boolean  | 确定类型为 T 的对象是否满足某约束，并返回 boolean 值。包含方法：boolean test(T t) |

```java
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Function<Integer, String> function = input -> input + "-demo";
        System.out.println("function.apply(20201121) = " + function.apply(20201121));

        Predicate<Integer> predicate = integer -> integer > 5;
        System.out.println("predicate.test(6) = " + predicate.test(6));

        Consumer<String> consumer = s -> System.out.println("s = " + s);
        consumer.accept("Hello World!");

        Supplier<String> supplier = () -> "Apple Silicon";
        System.out.println("supplier.get() = " + supplier.get());
    }
}
```

**Lambada** 表达式是一种对方法代码的描述方式，`()->{}` 中定义形参名和函数式方法的实现代码，等价于匿名内部类。函数式接口的实例化对象相当于该方法的引用。
