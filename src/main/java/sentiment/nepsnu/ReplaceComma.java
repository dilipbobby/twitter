package sentiment.nepsnu;

public class ReplaceComma {

	public static void main(String args []){
		
		String txt="when you're proud of something and nobody cares 😭";
 //String txt="When I'm bored, I eat. When I'm happy, I eat. When I'm sad, I eat.";///error
//i like turtles because they're so chill. They don't hurt anyone. They're just like, "Hey man, I want to swim, and maybe eat some lettuce."
		//String txt="@russianForGump Come on! Is it "+"cable channel.";
		 txt = txt.replaceAll("[^\\x00-\\x7f-\\x80-\\xad]", "");
		//String a=txt.replaceAll("[.!]", ",");
		 String b="changes outfit 27 times* *wears first outfit*";
		System.out.println(b);
	}
}
