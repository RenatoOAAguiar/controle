package br.com.estoque.telas.rh;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

import org.hibernate.exception.ConstraintViolationException;

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

@SuppressWarnings("serial")
public class CadastraFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtsobrenome;
	private JTextField txtlogin;
	private JPasswordField txtsenha;
	private JTextField txtlogradouro;
	private JFormattedTextField txtnumero;
	private JTextField txtcomplemento;
	private JTextField txtsetor;
	private JFormattedTextField txtcpf;
	private JFormattedTextField txtdatanasc;
	private JFormattedTextField txtcep;
	private JFormattedTextField txtdatacontratacao;
	private JComboBox<String> cbcargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraFuncionario frame = new CadastraFuncionario();
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
	public CadastraFuncionario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				PreencherCombo();
				txtnome.requestFocus();
			}
		});
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(15, 16, 77, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalFuncionario p = new PrincipalFuncionario();
				setVisible(false);
				p.setVisible(true);
			}
		});

		JSeparator separator = new JSeparator();
		separator.setBounds(15, 71, 810, 2);

		JLabel lblCadastroDeFuncionrios = new JLabel("Cadastro de Funcion\u00E1rios");
		lblCadastroDeFuncionrios.setBounds(220, 16, 420, 44);
		lblCadastroDeFuncionrios.setFont(new Font("Times New Roman", Font.BOLD, 38));

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

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(15, 107, 53, 14);

		txtnome = new JTextField();
		txtnome.setBounds(102, 104, 101, 20);
		txtnome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(15, 148, 77, 14);

		txtsobrenome = new JTextField();
		txtsobrenome.setBounds(102, 145, 159, 20);
		txtsobrenome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(15, 186, 53, 14);

		txtcpf = new JFormattedTextField(mascaraCpf);
		txtcpf.setBounds(102, 183, 101, 20);

		JLabel lblDataNasc = new JLabel("Data Nasc:");
		lblDataNasc.setBounds(15, 227, 77, 14);

		txtdatanasc = new JFormattedTextField(mascaraData);
		txtdatanasc.setBounds(102, 224, 77, 20);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(15, 268, 63, 14);

		txtlogin = new JTextField();
		txtlogin.setBounds(102, 265, 86, 20);
		txtlogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(15, 301, 63, 14);

		txtsenha = new JPasswordField();
		txtsenha.setBounds(102, 296, 105, 20);

		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(421, 140, 80, 14);

		txtlogradouro = new JTextField();
		txtlogradouro.setBounds(532, 135, 151, 20);
		txtlogradouro.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(421, 178, 69, 14);

		txtnumero = new JFormattedTextField();
		txtnumero.setBounds(532, 173, 86, 20);
		txtnumero.setColumns(10);
		((AbstractDocument) txtnumero.getDocument()).setDocumentFilter(new FixedLenghtDocument(5));
		((AbstractDocument) txtnumero.getDocument()).setDocumentFilter(new IeValidator(5));

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(421, 222, 101, 14);

		txtcomplemento = new JTextField();
		txtcomplemento.setBounds(532, 217, 86, 20);
		txtcomplemento.setColumns(10);

		JLabel lblSetor = new JLabel("Setor:");
		lblSetor.setBounds(421, 260, 53, 14);

		txtsetor = new JTextField();
		txtsetor.setBounds(532, 255, 86, 20);
		txtsetor.setColumns(10);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(15, 333, 53, 14);

		cbcargo = new JComboBox<String>();
		cbcargo.setBounds(102, 329, 137, 22);

		JButton btnCadastrar = new JButton("Cadastrar");
		rootPane.setDefaultButton(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				InterfaceFuncionario ifunc;
				ifunc = new FuncionarioDAO();

				try {
					String cpf = txtcpf.getText().replace(".", "").replace("-", "");
					String dataNasc = txtdatanasc.getText().replace("/", "").replace("_", "");
					String cep = txtcep.getText().replace("-", "");

					if (cpf.equals("") || dataNasc.equals("") || txtdatacontratacao.getText().equals("")
							|| txtlogin.getText().equals("") || txtsenha.getPassword().equals("")
							|| txtdatanasc.getText().equals("") || txtnome.getText().equals("")
							|| txtsobrenome.getText().equals("") || txtcep.getText().equals("")) {
						throw new ExcecaoCampoVazio();
					}
					Funcionario funcionario = new Funcionario();
					Cargo cargo = new Cargo();
					int c = cbcargo.getSelectedIndex() + 1;
					cargo.setId(c);
					funcionario.setCargo(cargo);
					funcionario.setComplemento(txtcomplemento.getText());
					funcionario.setCpf(cpf);
					funcionario.setCep(cep);
					funcionario.setDataNasc(format.parse(txtdatanasc.getText()));
					funcionario.setDataContratacao(format.parse(txtdatacontratacao.getText()));
					funcionario.setLogin(txtlogin.getText());
					funcionario.setLogradouro(txtlogradouro.getText());
					funcionario.setNome(txtnome.getText());
					funcionario.setNumero(Integer.parseInt(txtnumero.getText()));
					funcionario.setSenha(String.valueOf(txtsenha.getPassword()));
					funcionario.setSetor(txtsetor.getText());
					funcionario.setSobrenome(txtsobrenome.getText());

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
					ifunc.save(funcionario);
					limpar();

					JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ConstraintViolationException eH) {
					JOptionPane.showMessageDialog(null, "CPF digitado já existe!", "Erro de violação",
							JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e) {
					Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, e);
				} catch (ExcecaoCampoVazio e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCadastrar.setBounds(181, 394, 105, 23);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(479, 394, 105, 23);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnVoltar);
		contentPane.add(lblCadastroDeFuncionrios);
		contentPane.add(separator);
		contentPane.add(lblNome);
		contentPane.add(txtnome);
		contentPane.add(lblLogradouro);
		contentPane.add(txtlogradouro);
		contentPane.add(lblSobrenome);
		contentPane.add(txtsobrenome);
		contentPane.add(lblNmero);
		contentPane.add(txtnumero);
		contentPane.add(lblCpf);
		contentPane.add(txtcpf);
		contentPane.add(lblComplemento);
		contentPane.add(txtcomplemento);
		contentPane.add(lblSenha);
		contentPane.add(txtsenha);
		contentPane.add(btnCadastrar);
		contentPane.add(btnLimpar);
		contentPane.add(lblDataNasc);
		contentPane.add(txtdatanasc);
		contentPane.add(lblSetor);
		contentPane.add(lblLogin);
		contentPane.add(txtlogin);
		contentPane.add(lblCargo);
		contentPane.add(cbcargo);
		contentPane.add(txtsetor);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(421, 107, 46, 14);
		contentPane.add(lblCep);

		txtcep = new JFormattedTextField(mascaraCep);
		txtcep.setBounds(532, 104, 69, 20);
		contentPane.add(txtcep);

		JLabel lblDataContratao = new JLabel("Data Contrata\u00E7\u00E3o:");
		lblDataContratao.setBounds(421, 299, 101, 14);
		contentPane.add(lblDataContratao);

		txtdatacontratacao = new JFormattedTextField(mascaraData);
		txtdatacontratacao.setBounds(532, 296, 77, 20);
		contentPane.add(txtdatacontratacao);
	}

	/**
	 * Método responsável por preencher o combo de cargos
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
		txtlogin.setText("");
		txtlogradouro.setText("");
		txtnome.setText("");
		txtdatanasc.setText("");
		txtdatacontratacao.setText("");
		txtcep.setText("");
		txtnumero.setValue(0);
		txtsenha.setText("");
		txtsetor.setText("");
		txtsobrenome.setText("");
		txtnumero.setText("");
		txtcpf.setText("");
		cbcargo.setSelectedIndex(-1);

	}
}
