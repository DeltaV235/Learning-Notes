package xml.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 测试Jsoup的XPath
 *
 * @author DeltaV235
 */
public class JsoupDemo4 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        System.out.println(System.getProperty("user.dir"));
        Document document = Jsoup.parse(new File("JavaSE/src/xml/xsd/student.xml"), "utf-8");
        JXDocument jxDocument = new JXDocument(document);
        List<JXNode> jxNodes = jxDocument.selN("//name");
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode.getElement().text());
        }
        System.out.println("----------------------------");
        List<JXNode> jxNodes1 = jxDocument.selN("//student[@number='heima_0001']/name[@id]");
        for (JXNode jxNode : jxNodes1) {
            System.out.println(jxNode.getElement().text());
        }
    }
}
