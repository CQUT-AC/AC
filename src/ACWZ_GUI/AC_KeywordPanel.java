package ACWZ_GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import API.GetKeyWord_API;

public class AC_KeywordPanel extends JPanel{
	static String res = "";
	
	final JTextArea words = new JTextArea(5,50);		//�ı���
    final JTextArea result = new JTextArea(5,50);
    JButton button1 = new JButton("�ؼ���");				//�ؼ��ʰ�ť
    JButton button2 = new JButton("����");				//���ð�ť
    
    public AC_KeywordPanel(){
    	this.add(words);
    	this.add(result);
    	this.add(button1);
    	this.add(button2);
    	
    	
    	words.setText("����������ã�������ȥ���������棬��ĺܿ��ġ�");
        words.setLineWrap(true);
        result.setLineWrap(true);
        
        this.setPreferredSize(new Dimension(600, 400));
        
      //��ť��Ӧ.�ڲ���,��Ӧ�ִʰ�ť
        button1.addActionListener(
        	new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					res = GetKeyWord_API.getkeyword(words.getText());
					result.setText(res);
				}
			}
        );
        
        button2.addActionListener(
        	new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					words.setText("");
					result.setText("");
					GetKeyWord_API.reset();
				}
			}
        );
    }
}
