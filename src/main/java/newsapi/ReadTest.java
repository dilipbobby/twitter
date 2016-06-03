package newsapi;

public class ReadTest {
	  public static void main(String[] args) {
	  //  RSSFeedParser parser = new RSSFeedParser("http://www.vogella.com/article.rss");
		  RSSFeedParser parser = new RSSFeedParser("http://news.google.com/news?q=apple&output=rss");
	    Feed feed = parser.readFeed();
	    System.out.println(feed);
	    for (FeedMessage message : feed.getMessages()) {
	      System.out.println(message);
  }

	  }
	} 

