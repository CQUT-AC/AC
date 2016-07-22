package ACWZ_GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import API.Participle_API;

public class AC_TypePanel extends JPanel{

	static String res = "";
	
    JButton button1 = new JButton("分词");				//分词按钮
    JButton button2 = new JButton("重置");				//重置按钮
    JButton button3 = new JButton("动词");				//动词按钮 v
    JButton button4 = new JButton("名词");				//名词按钮 n
    JButton button5 = new JButton("状词");				//状词按钮 z
    JButton button6 = new JButton("标点");				//标点按钮 w
    JButton button7 = new JButton("时间");				//时间按钮 t
    JButton button8 = new JButton("处所");				//处所按钮 s
    JButton button9 = new JButton("方位");				//方位按钮 f
    JButton button10 = new JButton("形容词");			//形容词按钮 a
    JButton button11 = new JButton("代词");				//代词按钮 r
    JButton button12 = new JButton("数词");				//数词按钮 m
    JButton button13 = new JButton("量词");				//量词按钮 q
    JButton button14 = new JButton("副词");				//副词按钮 d
    JButton button15 = new JButton("介词");				//介词按钮 p
    JButton button16 = new JButton("连词");				//连词按钮 c
    JButton button17 = new JButton("助词");				//助词按钮 u
    JButton button18 = new JButton("叹词");				//叹词按钮 e
    JButton button19 = new JButton("语气词");			//语气词按钮 y
    JButton button20 = new JButton("拟声词");			//拟声词按钮 o
    JButton button21 = new JButton("前缀");				//前缀按钮 h
    JButton button22 = new JButton("后缀");				//后缀按钮 k
    JButton button23 = new JButton("字符串");			//字符串按钮 x
    final JTextArea words = new JTextArea(5,50);		//文本域
    final JTextArea result = new JTextArea(5,50);
    
    public AC_TypePanel(){
    	//命名作为词性标注
        button3.setName("v");
        button4.setName("n");
        button5.setName("z");
        button6.setName("w");
        button7.setName("t");
        button8.setName("s");
        button9.setName("f");
        button10.setName("a");
        button11.setName("r");
        button12.setName("m");
        button13.setName("q");
        button14.setName("d");
        button15.setName("p");
        button16.setName("c");
        button17.setName("u");
        button18.setName("e");
        button19.setName("y");
        button20.setName("o");
        button21.setName("h");
        button22.setName("k");
        button23.setName("x");
        
        this.add(words);
        this.add(result);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(button10);
        this.add(button11);
        this.add(button12);
        this.add(button13);
        this.add(button14);
        this.add(button15);
        this.add(button16);
        this.add(button17);
        this.add(button18);
        this.add(button19);
        this.add(button20);
        this.add(button21);
        this.add(button22);
        this.add(button23);
        
        words.setText("今天天气真好，所有我去了天狼星玩，真的很开心。");
        words.setLineWrap(true);
        result.setLineWrap(true);
        
      //按钮响应.内部类,响应分词按钮
        button1.addActionListener(
        	new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					res = Participle_API.participle(words.getText());
					result.setText(res);
				}
			}
        );
        
        //按钮响应.内部类,响应重置按钮        
        button2.addActionListener(
        	new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					words.setText("");
					result.setText("");
					Participle_API.reset();
				}
			}
        );
        
        button3.addActionListener(new AC_TypeListener(button3, result));
        button4.addActionListener(new AC_TypeListener(button4, result));
        button5.addActionListener(new AC_TypeListener(button5, result));
        button6.addActionListener(new AC_TypeListener(button6, result));
        button7.addActionListener(new AC_TypeListener(button7, result));
        button8.addActionListener(new AC_TypeListener(button8, result));
        button9.addActionListener(new AC_TypeListener(button9, result));
        button10.addActionListener(new AC_TypeListener(button10, result));
        button11.addActionListener(new AC_TypeListener(button11, result));
        button12.addActionListener(new AC_TypeListener(button12, result));
        button13.addActionListener(new AC_TypeListener(button13, result));
        button14.addActionListener(new AC_TypeListener(button14, result));
        button15.addActionListener(new AC_TypeListener(button15, result));
        button16.addActionListener(new AC_TypeListener(button16, result));
        button17.addActionListener(new AC_TypeListener(button17, result));
        button18.addActionListener(new AC_TypeListener(button18, result));
        button19.addActionListener(new AC_TypeListener(button19, result));
        button20.addActionListener(new AC_TypeListener(button20, result));
        button21.addActionListener(new AC_TypeListener(button21, result));
        button22.addActionListener(new AC_TypeListener(button22, result));
        button23.addActionListener(new AC_TypeListener(button23, result));

        this.setPreferredSize(new Dimension(600, 400));
    }
}


//按钮响应类，得到相应词性的词
class AC_TypeListener implements ActionListener{
	JTextArea result = null;
	JButton bt = null;
	public AC_TypeListener(JButton bt, JTextArea result){
		this.bt = bt;
		this.result = result;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		result.setText(Participle_API.getTypeWord(bt.getName()));
	}
}
