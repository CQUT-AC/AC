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
	
	final JTextArea words = new JTextArea(5,50);		//文本域
    final JTextArea result = new JTextArea(5,50);
    JButton button1 = new JButton("关键词");				//关键词按钮
    JButton button2 = new JButton("重置");				//重置按钮
    
    public AC_KeywordPanel(){
    	this.add(words);
    	this.add(result);
    	this.add(button1);
    	this.add(button2);
    	
    	
    	words.setText("今天天气真好，所有我去了天狼星玩，真的很开心。");
        words.setLineWrap(true);
        result.setLineWrap(true);
        
        this.setPreferredSize(new Dimension(600, 400));
        
      //按钮响应.内部类,响应分词按钮
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
