package API;

import lycode.NlpirTest;

public class GetKeyWord_API {
	static private String res = "";
	
	//得到关键词
	public static String getkeyword(String words){
		res = NlpirTest.GetKeyWord(words);
		return res;
	}
	
	//重置
	public static void reset(){
		res = "";
	}
}
