package br.com.estoque.telas.rh;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.estoque.dao.FuncionarioDAO;
import br.com.estoque.model.Funcionario;
import br.com.estoque.negocio.InterfaceFuncionario;

@SuppressWarnings("serial")
public class AlteraFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtcpf;
	private static String cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlteraFuncionario frame = new AlteraFuncionario(cpf);
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
	public AlteraFuncionario(String cpf) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtcpf.setText(cpf);
			}
		});
		setTitle("Alterar Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(37, 42, 46, 14);
		contentPane.add(lblCpf);
		
		txtcpf = new JTextField();
		txtcpf.setForeground(Color.BLACK);
		txtcpf.setFont(new Font("Arial", Font.BOLD, 14));
		txtcpf.setEnabled(false);
		txtcpf.setBounds(93, 39, 105, 20);
		contentPane.add(txtcpf);
		txtcpf.setColumns(10);
	}
	
	private void preencherInformacoes(){
		
		InterfaceFuncionario ifunc;
		ifunc = new FuncionarioDAO();
		Funcionario funcionario = ifunc.getFuncionario(cpf);
		
		
	}

}
