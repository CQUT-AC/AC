package code;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量 这一个语句是来加载dll的，注意dll文件的路径可以是绝对路径也可以是相对路径，只需要填写dll的文件名，不能加后缀。
		CLibrary Instance = (CLibrary) Native.loadLibrary("lib//win64//NLPIR", CLibrary.class);
		// 初始化函数声明
		public int NLPIR_Init(byte[] sDataPath, int encoding,byte[] sLicenceCode);
		//执行分词函数声明
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);
		//提取关键词函数声明
		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,boolean bWeightOut);
		//退出函数声明
		public void NLPIR_Exit();
		
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);
				
		public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public int NLPIR_AddUserWord(String sWord);//add by qp 2008.11.10
		public int NLPIR_DelUsrWord(String sWord);//add by qp 2008.11.10
		public String NLPIR_GetLastErrorMsg();
	}
	/*
	 *分词
	 */
	public static String ParagraphProcess(String sInput){
		return ParagraphProcess(sInput, 1);
		
	}
	public static String ParagraphProcess(String sInput, int type){
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "GBK";
		int charset_type = 1;
		// int charset_type = 0;
		// 调用printf打印信息
		int init_flag = 0;
		try {
			init_flag = CLibrary.Instance.NLPIR_Init(argu
					.getBytes(system_charset), charset_type, "0"
					.getBytes(system_charset));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return null;
		}

		String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, type);

			CLibrary.Instance.NLPIR_Exit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nativeBytes;
	}
	
	/*
	 *关键词
	 */
	public static String GetKeyWord(String sInput){
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "GBK";
		int charset_type = 1;
		// int charset_type = 0;
		// 调用printf打印信息
		int init_flag = 0;
		try {
			init_flag = CLibrary.Instance.NLPIR_Init(argu
					.getBytes(system_charset), charset_type, "0"
					.getBytes(system_charset));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (0 == init_flag) {
			System.err.println("初始化失败！");
			return null;
		}

		String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 5, false);

			CLibrary.Instance.NLPIR_Exit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nativeBytes;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return;
		}

		String sInput = "据悉，质检总局已将最新有关情况再次通报美方，要求美方加强对输华玉米的产地来源、运输及仓储等环节的管控措施，有效避免输华玉米被未经我国农业部安全评估并批准的转基因品系污染。";

		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			System.out.println("分词结果为： " + nativeBytes);
			
			CLibrary.Instance.NLPIR_AddUserWord("要求美方加强对输 n");
			CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 0);
			System.out.println("增加用户词典后分词结果为： " + nativeBytes);
			
			CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("删除用户词典后分词结果为： " + nativeBytes);
			
			
			int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			//nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10,false);

			System.out.print("关键词提取结果是：" + nativeByte);

			

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
	
	
	
	
//	public static void main(String[] args) throws Exception {
//		String argu = "";
//		// String system_charset = "GBK";//GBK----0
//		String system_charset = "GBK";
//		int charset_type = 1;
//		// int charset_type = 0;
//		// 调用printf打印信息
//		int init_flag = CLibrary.Instance.NLPIR_Init(argu
//				.getBytes(system_charset), charset_type, "0"
//				.getBytes(system_charset));
//
//		if (0 == init_flag) {
//			System.err.println("初始化失败！");
//			return;
//		}
//
//		String sInput = "东方网12月4日消息：2009年10月21日,辽宁省阜新市委收到举报信,举报以付玉红为首吸毒、强奸、聚众淫乱,阜新市委政法委副书记于洋等参与吸毒、强奸、聚众淫乱等。对此,阜新市委高度重视,责成阜新市公安局立即成立调查组,抽调精干力量展开调查。　　调查期间,署名举报人上官宏祥又通过尹东方(女)向阜新市公安局刑警支队提供书面举报,举报于洋等参与吸毒、强奸、聚众淫乱。11月19日,正义网发表上官宏祥接受记者专访,再次实名举报于洋等参与吸毒、强奸、聚众淫乱,引起网民广泛关注。对此辽宁省政法委、省公安厅高度重视。当日,责成有关领导专程赴阜新听取案件调查情况。为加强对案件的督办和指导,省有关部门迅速成立工作组,赴阜新督办、指导案件调查工作,并将情况上报有关部门。　　经前一段调查证明,举报事实不存在,上官宏祥行为触犯《刑法》第243条,涉嫌诬告陷害罪。根据《刑事诉讼法》有关规定,阜新市公安局已于11月27日依法立案侦查。上官宏祥已于2009年12月1日到案,12月2日阜新市海州区人大常委会已依法停止其代表资格,阜新市公安局对其进行刑事拘留,并对同案人尹东方进行监视居住。现侦查工作正在进行中。";
//
//		String nativeBytes = null;
//		try {
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 3);
//			int nCountKey = 0;
//			
//			System.out.println(nativeBytes);
//			
//			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 5, false);
//
//			System.out.print("关键词提取结果是：" + nativeByte);
//			
//
//			CLibrary.Instance.NLPIR_Exit();
//
//		} catch (Exception ex) {
//			// TODO Auto-generated catch block
//			ex.printStackTrace();
//		}
//
//	}
}