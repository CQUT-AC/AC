package emotion;

import lycode.NlpirTest;

public class Bayes {
	
	public static boolean Judge(String line){
		String str = NlpirTest.ParagraphProcess(line, 0);
		System.out.println(str);
		String[] strs = str.split(" ");
		for(String word : strs){
			if(wordFre.wordset.contains(word)){
				System.out.println(word);
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		wordFre.getEmotionWord();
		Judge("我今天很开心，因为去了KTV");
	}
}
