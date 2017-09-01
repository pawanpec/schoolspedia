import java.io.File;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public final class ForumMigration {
	public static final String mongoHost = "127.0.0.1";// for qc
	public static Mongo mongo;
	public static DBCollection node;
	public static DB db;
	static{
		
		try {
			mongo = new Mongo(mongoHost, 27017);
			db = mongo.getDB("spedia");
			node = db.getCollection("fb_group");
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public final static String uid = "7a361202-da6c-40fd-83e8-e2385d6899f6";
	public final static String auth_token="Authorization: Bearer "+uid;
	public static void main(String[] args) throws Exception {
		/*
		 52659,6,Bangalore,Nursery,Admission
52671,7	Nursery Admission Calcutta
52658,8	Nursery Admission Chandigarh
52652,9	Nursery Admission Chennai
42999,10 Nursery Admission	Delhi
90177,10,Delhi,Nursery,Admission
52698,11,,Nursery Admission Hyderabad
52672,12, Nursery Admission Mumbai
43000,13, Nursery Admission Noida
43083,5,,nursery,admission
43002,14,Nursery Admission Faridabad
43001,15,Nursery Admission Gurgaon
52689,16,Nursery Admission Pune
,,,,,17,,,FAQ
		 */
		Map<Integer,Integer>  catMap=new HashMap<Integer,Integer>();
		catMap.put(52659,6);
		catMap.put(52671,7);
		catMap.put(52658,8);
		catMap.put(52652,9);
		catMap.put(42999,10);
		catMap.put(90177,10);
		catMap.put(52698,11);
		catMap.put(52672,12);
		catMap.put(43000,13);
		catMap.put(43083,5);
		catMap.put(43002,14);
		catMap.put(43001,15);
		catMap.put(52689,16);
		int i=0;
		Set<String> cids=new HashSet<String>();
			for (int j = 0; j < 600;) {
				DBCursor dbuCursor = node.find().skip(j).limit(100);
				j=j+100;
				while (dbuCursor.hasNext()) {
					DBObject nodeObject = dbuCursor.next();
					if (nodeObject != null) {
						String title = (String) nodeObject.get("message");
						String content = title;
						String cid = "10";//nursery admission
					/*	if (nodeObject.containsField("body_value")) {
							content = (String) nodeObject.get("body_value");
						}
						if (nodeObject.containsField("taxonomy_forums_tid")) {
							Integer tid = (Integer) nodeObject
									.get("taxonomy_forums_tid");
							Integer c = catMap.get(tid);
							if (c != null) {
								cid = c + "";
							} else {
								cid = "17";//FAQ
							}
						}*/
						cids.add(cid);
						if (title!=null) {
							String data = prepareData(title, content, cid);
							System.out.println(title);
							sendPost(data,i);
							Thread.sleep(100);
							i++;
						}
					}
				}
			}
		System.out.println(" cid "+cids);
		System.out.println("done "+i);
	}


	private static String prepareData(String title,String content,String cid) {
		String data="title="+
				title+
				"&content="
				+content+
				"&cid="+cid
				+ "&_uid="+uid;
		return data;
	}

	
		// HTTP POST request
	private static void sendPost(String data,int i){
		
			/*
			 * /usr/bin/curl -H "Authorization: Bearer 99df5b53-6fc0-4d3b-a3f8-99df2ed42ba4" --data "title=My child faces lot of difficulty in learning and retaining that learning. How can I help to improve his learning?&content=Firstly you can help her to concentrate on preparation rather than worrying about marks. Help her in understanding concepts, can ask your child to regularly review the time table and make changes in light according to progress. You can encourage child to give you short tests during the revision.&cid=17&_uid=99df5b53-6fc0-4d3b-a3f8-99df2ed42ba4" http://test.schoolspedia.com/forum/api/v1/topics

			 */
				ProcessBuilder pb=new ProcessBuilder("curl","-H",
						auth_token,"--data",data
						,"http://schoolspedia.com/forum/api/v1/topics"); 
			    pb.directory(new File("/usr/bin/"));
				pb.redirectErrorStream(true);
				Process p=null;
				try {
					p = pb.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					/*if(p!=null){
						p.destroy();
					}
					*/
				}
			  if(i%10==0){
				if(p!=null){
					p.destroy();
				}
			}
				   
	}
	

}
