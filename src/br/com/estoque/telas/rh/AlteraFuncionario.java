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
	private JTextField txtnumero;
	private JLabel lblcomplemento;
	private JTextField txtcomplemento;
	private JLabel lblsetor;
	private JTextField txtsetor;
	private JLabel lblcargo;
	private JComboBox<String> cbcargo;
	private JFormattedTextField txtcpf;
	private JFormattedTextField txtdataNasc;
	private Long id;

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
			}
		});
		setTitle("Alterar Cadastro");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 803, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(37, 64, 46, 14);
		contentPane.add(lblCpf);

		MaskFormatter mascaraData = null;
		MaskFormatter mascaraCpf = null;
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrincipalFuncionario p = new PrincipalFuncionario();
				setVisible(false);
				p.setVisible(true);
			}
		});
		btnVoltar.setBounds(37, 11, 73, 23);
		contentPane.add(btnVoltar);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(37, 113, 46, 14);
		contentPane.add(lblNome);

		txtnome = new JTextField();
		txtnome.setBounds(124, 110, 101, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);

		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(37, 149, 73, 14);
		contentPane.add(lblSobrenome);

		txtsobrenome = new JTextField();
		txtsobrenome.setColumns(10);
		txtsobrenome.setBounds(124, 146, 101, 20);
		contentPane.add(txtsobrenome);

		JLabel lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(37, 187, 73, 14);
		contentPane.add(lblDataNasc);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(276, 64, 73, 14);
		contentPane.add(lblLogin);

		txtlogin = new JTextField();
		txtlogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtlogin.setEditable(false);
		txtlogin.setColumns(10);
		txtlogin.setBounds(363, 61, 86, 20);
		contentPane.add(txtlogin);

		label = new JLabel("Logradouro:");
		label.setBounds(369, 118, 80, 14);
		contentPane.add(label);

		txtlogradouro = new JTextField();
		txtlogradouro.setColumns(10);
		txtlogradouro.setBounds(480, 113, 151, 20);
		contentPane.add(txtlogradouro);

		lblnumero = new JLabel("N\u00FAmero:");
		lblnumero.setBounds(369, 156, 69, 14);
		contentPane.add(lblnumero);

		txtnumero = new JTextField();
		txtnumero.setColumns(10);
		txtnumero.setBounds(480, 151, 53, 20);
		contentPane.add(txtnumero);
		((AbstractDocument)txtnumero.getDocument()).setDocumentFilter(new FixedLenghtDocument(5));
		((AbstractDocument)txtnumero.getDocument()).setDocumentFilter(new IeValidator(5));

		lblcomplemento = new JLabel("Complemento:");
		lblcomplemento.setBounds(369, 200, 101, 14);
		contentPane.add(lblcomplemento);

		txtcomplemento = new JTextField();
		txtcomplemento.setColumns(10);
		txtcomplemento.setBounds(480, 195, 86, 20);
		contentPane.add(txtcomplemento);

		lblsetor = new JLabel("Setor:");
		lblsetor.setBounds(369, 238, 53, 14);
		contentPane.add(lblsetor);

		txtsetor = new JTextField();
		txtsetor.setColumns(10);
		txtsetor.setBounds(480, 233, 86, 20);
		contentPane.add(txtsetor);

		lblcargo = new JLabel("Cargo:");
		lblcargo.setBounds(37, 232, 53, 14);
		contentPane.add(lblcargo);

		cbcargo = new JComboBox<String>();
		cbcargo.setBounds(124, 234, 137, 22);
		contentPane.add(cbcargo);

		txtcpf = new JFormattedTextField(mascaraCpf);
		txtcpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcpf.setEditable(false);
		txtcpf.setBounds(124, 61, 121, 20);
		contentPane.add(txtcpf);

		txtdataNasc = new JFormattedTextField(mascaraData);
		txtdataNasc.setBounds(124, 184, 73, 20);
		contentPane.add(txtdataNasc);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				InterfaceFuncionario ifunc;
				ifunc = new FuncionarioDAO();
				
				try{
					String cpf = txtcpf.getText().replace(".", "").replace("-", "");
					String data = txtdataNasc.getText().replace("/", "").replace("_", ""); 
					if(cpf.equals("")|| data.equals("")){
						throw new ExcecaoCampoVazio();
					}
					Funcionario funcionario = new Funcionario();
					Cargo cargo = new Cargo();
					int c = cbcargo.getSelectedIndex()+1;
					cargo.setId(c);
					funcionario.setCargo(cargo);
					funcionario.setId(id);
					funcionario.setComplemento(txtcomplemento.getText());
					funcionario.setCpf(cpf);
					funcionario.setDataNasc(format.parse(txtdataNasc.getText()));
					funcionario.setLogin(txtlogin.getText());
					funcionario.setLogradouro(txtlogradouro.getText());
					funcionario.setNome(txtnome.getText());
					funcionario.setNumero(Integer.parseInt(txtnumero.getText()));
					funcionario.setSetor(txtsetor.getText());
					funcionario.setSobrenome(txtsobrenome.getText());
					
					Permissao permissao = new Permissao();
					
					
					switch(c){
					case 1:{
						permissao.setId(1);
						break;
					}
					case 2:{
						permissao.setId(4);
						break;
					}
					case 3:{
						permissao.setId(2);
						break;
					}
					case 4:{
						permissao.setId(5);
						break;
					}
					case 5:{
						permissao.setId(5);
						break;
					}
					case 6:{
						permissao.setId(6);
						break;
					}
					default:{
						permissao.setId(3);
						break;
					}
					}
					funcionario.setPermissao(permissao);
					ifunc.update(funcionario);
					limpar();
					
					JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
				}

				catch(ParseException e5){
					Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, e5);
				}
				catch(ExcecaoCampoVazio e1){
					JOptionPane.showMessageDialog(null,e1.getMessage(), "Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlterar.setBounds(156, 290, 89, 23);
		contentPane.add(btnAlterar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(363, 290, 89, 23);
		contentPane.add(btnLimpar);
	}

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
	
	private void limpar(){
		txtcomplemento.setText("");
		txtlogin.setText("");
		txtlogradouro.setText("");
		txtnome.setText("");
		txtdataNasc.setText("");
		txtnumero.setText("");
		txtsetor.setText("");
		txtsobrenome.setText("");
		txtnumero.setText("");
		txtcpf.setText("");
		cbcargo.setSelectedIndex(-1);
		
	}

	@SuppressWarnings("deprecation")
	private void preencherInformacoes(String cpf) {

		InterfaceFuncionario ifunc;
		ifunc = new FuncionarioDAO();
		Funcionario funcionario = ifunc.getFuncionario(cpf);
		txtlogin.setText(funcionario.getLogin());
		txtdataNasc.setText(funcionario.getDataNasc().toLocaleString());
		cbcargo.setSelectedItem(funcionario.getCargo().getNome());
		txtnome.setText(funcionario.getNome());
		txtnumero.setText(Integer.toString(funcionario.getNumero()));
		txtsetor.setText(funcionario.getSetor());
		txtsobrenome.setText(funcionario.getSobrenome());
		txtlogradouro.setText(funcionario.getLogradouro());
		txtcomplemento.setText(funcionario.getComplemento());
		this.id = funcionario.getId();
	}
}
