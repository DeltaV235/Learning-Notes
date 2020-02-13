package xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 测试Document对象的Select方法
 *
 * @author DeltaV235
 */
public class JsoupDemo3 {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("src/xml/xsd/student.xml"), "utf-8");
        Elements select = document.select("student[number=\"heima_0001\"] > sex");
        System.out.println(select.text());
    }
}
