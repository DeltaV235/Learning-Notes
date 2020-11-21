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
