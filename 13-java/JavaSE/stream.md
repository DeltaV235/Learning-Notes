# Stream Notes

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

## stream

**stream** 是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
> 集合讲的是数据，流讲的是计算

- **stream** 自己不会存储元素
- **stream** 不会改变源对象。相反，他们会返回一个持有结果的新 **stream**。
- **stream** 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

**stream 的三个阶段**:

1. 创建一个Stream：一个数据源（数组、集合）
2. 中间操作：一个中间操作，处理数据源数据
3. 终止操作：一个终止操作，执行中间操作链，产生结果

```java
/**
 * 输出 List 中 id 为偶数、age > 24、按 userName 倒数排列的第一个元素的 userName 大写
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2020/11/21 11:28
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 22);
        User u2 = new User(12, "b", 23);
        User u3 = new User(13, "c", 24);
        User u4 = new User(14, "d", 25);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
                .map(user -> user.getUserName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private Integer id;
        private String userName;
        private int age;
    }
}
```

### filter(Predicate<? super T>)

每个 **stream** 中的元素作为 **Predicate** 方法的入参，返回 **true** 则将该元素输出至下一个操作

### map(Function<? super T, ? extends R>)

**Function** 方法的入参类型为前一个 **stream** 的出参类型，出参类型为实际的 **return** 对象的类型

### sorted(Comparator<? super T>)

对入参进行自定义规则的排序

### limit

同 **SQL** 的 **limit startIndex** 配合 **skip** 可以达到 **limit startIndex rowNum** 的效果

### skip

跳过指定个元素

### forEach(Consumer<? super T>)

对 **stream** 中的元素执行 **Consumer** 接口中的操作

### collect(Collector<? super T, A, R>)

```java
List<String> collect = list.stream()
        .filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
        .map(user -> user.getUserName().toUpperCase())
        .sorted(Comparator.reverseOrder())
        .limit(1).collect(Collectors.toList());
```

将 **stream** 中的元素转为 **List**
