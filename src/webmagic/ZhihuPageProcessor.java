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
    	String cookie = "q_c1=5b4bcd16dcd34af0944a211b6df7beea|1526374474000|1526374474000; _zap=71a5907d-782f-4b21-a38f-1d045da5805f; tgw_l7_route=7139e401481ef2f46ce98b22af4f4bed; _xsrf=a7b45c88-5207-4c61-a9b9-16c65396fff2; capsion_ticket=2|1:0|10:1526379929|14:capsion_ticket|44:YTAxNzU1NjFlNWNmNDNlYjgxODM5YWQ1M2I2NDBhZDk=|6e7245144d71b996cfea4df61d892b819d834c0de3c8ac31096afd67e6c21909; z_c0=2|1:0|10:1526379946|4:z_c0|80:MS4xcl9oaUJ3QUFBQUFtQUFBQVlBSlZUYW9ENkZ2bHBZTkZibk45di11aU03WDAwNGFreV9EQTBnPT0=|044950aece49cbae2fc0565afdc728fec541ff809f5e4cf24b64e457ed07a2f7";
        System.setProperty("selenuim_config", "D:\\workspace2\\mvnTest\\config.ini");
        Spider.create(new ZhihuPageProcessor()).addUrl("https://www.zhihu.com/question/38515926").addPipeline(new MyFilePipeline("D:\\workspace2\\mvnTest"))
        .downloader(new MySeleniumDownloader("D:\\workspace2\\mvnTest\\chromedriver.exe"))
        .run();
    }
}

