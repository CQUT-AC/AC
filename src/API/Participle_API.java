package API;

import lycode.NlpirTest;

public class Participle_API {
	static private String res = "";
	
	//���õķִʺ���
	public static String participle(String words){
		res = NlpirTest.ParagraphProcess(words);
		return res;
	}
	
	//�õ���Ӧ�Ĵ�
	public static String getTypeWord(String type){
		StringBuffer sb = new StringBuffer();
		String[] strs = res.split(" ");
		String flog = null;
		for(String str : strs){
			flog = str.substring(str.indexOf("/")+1);
			//System.out.println(flog);
			if(flog.startsWith(type)){
				str = str.substring(0,str.indexOf("/"));
				sb.append(str + " ");
			}
		}
		return sb.toString();
	}
	
	//����
	public static void reset(){
		res = "";
	}
}
