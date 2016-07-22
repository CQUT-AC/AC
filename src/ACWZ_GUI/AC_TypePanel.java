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
	
    JButton button1 = new JButton("�ִ�");				//�ִʰ�ť
    JButton button2 = new JButton("����");				//���ð�ť
    JButton button3 = new JButton("����");				//���ʰ�ť v
    JButton button4 = new JButton("����");				//���ʰ�ť n
    JButton button5 = new JButton("״��");				//״�ʰ�ť z
    JButton button6 = new JButton("���");				//��㰴ť w
    JButton button7 = new JButton("ʱ��");				//ʱ�䰴ť t
    JButton button8 = new JButton("����");				//������ť s
    JButton button9 = new JButton("��λ");				//��λ��ť f
    JButton button10 = new JButton("���ݴ�");			//���ݴʰ�ť a
    JButton button11 = new JButton("����");				//���ʰ�ť r
    JButton button12 = new JButton("����");				//���ʰ�ť m
    JButton button13 = new JButton("����");				//���ʰ�ť q
    JButton button14 = new JButton("����");				//���ʰ�ť d
    JButton button15 = new JButton("���");				//��ʰ�ť p
    JButton button16 = new JButton("����");				//���ʰ�ť c
    JButton button17 = new JButton("����");				//���ʰ�ť u
    JButton button18 = new JButton("̾��");				//̾�ʰ�ť e
    JButton button19 = new JButton("������");			//�����ʰ�ť y
    JButton button20 = new JButton("������");			//�����ʰ�ť o
    JButton button21 = new JButton("ǰ׺");				//ǰ׺��ť h
    JButton button22 = new JButton("��׺");				//��׺��ť k
    JButton button23 = new JButton("�ַ���");			//�ַ�����ť x
    final JTextArea words = new JTextArea(5,50);		//�ı���
    final JTextArea result = new JTextArea(5,50);
    
    public AC_TypePanel(){
    	//������Ϊ���Ա�ע
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
        
        words.setText("����������ã�������ȥ���������棬��ĺܿ��ġ�");
        words.setLineWrap(true);
        result.setLineWrap(true);
        
      //��ť��Ӧ.�ڲ���,��Ӧ�ִʰ�ť
        button1.addActionListener(
        	new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					res = Participle_API.participle(words.getText());
					result.setText(res);
				}
			}
        );
        
        //��ť��Ӧ.�ڲ���,��Ӧ���ð�ť        
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


//��ť��Ӧ�࣬�õ���Ӧ���ԵĴ�
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
