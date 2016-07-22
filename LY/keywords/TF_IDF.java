package keywords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lycode.NlpirTest;



public class TF_IDF {
	private static Map<String, Node> map = new TreeMap<String, Node>();
	
	private static Set<String> set = new TreeSet<String>();
	
	private static void getAllwords(String line){
		line = NlpirTest.ParagraphProcess(line, 1);
		String[] strs = line.split(" ");
		for(String word : strs){
			set.add(word);
			System.out.println(word);
		}
	}
	
	private static void dealstring(String line){
		line = NlpirTest.ParagraphProcess(line, 1);
		String[] strs = line.split(" ");
		for(String word : strs){
			if(map.containsKey(word)){
				map.get(word).setAllTimes();
			}
			else {
				map.put(word, new Node(1, 0));
			}
		}
	}
	
	private static void readTxt(File file){
		System.out.println(set.size());
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = br.readLine())!=null){
				getAllwords(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
	
	private static void showAllFiles(File dir){
		System.out.println(set.size());
		File[] fs = dir.listFiles();
		for(int i=0; i<fs.length; i++){
//			System.out.println(fs[i].getAbsolutePath());
			if(fs[i].isDirectory()){
				showAllFiles(fs[i]);
			}else {
				readTxt(fs[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		File root = new File("E:\\Èí¼þ4\\·Ö´Ê\\train");
		showAllFiles(root);
		System.out.println(set.size());
	}
}

class Node{
	private int allTimes;
	private int txtTimes;
	
	public int getAllTimes() {
		return allTimes;
	}

	public void setAllTimes() {
		this.allTimes++;
	}

	public int getTxtTimes() {
		return txtTimes;
	}

	public void setTxtTimes() {
		this.txtTimes++;
	}

	public Node(int allTimes, int txtTimes){
		this.allTimes = allTimes;
		this.txtTimes = txtTimes;
	}
}
