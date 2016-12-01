package br.com.estoque.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.estoque.telas.estoque.PrincipalProduto;
import br.com.estoque.telas.rh.PrincipalFuncionario;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2172989222138511175L;
	private JPanel contentPane;
	private JButton btnRh;
	private JButton btnEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param permissao
	 */
	public Principal(int permissao) {
		setAlwaysOnTop(true);
		setTitle("Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRh = new JButton("RH");
		btnRh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrincipalFuncionario p = new PrincipalFuncionario();
				setVisible(false);
				p.setVisible(true);
			}
		});
		btnRh.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRh.setBackground(Color.LIGHT_GRAY);
		btnRh.setBounds(62, 41, 122, 55);
		contentPane.add(btnRh);

		btnEstoque = new JButton("ESTOQUE");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalProduto p = new PrincipalProduto();
				setVisible(false);
				p.setVisible(true);
			}
		});
		btnEstoque.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEstoque.setBackground(Color.LIGHT_GRAY);
		btnEstoque.setBounds(286, 41, 122, 55);
		contentPane.add(btnEstoque);
		setLocationRelativeTo(null);

		switch (permissao) {
		case 7: {
			btnRh.setVisible(false);
			btnEstoque.setHorizontalAlignment(JButton.CENTER);
			break;
		}
		}
	}
}
