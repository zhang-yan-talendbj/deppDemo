package com.caribe.stone.word;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ReviewFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3527079935208936671L;
	private JButton remeberBtn = new JButton("remebered");
	private JButton forgetBtn = new JButton("forgot");

	public JButton getRemeberBtn() {
		return remeberBtn;
	}

	public void setRemeberBtn(JButton remeberBtn) {
		this.remeberBtn = remeberBtn;
	}

	public JButton getForgetBtn() {
		return forgetBtn;
	}

	public void setForgetBtn(JButton forgetBtn) {
		this.forgetBtn = forgetBtn;
	}

	public ReviewFrame(String word) throws HeadlessException {
		super("Please review [" + word + "]");
		JTextField text = new JTextField();
		text.setSize(100, 30);
		text.setText(word);

		setSize(600, 100);
		JPanel panel = new JPanel();
		panel.setSize(600, 100);

		panel.add(text);
		panel.add(remeberBtn);
		panel.add(forgetBtn);
		add(panel);
		setVisible(true);
	}
}