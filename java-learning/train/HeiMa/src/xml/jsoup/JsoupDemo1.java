package xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupDemo1 {
    public static void main(String[] args) throws IOException {
//        String path = JsoupDemo1.class.getClassLoader().getResource("xml/dtd/student.xml").getPath();
        Document document = Jsoup.parse(new File("src/xml/dtd/student.xml"), "utf-8");
        Elements names = document.getElementsByTag("name");
        for (Element name : names)
            System.out.println(name.text());

        Document document1 = Jsoup.parse(new URL("https://www.baidu.com"), 5000);
        System.out.println(document1);
    }
}
