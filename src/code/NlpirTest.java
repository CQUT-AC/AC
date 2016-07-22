package code;

import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// ����ӿ�CLibrary���̳���com.sun.jna.Library
	public interface CLibrary extends Library {
		// ���岢��ʼ���ӿڵľ�̬���� ��һ�������������dll�ģ�ע��dll�ļ���·�������Ǿ���·��Ҳ���������·����ֻ��Ҫ��дdll���ļ��������ܼӺ�׺��
		CLibrary Instance = (CLibrary) Native.loadLibrary("lib//win64//NLPIR", CLibrary.class);
		// ��ʼ����������
		public int NLPIR_Init(byte[] sDataPath, int encoding,byte[] sLicenceCode);
		//ִ�зִʺ�������
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);
		//��ȡ�ؼ��ʺ�������
		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,boolean bWeightOut);
		//�˳���������
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
	 *�ִ�
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
		// ����printf��ӡ��Ϣ
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
			System.err.println("��ʼ��ʧ�ܣ�");
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
	 *�ؼ���
	 */
	public static String GetKeyWord(String sInput){
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "GBK";
		int charset_type = 1;
		// int charset_type = 0;
		// ����printf��ӡ��Ϣ
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
			System.err.println("��ʼ��ʧ�ܣ�");
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
			System.err.println("��ʼ��ʧ�ܣ�fail reason is "+nativeBytes);
			return;
		}

		String sInput = "��Ϥ���ʼ��ܾ��ѽ������й�����ٴ�ͨ��������Ҫ��������ǿ���仪���׵Ĳ�����Դ�����估�ִ��Ȼ��ڵĹܿش�ʩ����Ч�����仪���ױ�δ���ҹ�ũҵ����ȫ��������׼��ת����Ʒϵ��Ⱦ��";

		//String nativeBytes = null;
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

			System.out.println("�ִʽ��Ϊ�� " + nativeBytes);
			
			CLibrary.Instance.NLPIR_AddUserWord("Ҫ��������ǿ���� n");
			CLibrary.Instance.NLPIR_AddUserWord("�����׵Ĳ�����Դ n");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 0);
			System.out.println("�����û��ʵ��ִʽ��Ϊ�� " + nativeBytes);
			
			CLibrary.Instance.NLPIR_DelUsrWord("Ҫ��������ǿ����");
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("ɾ���û��ʵ��ִʽ��Ϊ�� " + nativeBytes);
			
			
			int nCountKey = 0;
			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);

			System.out.print("�ؼ�����ȡ����ǣ�" + nativeByte);

			//nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\�����㳡\\2012\\5\\16766.txt", 10,false);

			System.out.print("�ؼ�����ȡ����ǣ�" + nativeByte);

			

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
//		// ����printf��ӡ��Ϣ
//		int init_flag = CLibrary.Instance.NLPIR_Init(argu
//				.getBytes(system_charset), charset_type, "0"
//				.getBytes(system_charset));
//
//		if (0 == init_flag) {
//			System.err.println("��ʼ��ʧ�ܣ�");
//			return;
//		}
//
//		String sInput = "������12��4����Ϣ��2009��10��21��,����ʡ������ί�յ��ٱ���,�ٱ��Ը����Ϊ��������ǿ�顢��������,������ί����ί���������Ȳ���������ǿ�顢�������ҵȡ��Դ�,������ί�߶�����,��ɸ����й�������������������,�����������չ�����顣���������ڼ�,�����ٱ����Ϲٺ�����ͨ��������(Ů)�����й������̾�֧���ṩ����ٱ�,�ٱ�����Ȳ���������ǿ�顢�������ҡ�11��19��,�����������Ϲٺ�����ܼ���ר��,�ٴ�ʵ���ٱ�����Ȳ���������ǿ�顢��������,��������㷺��ע���Դ�����ʡ����ί��ʡ�������߶����ӡ�����,����й��쵼ר�̸�������ȡ�������������Ϊ��ǿ�԰����Ķ����ָ��,ʡ�йز���Ѹ�ٳ���������,�����¶��졢ָ���������鹤��,��������ϱ��йز��š�������ǰһ�ε���֤��,�ٱ���ʵ������,�Ϲٺ�����Ϊ�������̷�����243��,�����ܸ��ݺ�����ݡ��������Ϸ����йع涨,�����й���������11��27������������顣�Ϲٺ�������2009��12��1�յ���,12��2�ո����к������˴�ί��������ֹͣ������ʸ�,�����й����ֶ���������¾���,����ͬ�������������м��Ӿ�ס������鹤�����ڽ����С�";
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
//			System.out.print("�ؼ�����ȡ����ǣ�" + nativeByte);
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