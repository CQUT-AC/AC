package API;

import lycode.NlpirTest;

public class GetKeyWord_API {
	static private String res = "";
	
	//�õ��ؼ���
	public static String getkeyword(String words){
		res = NlpirTest.GetKeyWord(words);
		return res;
	}
	
	//����
	public static void reset(){
		res = "";
	}
}
