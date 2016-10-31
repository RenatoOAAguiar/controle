package br.com.estoque.telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.estoque.telas.rh.PrincipalFuncionario;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2172989222138511175L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setAlwaysOnTop(true);
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRh = new JButton("RH");
		btnRh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipalFuncionario p = new PrincipalFuncionario();
				setVisible(false);
				p.setVisible(true);
			}
		});
		btnRh.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRh.setBackground(SystemColor.inactiveCaption);
		btnRh.setBounds(64, 59, 122, 55);
		contentPane.add(btnRh);
		setLocationRelativeTo(null);
	}

}
