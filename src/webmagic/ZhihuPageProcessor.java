package webmagic;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.6.0
 */
public class ZhihuPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private static int i = 0;
    @Override
    public void process(Page page) {
//      page.addTargetRequests(page.getHtml().links().regex("https://www\\.zhihu\\.com/question/\\d+.*").all());
/*      page.putField("title", page.getHtml().xpath("//h2[@class='zm-item-title']/a/text()").toString());
        page.putField("question", page.getHtml().xpath("//div[@id='zh-question-detail']//tidyText()").toString());
        page.putField("answer", page.getHtml().xpath("//div[@id='zh-question-answer-wrap']//div[@class='zm-editable-content']/tidyText()").toString());
*/     
       String title = "";
       List<String> list = new ArrayList<>();
       for(String data : page.getHtml().xpath("//div[@class='ContentItem AnswerItem']/@data-zop").all()){
    	   JSONObject da = JSONObject.parseObject(data);    	 
    	   list.add((String)da.get("authorName"));
    	   title = (String)da.get("title");
       }
       page.putField("title", title);
       page.putField("autor", list);
//     page.putField("answer", page.getHtml().xpath("//body//a[1]").toString());
       
       List<String> urllist = new ArrayList<>();
       for(String data : page.getHtml().xpath("//div[@class='SimilarQuestions-item']//meta[@itemprop='url']/@content").all()){
    	   urllist.add(data);
       }
   	   page.addTargetRequests(urllist);
           
	}

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.setProperty("selenuim_config", "D:\\workspace2\\mvnTest\\config.ini");
        Spider.create(new ZhihuPageProcessor()).addUrl("https://www.zhihu.com/question/38515926").addPipeline(new MyFilePipeline("D:\\workspace2\\mvnTest"))
        .downloader(new MySeleniumDownloader("D:\\workspace2\\mvnTest\\chromedriver.exe"))
        .run();
    }
}

