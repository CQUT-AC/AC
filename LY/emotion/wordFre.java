package emotion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class wordFre {
	public static Map<String, Node> map = new HashMap<String, Node>();
	public static Set<String> wordset = new HashSet<String>();
	public static int flog = 0;
	
	//统计词在出现消极，和积极的词频
	public static void wordtime(String word, String line){
		Node node = null;
		String[] strs = null;
		strs = line.split(word);
		if(flog == 0){
			if (map.containsKey(word)) {
				map.get(word).neg += (strs.length-1);
			}else {
				node = new Node();
				node.neg = strs.length - 1;
				map.put(word, new Node());
			}
		}
		else {
			if (map.containsKey(word)) {
				map.get(word).pos += (strs.length-1);
			}else {
				node = new Node();
				node.pos = strs.length - 1;
				map.put(word, new Node());
			}
		}
		Node.allwordsTime += strs.length -1;
	}
	
	/*
	 * 统计wordset中所有的词的词频
	 */
	public static void wordSettime(String line){
		for(String word : wordset){
			wordtime(word,  line);
		}
	}
	
	/*
	 * 读取txt中的内容，放在一个string中，适用短文本
	 */
	public static void readTxt(File file){
        try  
        {  	
        	StringBuffer sb = new StringBuffer();
            BufferedReader bw = new BufferedReader(new FileReader(file));  
            String line = null;
            while ((line = bw.readLine()) != null)  
            {  	
            	line = line.trim();
            	if(!line.equals(""))
            		sb.append(line);
            }
            line = sb.toString();
            wordSettime(line);
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }
	}
	
	/*
	 * 读取文件夹中的所有txt; 使用递归读取所有子集文件夹的的txt
	 */
	public static void readfiles(String path){
		File file=new File(path);
		File[] tempList = file.listFiles();
//		System.out.println("该目录下对象个数："+tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				readTxt(tempList[i]);
			}
//			if (tempList[i].isDirectory()) {
//				System.out.println("文件夹："+tempList[i]);
//			}
		}
	}
	
	/*
	 * 读取所有的情感词
	 */
	public static void getEmotionWord(){
		File[] files = new File[2];
		files[0] = new File("LYdata//emotionData//negDic.txt");
		files[1] = new File("LYdata//emotionData//posDic.txt");
		try  
        {  	
        	for(File file: files){
        		BufferedReader bw = new BufferedReader(new FileReader(file));  
                String line = null;
                while ((line = bw.readLine()) != null)  
                {  	
                	line = line.trim();
                	wordset.add(line);
                }
        	}
            
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }
	}
	
	/*
	 * 计算词的概率
	 */
	public static void culateRate(){
		for(Map.Entry<String, Node> entry : map.entrySet()){
			Node node = entry.getValue();
			if(0 != node.neg || 0 != node.pos ){
				Node.Max = Math.max(Node.Max, node.neg);
				Node.Max = Math.max(Node.Max, node.pos);
			}
		}
		
		for(Map.Entry<String, Node> entry : map.entrySet()){
			Node node = entry.getValue();
			if(0 != node.neg || 0 != node.pos ){
				node.rateNeg = (double)node.neg / Node.Max;
				node.ratePos = (double)node.pos / Node.Max;
			}
		}
	}
	
	/*
	 * 输出到emotionData//mapOut.txt文件
	 */
	public static void prnMap(){
		File file = new File("LYdata//emotionData//mapOut.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(Node.allwordsTime + "\r\n");
			bw.write("word\tneg\tpos\trateNeg\tratePos\r\n");
			for(Map.Entry<String, Node> entry : map.entrySet()){
				Node node = entry.getValue();
				if(0 != node.neg || 0 != node.pos ){
					bw.write(entry.getKey() + node.toString() + "\r\n");
				}
			}
			bw.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {		
		getEmotionWord();
		flog = 0;
		readfiles("emotionData/neg");
		flog = 1;
		readfiles("emotionData/pos");
		culateRate();
		prnMap();
	}
}

class Node{
	public static int allwordsTime = 0;
	public static int Max = 0;
	public int neg = 0;
	public int pos = 0;
	public double rateNeg = 0;
	public double ratePos = 0;
	
	@Override
	public String toString() {
		return "\t" +neg + "\t" + 
	pos + "\t" + rateNeg + "\t" + ratePos;
	}
}