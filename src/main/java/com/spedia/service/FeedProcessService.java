package com.spedia.service;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.spedia.dao.ContentDao;
import com.spedia.model.Content;
import com.spedia.model.ContentBody;
import com.spedia.utils.SEOURLUtils;

public class FeedProcessService extends TimerTask {

	private static final String NEWS_GOOGLE = "https://news.google.de/news/feeds?pz=1&cf=all&ned=de&hl=de&q=nursery admission delhi&output=rss";
	public static ContentDao contentDao;
	public FeedProcessService(){
		
	}
			
	public FeedProcessService(ContentDao contentDao2) {
		this.contentDao=contentDao2;
		TimerTask task = new FeedProcessService();
    	Timer timer = new Timer();
    	//run every hour
    	timer.schedule(task,1000,60000*60);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL(NEWS_GOOGLE);
			Feed feed = FeedParser.parse(url);
			System.out.println("** HEADER **");
			FeedHeader header = feed.getHeader();
			System.out.println("Title: " + header.getTitle());
			System.out.println("Link: " + header.getLink());
			System.out.println("Description: " + header.getDescription());
			System.out.println("Language: " + header.getLanguage());
			System.out.println("PubDate: " + header.getPubDate());

			System.out.println("** ITEMS **");
			int items = feed.getItemCount();
			for (int i = 0; i < items; i++) {
				FeedItem item = feed.getItem(i);
				System.out.println("Id: " + item.getGUID());
				Content content=new Content();
				content.setRid(item.getGUID());
				ContentBody body=new ContentBody();
				body.setValue(item.getDescriptionAsHTML());
				body.setSummary(item.getDescriptionAsText());
				content.setBody(body);
				content.setTitle(item.getTitle());
				content.setType("nursery_admission_news");
				String seourl = SEOURLUtils.getSEOURL(content.getType(),
						content.getTitle());
				content.setAlias(seourl);
				System.out.println(seourl);
				Long time = SEOURLUtils.getCurrentTime();
				content.setChanged(time);
				content.setCreated(time);
				if (content.getStatus() == 0) {
					content.setChanged(time);
				}
				contentDao.saveContent(content);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
