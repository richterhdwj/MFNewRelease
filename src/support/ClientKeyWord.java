/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author hdwjy
 */
public class ClientKeyWord {

    static String url = "http://www.mediafactory.co.jp/bunkoj/top/index#new_release";
    static String orgUrl="http://www.mediafactory.co.jp";

    public static void main(String[] arg) throws Exception {
        getUrl(url);
    }

    private final static void getUrl(String url) {
        Document doc = null;
        try {
            doc = (Document) Jsoup.connect(url).get();
        } catch (Exception e) {
        }
        //获取标题
        Element titles = doc.getElementById("new_pub");
        Elements titleElement = titles.getElementsByTag("H2");
        for (Element next : titleElement) {
            System.out.println(next.text());
        }

        //获取内容
        Elements contactElement = titles.getElementsByTag("a");
        for (Element nextLink : contactElement) {
            String link = nextLink.attr("href");
            Elements contactElementImg = nextLink.getElementsByTag("img");
            for (Element next : contactElementImg) {
                String title = next.attr("alt");
                String img = next.attr("src");
                if (title.isEmpty()) {
                    continue;
                }
                System.out.println(title);
                System.out.println(orgUrl+link);
                System.out.println(img);
            }
        }
    }
}
