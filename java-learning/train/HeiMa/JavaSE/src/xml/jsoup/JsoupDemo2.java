package xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * 测试Jsoup中Document对象的方法
 *
 * @author DeltaV235
 */
public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("src/xml/xsd/student.xml"), "utf-8");
        Elements students = document.getElementsByTag("student");
        System.out.println(students);
        System.out.println("----------------------------------");

        Elements id = document.getElementsByAttribute("id");
        System.out.println(id);
        System.out.println("----------------------------------");

        Elements elementsByAttributeValue = document.getElementsByAttributeValue("number", "heima_0002");
        System.out.println(elementsByAttributeValue);
        System.out.println("----------------------------------");

        Element elementById = document.getElementById("1");
        System.out.println(elementById);
    }
}
