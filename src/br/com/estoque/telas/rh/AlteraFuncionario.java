package br.com.estoque.telas.rh;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import br.com.estoque.dao.CargoDAO;
import br.com.estoque.dao.FuncionarioDAO;
import br.com.estoque.model.Cargo;
import br.com.estoque.model.Funcionario;
import br.com.estoque.model.Permissao;
import br.com.estoque.negocio.InterfaceCargo;
import br.com.estoque.negocio.InterfaceFuncionario;
import br.com.estoque.utils.ExcecaoCampoVazio;
import br.com.estoque.utils.FixedLenghtDocument;
import br.com.estoque.utils.IeValidator;
import java.awt.Font;

@SuppressWarnings("serial")
public class AlteraFuncionario extends JFrame {

	private JPanel contentPane;
	private static String cpf;
	private JTextField txtnome;
	private JTextField txtsobrenome;
	private JTextField txtlogin;
	private JLabel label;
	private JTextField txtlogradouro;
	private JLabel lblnumero;
	private JFormattedTextField txtnumero;
	private JLabel lblcomplemento;
	private JTextField txtcomplemento;
	private JLabel lblsetor;
	private JTextField txtsetor;
	private JLabel lblcargo;
	private JComboBox<String> cbcargo;
	private JFormattedTextField txtcpf;
	private JFormattedTextField txtdataNasc;
	private JFormattedTextField txtdatacontratacao;
	private JFormattedTextField txtcep;
	private Long id;
	private String senha;
	private JLabel lblDataContra;

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
	public AlteraFuncionario(final String cpf) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtcpf.setText(cpf);
				PreencherCombo();
				preencherInformacoes(cpf);
				txtnome.requestFocus();
			}
		});
		setTitle("Alterar Cadastro");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 803, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(37, 64, 46, 14);
		contentPane.add(lblCpf);

		MaskFormatter mascaraData = null;
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraCep = null;
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraCep = new MaskFormatter("#####-###");
			mascaraCep.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(37, 11, 73, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalFuncionario p = new PrincipalFuncionario();
				setVisible(false);
				p.setVisible(true);
			}
		});
		contentPane.add(btnVoltar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(37, 102, 46, 14);
		contentPane.add(lblNome);

		txtnome = new JTextField();
		txtnome.setBounds(124, 99, 101, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(37, 138, 73, 14);
		contentPane.add(lblSobrenome);

		txtsobrenome = new JTextField();
		txtsobrenome.setBounds(124, 135, 101, 20);
		txtsobrenome.setColumns(10);
		contentPane.add(txtsobrenome);

		JLabel lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(37, 176, 73, 14);
		contentPane.add(lblDataNasc);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(276, 64, 73, 14);
		contentPane.add(lblLogin);

		txtlogin = new JTextField();
		txtlogin.setBounds(363, 61, 86, 20);
		txtlogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtlogin.setEditable(false);
		txtlogin.setColumns(10);
		contentPane.add(txtlogin);

		label = new JLabel("Logradouro:");
		label.setBounds(363, 109, 80, 14);
		contentPane.add(label);

		txtlogradouro = new JTextField();
		txtlogradouro.setBounds(453, 99, 151, 20);
		txtlogradouro.setColumns(10);
		contentPane.add(txtlogradouro);

		lblnumero = new JLabel("N\u00FAmero:");
		lblnumero.setBounds(363, 138, 69, 14);
		contentPane.add(lblnumero);

		txtnumero = new JFormattedTextField();
		txtnumero.setBounds(453, 137, 53, 20);
		txtnumero.setColumns(10);
		contentPane.add(txtnumero);
		((AbstractDocument) txtnumero.getDocument()).setDocumentFilter(new FixedLenghtDocument(5));
		((AbstractDocument) txtnumero.getDocument()).setDocumentFilter(new IeValidator(5));

		lblcomplemento = new JLabel("Complemento:");
		lblcomplemento.setBounds(363, 174, 101, 14);
		contentPane.add(lblcomplemento);

		txtcomplemento = new JTextField();
		txtcomplemento.setBounds(453, 173, 86, 20);
		txtcomplemento.setColumns(10);
		contentPane.add(txtcomplemento);

		lblsetor = new JLabel("Setor:");
		lblsetor.setBounds(363, 214, 53, 14);
		contentPane.add(lblsetor);

		txtsetor = new JTextField();
		txtsetor.setBounds(453, 211, 86, 20);
		txtsetor.setColumns(10);
		contentPane.add(txtsetor);

		lblcargo = new JLabel("Cargo:");
		lblcargo.setBounds(37, 214, 53, 14);
		contentPane.add(lblcargo);

		cbcargo = new JComboBox<String>();
		cbcargo.setBounds(124, 210, 137, 22);
		contentPane.add(cbcargo);

		txtcpf = new JFormattedTextField(mascaraCpf);
		txtcpf.setBounds(124, 61, 121, 20);
		txtcpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcpf.setEditable(false);
		contentPane.add(txtcpf);

		txtdataNasc = new JFormattedTextField(mascaraData);
		txtdataNasc.setBounds(124, 173, 73, 20);
		contentPane.add(txtdataNasc);

		JButton btnAlterar = new JButton("Alterar");
		rootPane.setDefaultButton(btnAlterar);
		btnAlterar.setBounds(156, 290, 89, 23);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				InterfaceFuncionario ifunc;
				ifunc = new FuncionarioDAO();

				try {
					String cpf = txtcpf.getText().replace(".", "").replace("-", "");
					String cep = txtcep.getText().replace("-", "");
					String data = txtdataNasc.getText().replace("/", "").replace("_", "");
					if (cpf.equals("") || data.equals("")) {
						throw new ExcecaoCampoVazio();
					}
					Funcionario funcionario = new Funcionario();
					Cargo cargo = new Cargo();
					int c = cbcargo.getSelectedIndex() + 1;
					cargo.setId(c);
					funcionario.setCargo(cargo);
					funcionario.setId(id);
					funcionario.setComplemento(txtcomplemento.getText());
					funcionario.setCpf(cpf);
					funcionario.setCep(cep);
					funcionario.setDataNasc(format.parse(txtdataNasc.getText()));
					funcionario.setDataContratacao(format.parse(txtdatacontratacao.getText()));
					funcionario.setLogin(txtlogin.getText());
					funcionario.setLogradouro(txtlogradouro.getText());
					funcionario.setNome(txtnome.getText());
					funcionario.setNumero(Integer.parseInt(txtnumero.getText()));
					funcionario.setSetor(txtsetor.getText());
					funcionario.setSobrenome(txtsobrenome.getText());
					funcionario.setSenha(senha);

					Permissao permissao = new Permissao();

					switch (c) {
					case 1: {
						permissao.setId(1);
						break;
					}
					case 2: {
						permissao.setId(4);
						break;
					}
					case 3: {
						permissao.setId(2);
						break;
					}
					case 4: {
						permissao.setId(5);
						break;
					}
					case 5: {
						permissao.setId(5);
						break;
					}
					case 6: {
						permissao.setId(6);
						break;
					}
					default: {
						permissao.setId(3);
						break;
					}
					}
					funcionario.setPermissao(permissao);
					ifunc.update(funcionario);
					limpar();

					JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					PrincipalFuncionario p = new PrincipalFuncionario();
					p.setVisible(true);
				}

				catch (ParseException e5) {
					Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, e5);
				} catch (ExcecaoCampoVazio e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnAlterar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(363, 290, 89, 23);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		contentPane.add(btnLimpar);

		lblDataContra = new JLabel("Data Contra.:");
		lblDataContra.setBounds(37, 249, 73, 14);
		contentPane.add(lblDataContra);

		txtdatacontratacao = new JFormattedTextField(mascaraData);
		txtdatacontratacao.setEnabled(false);
		txtdatacontratacao.setBounds(124, 246, 73, 20);
		contentPane.add(txtdatacontratacao);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(363, 249, 46, 14);
		contentPane.add(lblCep);

		txtcep = new JFormattedTextField(mascaraCep);
		txtcep.setBounds(453, 246, 69, 20);
		contentPane.add(txtcep);
	}

	/**
	 * Método responsável por preencher o combo de Cargos
	 * 
	 */
	public void PreencherCombo() {
		InterfaceCargo icargo;
		icargo = new CargoDAO();
		List<Cargo> listaCargo = icargo.list();
		int i;

		try {
			for (i = 0; i < listaCargo.size(); i++) {
				cbcargo.addItem(listaCargo.get(i).getNome());
			}
		} catch (Exception ex) {
			Logger.getLogger(Cargo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Método responsável por limpar os campos
	 * 
	 */
	private void limpar() {
		txtcomplemento.setText("");
		txtlogradouro.setText("");
		txtnome.setText("");
		txtcep.setText("");
		txtdataNasc.setText("");
		txtdatacontratacao.setText("");
		txtnumero.setValue(0);
		txtsetor.setText("");
		txtsobrenome.setText("");
		txtnumero.setText("");
		cbcargo.setSelectedIndex(-1);

	}

	/**
	 * Método responsável por preencher informações nos campos
	 * 
	 * @param cpf
	 */
	@SuppressWarnings("deprecation")
	private void preencherInformacoes(String cpf) {

		try {
			InterfaceFuncionario ifunc;
			ifunc = new FuncionarioDAO();
			Funcionario funcionario = ifunc.getFuncionario(cpf);
			txtlogin.setText(funcionario.getLogin());
			txtdatacontratacao.setText(funcionario.getDataContratacao().toLocaleString());
			txtdataNasc.setText(funcionario.getDataNasc().toLocaleString().substring(0, 10));
			cbcargo.setSelectedItem(funcionario.getCargo().getNome());
			txtnome.setText(funcionario.getNome());
			txtcep.setText(funcionario.getCep());
			txtnumero.setText(Integer.toString(funcionario.getNumero()));
			txtsetor.setText(funcionario.getSetor());
			txtsobrenome.setText(funcionario.getSobrenome());
			txtlogradouro.setText(funcionario.getLogradouro());
			txtcomplemento.setText(funcionario.getComplemento());
			this.senha = funcionario.getSenha();
			this.id = funcionario.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
