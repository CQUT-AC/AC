package ACWZ_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class acwz_window extends JFrame implements ActionListener{
	String res = "";
	JMenuBar mb ;
	JMenu mnAPI;
	JMenuItem miPart, miKey;
	public JPanel jPanel = new JPanel();
	public acwz_window(){
		super("AC����");
		
		mb = new JMenuBar();
		mnAPI = new JMenu("API");
		miPart = new JMenuItem("�ִ�");
		miKey = new JMenuItem("�ؼ���");
		
		mnAPI.add(miPart);
		mnAPI.add(miKey);
		
		mb.add(mnAPI);
		
		this.setJMenuBar(mb);
		
		
		jPanel.add(new AC_TypePanel());
		this.add(jPanel, BorderLayout.CENTER);
		miPart.addActionListener(new PanelListener(this, miPart));
		miKey.addActionListener(new PanelListener(this, miKey));
       
		 
		//�رմ���ʱ���ر����г���
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	public static void main(String args[]){
		acwz_window acwz = new acwz_window();
		acwz.pack();
		acwz.setSize(600,400);					//���ڴ�С
		acwz.setVisible(true);					//���ڿɼ�
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class PanelListener implements ActionListener{
	acwz_window frame;
	JMenuItem jItem;
	public PanelListener(acwz_window frame, JMenuItem jItem){
		this.frame = frame;
		this.jItem = jItem;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jItem.getText().equals("�ִ�")){
			frame.jPanel.removeAll();
			frame.jPanel.add(new AC_TypePanel());
			frame.jPanel.revalidate();
			frame.jPanel.repaint();
		}
		if(jItem.getText().equals("�ؼ���")){
			frame.jPanel.removeAll();
			frame.jPanel.add(new AC_KeywordPanel());
			frame.jPanel.revalidate();
			frame.jPanel.repaint();
		}
	}
	
}

