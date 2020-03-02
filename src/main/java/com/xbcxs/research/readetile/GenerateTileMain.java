package com.xbcxs.research.readetile;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.SysexMessage;
import java.io.IOException;

import static sun.util.logging.LoggingSupport.log;

public class GenerateTileMain {

    private static Logger log = LoggerFactory.getLogger(GenerateTileMain.class);

    public static void main(String[] args) throws IOException {
        String github_url = "https://github.com";
        Document firstLevelDoc = Jsoup.connect(github_url + "/xbcxs/xbcxs.github.io").get();
        Elements svgElements = firstLevelDoc.select("svg[aria-label=directory][class=octicon octicon-file-directory][role=img]");
        Element firstLevelAElement;
        Document secondLevelDoc;
        Elements secondLevelAElements;
        System.out.println("<html>");
        for (Element svgElement : svgElements) {
            firstLevelAElement = svgElement.parent().nextElementSibling().select("a[class=js-navigation-open]").get(0);
            if(!firstLevelAElement.text().contains("resources")){
                System.out.println("<a href=" + github_url + firstLevelAElement.attr("href") + ">" + firstLevelAElement.text() + "</a></br>");
                secondLevelDoc = Jsoup.connect(github_url + firstLevelAElement.attr("href")).get();
                secondLevelAElements = secondLevelDoc.select("a[class=js-navigation-open][rel!=nofollow]");
                for(Element secondLevelAElement : secondLevelAElements){
                    System.out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=" + github_url + secondLevelAElement.attr("href") + " style=font-size:14px" + ">" + secondLevelAElement.text() + "</a></br>");
                }
            }
        }
        System.out.println("</html>");
    }

    private static void httpTest() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet("https://github.com/xbcxs/xbcxs.github.io");
        // 设置长连接
        // httpGet.setHeader("Connection", "keep-alive");
        // 设置代理（模拟浏览器版本）
        // httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        // 设置 Cookie
        // httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");

        CloseableHttpResponse httpResponse = null;
        try {
            // 请求并获得响应结果
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            System.out.println(EntityUtils.toString(httpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 无论如何必须关闭连接
        finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
