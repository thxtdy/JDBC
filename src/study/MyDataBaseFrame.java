package study;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyDataBaseFrame extends JFrame{
	
	MyDataBaseFrame mContext = this;
	
	private JLabel frameWindow;
	private JTextField updateContent;
	private JTextArea logtext;
	
	public MyDataBaseFrame() {
		initData();
		setInitLayout();
		
	}
	
	private void initData() {
		setTitle("케로케로리");
		frameWindow = new JLabel(new ImageIcon("image/keroroFrame.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(frameWindow);
		setSize(860, 640);
		
		// 텍스트 입력란
		updateContent = new JTextField(50);
		updateContent.setBounds(350, 300, 120, 30);
	}
	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		add(updateContent);
		
		setVisible(true);
	}
	private void addEventListener() {
		
		updateContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!updateContent.equals("")) {
					
				}
				
			}
		});
		
	}
	public static void main(String[] args) {
		
		new MyDataBaseFrame();
		
	}
	
}
