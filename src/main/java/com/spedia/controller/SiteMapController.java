package com.spedia.controller;

import static com.spedia.utils.MongoConstants.FIELDS_CURRENT_NODE;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.SitemapIndexGenerator;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import com.spedia.dao.MongoDao;
import com.spedia.dao.MongoDaoImpl;
import com.spedia.model.Feed;
import com.spedia.model.FeedMessage;
import com.spedia.utils.RSSFeedWriter;
import com.spedia.utils.SocialUtility;

@Controller
public class SiteMapController {
	private static final int SITE_MAP_URL_LIMIT = 2000;
	@Autowired
	public static final String SITE_MAP_DIR_PATH = "/opt/tomcat/webapps/spedia/sitemap"; //"/var/www/";
	private static final String SITE_NAME = "http://schoolspedia.com/";
	@Autowired
	@Qualifier("mongoDao")
	private MongoDaoImpl mongoDao;

	@RequestMapping(value = { "/buildSiteMap.html" }, method = { RequestMethod.GET })
	public @ResponseBody
	String buildSiteMap() {
		final File siteMapFile = new File(SITE_MAP_DIR_PATH + "/sitemap.xml");
		DBObject basicDBObject=new BasicDBObject();
		DBCollection node = mongoDao.getMongoDatabase().getCollection(
				FIELDS_CURRENT_NODE);
		DBCursor dbCursor=node.find(basicDBObject);
		Integer totalURLS = dbCursor.size();
		String companySiteMap = "sp_sitemap";
		buildSPSiteMap(dbCursor, companySiteMap);
		SitemapIndexGenerator sig;
		try {
			sig = new SitemapIndexGenerator(SITE_NAME,
					siteMapFile);
			for (int i = 0; i <= totalURLS / SITE_MAP_URL_LIMIT;) {
				sig.addUrl(SITE_NAME + "spedia/sitemap/" + companySiteMap
						+ ++i + ".xml");
			}
			sig.write();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}

		return "done";
	}
	@RequestMapping(value = { "/buildRss.html" }, method = { RequestMethod.GET })
	public @ResponseBody
	String buildRss() {
		    String copyright = "Copyright hold by schoolspedia.com";
		    String title = "Schoolspedia Latest News";
		    String description = "Schoolspedia Latest News";
		    String language = "en";
		    String link = "http://schoolspedia.com";
		    Calendar cal = new GregorianCalendar();
		    Date creationDate = cal.getTime();
		    SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
		    String pubdate = date_format.format(creationDate);
		    Feed rssFeeder = new Feed(title, link, description, language,
		        copyright, pubdate);
		    DBObject query = new BasicDBObject();
			DBObject clause1 = new BasicDBObject("type", "nursery_admission");
			DBObject clause2 = new BasicDBObject("type", "nursery_admission_news");
			DBObject clause3 = new BasicDBObject("type", "summer_camp");
			DBObject clause4 = new BasicDBObject("type", "schools_news");
			DBObject clause5 = new BasicDBObject("type", "parents_tips");
			BasicDBList or = new BasicDBList();
			or.add(clause1);
			or.add(clause2);
			or.add(clause3);
			or.add(clause4);
			or.add(clause5);
			query = new BasicDBObject("$or", or);
		int limit=500;
		DBCursor dbCursor=mongoDao.getContent(query).limit(limit);
		int i=0;
		for (DBObject dbObject : dbCursor) {
		    // now add one example entry
		    try {
				FeedMessage feed = new FeedMessage();
				String _id=(Integer)dbObject.get("_id")+"";
				String stitle=(String)dbObject.get("title");
				String summary=stitle;
				if(dbObject.containsField("body")){
					 BasicDBObject body=(BasicDBObject)dbObject.get("body");
					    if(body.containsField("summary")){
					    	 summary=(String) body.get("summary");
					    }
				}
				String alias=(String)dbObject.get("alias");
				feed.setTitle(stitle);
				feed.setDescription(summary);
				feed.setLink(SITE_NAME+alias);
				feed.setGuid(_id);
				rssFeeder.getMessages().add(feed);
				i++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, "sp.rss");
		    try {
		      writer.write();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		return "done " +i;
	}


	public String buildSPSiteMap(DBCursor dbCursor,
			String fileName) {
		final File siteMapDir = new File(SITE_MAP_DIR_PATH);
		Date currenntDate = new Date();
		try {
			WebSitemapGenerator wsg = WebSitemapGenerator
					.builder(SITE_NAME, siteMapDir)
					.fileNamePrefix(fileName).maxUrls(SITE_MAP_URL_LIMIT)
					.build();
			for (DBObject dbObject : dbCursor) {
					String pageURL = (String) dbObject.get("alias");
					if(pageURL==null){
						continue;
					}
					try {
						if(pageURL.contains("discussion")){
							continue;
						}
						if(pageURL.contains("school-virtual-tour")){
							continue;
						}
						if(pageURL.contains("activities-and-campus-life")){
							continue;
						}
						if(pageURL.contains("distinguished-students")){
							continue;
						}
						if(pageURL.contains("hostel-residential-facilities")){
							continue;
						}
						if(pageURL.contains("transportation-facilities")){
							continue;
						}
						if(pageURL.contains("news-and-updates")){
							continue;
						}
						if(pageURL.contains("academic-sports-other-facilities")){
							continue;
						}
						if(pageURL.contains("houses-school")){
							continue;
						}
						if(pageURL.contains("extra-cirricular-activities")){
							continue;
						}
						if(pageURL.contains("school-alumni-details")){
							continue;
						}
						pageURL=SITE_NAME
								+ pageURL;
						System.out.println("Adding URL to sitemap "+pageURL);
						WebSitemapUrl url = new WebSitemapUrl.Options(
								pageURL).lastMod(currenntDate).priority(1.0)
								.changeFreq(ChangeFreq.DAILY).build();
						wsg.addUrl(url);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			wsg.write();
			wsg.writeSitemapsWithIndex();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "done";
	}


}
