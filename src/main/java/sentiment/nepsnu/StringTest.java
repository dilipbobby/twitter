package sentiment.nepsnu;


	public class StringTest {

	    public static void main(String args[]) {

	        String text = "RT @AshStewart09: Vote for Lady Gaga for \"Best Fans\""
	                + " at iHeart Awards\n"
	                + "\n"
	                + "RT!!\n"
	                + "\n"
	                + "My vote for #FanArmy goes to #LittleMonsters #iHeartAwards"
	                + " htt…";

	        String[] hashtags = {"#FanArmy", "#LittleMonsters", "#iHeartAwards"};
	        System.out.println("Before: " + text + "\n");

	        // Delete all usernames mentioned (may run multiple times)
	        text = text.replaceAll("@AshStewart09", "");
	        System.out.println("First Phase: " + text + "\n");

	        // Delete all RT (retweets flags)
	        text = text.replaceAll("RT", "");
	        System.out.println("Second Phase: " + text + "\n");

	        // Delete all hashtags mentioned
	        for (String hashtag : hashtags) {
	            text = text.replaceAll(hashtag, "");
	        }
	        System.out.println("Third Phase: " + text + "\n");

	        // Replace all break lines with spaces
	        text = text.replaceAll("\n", " ");
	        System.out.println("Fourth Phase: " + text + "\n");

	        // Replace all double spaces with single spaces
	        text = text.replaceAll(" +", " ");
	        System.out.println("Fifth Phase: " + text + "\n");

	        // Delete all special characters except spaces 
	        text = text.replaceAll("[^a-zA-Z0-9 ]+", "").trim();
	        System.out.println("Finaly: " + text);
	    }
	}


