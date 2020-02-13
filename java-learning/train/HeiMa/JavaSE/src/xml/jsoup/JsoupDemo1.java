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
        /*
            由于项目路径中出现空格的原因，导致path路径不能正确地创建File对象，所以后续Jsoup测试使用user.dir相对路径直接构建File
            对象。下方的方法会将路径中的空格变为"%20"，导致后续的File对象不能正确创建，使用String.replace()替换"%20"为空格后，
            可以正常运行。
            user.dir为项目的根目录，不是module根目录，所以此处的user.dir是HeiMa/，File使用user.dir相对路径来创建。
        */
        String path = JsoupDemo1.class.getClassLoader().getResource("xml/dtd/student.xml").getPath();
        path = path.replace("%20", " ");
        System.out.println(path);
        Document document = Jsoup.parse(new File(path), "utf-8");
        Elements names = document.getElementsByTag("name");
        for (Element name : names)
            System.out.println(name.text());

        Document document1 = Jsoup.parse(new URL("https://www.baidu.com"), 5000);
        System.out.println(document1);
    }
}
