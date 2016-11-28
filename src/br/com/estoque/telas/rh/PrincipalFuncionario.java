package br.com.estoque.telas.rh;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.estoque.dao.FuncionarioDAO;
import br.com.estoque.model.Funcionario;
import br.com.estoque.negocio.InterfaceFuncionario;
import br.com.estoque.telas.Principal;
import br.com.estoque.utils.Mascaras;

@SuppressWarnings("serial")
public class PrincipalFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTable table;
	private JFormattedTextField txtcpf;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnGerarRelatorio;
	private String cpf;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFuncionario frame = new PrincipalFuncionario();
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
	public PrincipalFuncionario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);
				btnGerarRelatorio.setVisible(false);
			}
		});
		setResizable(false);
		setTitle("Funcion\u00E1rio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JSeparator separator = new JSeparator();

		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("Listagem de Funcion\u00E1rios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal p = new Principal();
				setVisible(false);
				p.setVisible(true);
			}
		});

		JLabel lblNome = new JLabel("Nome:");

		txtnome = new JTextField();
		txtnome.setColumns(10);

		JLabel lblcpf = new JLabel("Cpf:");

		txtcpf = new JFormattedTextField(mascaraCpf);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheTable();
				btnGerarRelatorio.setVisible(true);
			}
		});

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cpf = table.getValueAt(table.getSelectedRow(), 1).toString();
				AlteraFuncionario a = new AlteraFuncionario(cpf.replace(".", "").replace("-", ""));
				setVisible(false);
				a.setVisible(true);
			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cpf = table.getValueAt(table.getSelectedRow(), 1).toString();
				try {
					InterfaceFuncionario ifunc;
					ifunc = new FuncionarioDAO();
					Funcionario funcionario = new Funcionario();
					funcionario.setCpf(cpf.replace(".", "").replace("-", ""));
					ifunc.remove(funcionario);
				} catch (Exception e3) {
					e3.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Registro Excluído com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
				preencheTable();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraFuncionario cad = new CadastraFuncionario();
				setVisible(false);
				cad.setVisible(true);
			}
		});
		
		btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addGap(98)
							.addComponent(lblNewLabel)
							.addContainerGap())
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(lblNome)
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnConsultar)
									.addGap(45)
									.addComponent(btnAlterar)
									.addGap(52)
									.addComponent(btnExcluir))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(45)
									.addComponent(lblcpf)
									.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
									.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
									.addComponent(btnIncluir)))))
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(670, Short.MAX_VALUE)
					.addComponent(btnGerarRelatorio)
					.addGap(61))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(btnVoltar))
						.addComponent(lblNewLabel))
					.addGap(17)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblcpf)
						.addComponent(txtcpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIncluir))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConsultar)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(btnGerarRelatorio)
					.addContainerGap())
		);
		modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(true);
			}
		});
	}

	@SuppressWarnings("deprecation")
	protected void preencheTable() {

		InterfaceFuncionario ifunc;
		ifunc = new FuncionarioDAO();
		String cpf = txtcpf.getText().replace(".", "").replace("-", "").replace("_", "");
		String nome = txtnome.getText();
		List<Funcionario> listaFuncionario = null;
		Mascaras masc = new Mascaras();
		int i;

		String[] columnNames = { "Nome", "CPF", "Data Nascimento", "Cargo" };

		modelo.setNumRows(0);
		try {
			modelo.setColumnIdentifiers(columnNames);
			listaFuncionario = ifunc.list(cpf, nome);

			for (i = 0; i < listaFuncionario.size(); i++) {
				modelo.addRow(new String[] { listaFuncionario.get(i).getNome(),
						masc.mascaraCPF(listaFuncionario.get(i).getCpf()),
						listaFuncionario.get(i).getDataNasc().toLocaleString().substring(0, 10),
						listaFuncionario.get(i).getCargo().getNome() });
			}

			table.setModel(modelo);
		} catch (Exception e) {

		}

	}
}
