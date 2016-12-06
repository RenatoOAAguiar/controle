package br.com.estoque.telas.estoque;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import br.com.estoque.dao.FornecedorDAO;
import br.com.estoque.dao.ProdutoDAO;
import br.com.estoque.model.Cargo;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.negocio.InterfaceFornecedor;
import br.com.estoque.negocio.InterfaceProduto;
import br.com.estoque.utils.ExcecaoCampoVazio;
import br.com.estoque.utils.FixedLenghtDocument;
import br.com.estoque.utils.IeValidator;

@SuppressWarnings("serial")
public class CadastraProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtquantidade;
	private JTextField txtvalor;
	private JComboBox<String> cbfornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraProduto frame = new CadastraProduto();
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
	public CadastraProduto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				PreencherCombo();
				txtnome.requestFocus();
			}
		});
		setTitle("Cadastro de Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PrincipalProduto p = new PrincipalProduto();
				p.setVisible(true);
			}
		});
		btnVoltar.setBounds(31, 22, 91, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblCadastroDeProdutos.setBounds(217, 11, 374, 57);
		contentPane.add(lblCadastroDeProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 66, 704, 2);
		contentPane.add(separator);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(51, 105, 46, 14);
		contentPane.add(lblNome);
		
		txtnome = new JTextField();
		txtnome.setBounds(128, 102, 86, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Quantidade:");
		lblNewLabel.setBounds(370, 105, 74, 14);
		contentPane.add(lblNewLabel);
		
		txtquantidade = new JTextField();
		txtquantidade.setBounds(470, 102, 63, 20);
		contentPane.add(txtquantidade);
		txtquantidade.setColumns(10);
		((AbstractDocument) txtquantidade.getDocument()).setDocumentFilter(new FixedLenghtDocument(5));
		((AbstractDocument) txtquantidade.getDocument()).setDocumentFilter(new IeValidator(5));
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(51, 164, 46, 14);
		contentPane.add(lblValor);
		
		txtvalor = new JTextField();
		txtvalor.setBounds(128, 161, 86, 20);
		contentPane.add(txtvalor);
		txtvalor.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(370, 164, 74, 14);
		contentPane.add(lblFornecedor);
		
		cbfornecedor = new JComboBox<String>();
		cbfornecedor.setBounds(470, 160, 149, 22);
		contentPane.add(cbfornecedor);
		
		JButton btnIncluir = new JButton("Cadastrar");
		rootPane.setDefaultButton(btnIncluir);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					InterfaceProduto iProd;
					iProd = new ProdutoDAO();
					Produto produto = new Produto();
					Fornecedor fornecedor = new Fornecedor();
					int c = cbfornecedor.getSelectedIndex() + 1;
					fornecedor.setId(c);
					produto.setFornecedor(fornecedor);
					if(txtnome.getText().equals("") || txtquantidade.getText().equals("") || txtvalor.getText().equals("")){
						throw new ExcecaoCampoVazio();
					}
					produto.setNome(txtnome.getText());
					produto.setValor(Double.parseDouble(txtvalor.getText()));
					produto.setQuantidade(Integer.parseInt(txtquantidade.getText()));
					iProd.save(produto);
					
					JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					
					limpar();
					
				}
				catch (ExcecaoCampoVazio e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIncluir.setBounds(188, 237, 91, 23);
		contentPane.add(btnIncluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(387, 237, 91, 23);
		contentPane.add(btnLimpar);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Método responsável por preencher o combo de Fornecedor
	 * 
	 */
	public void PreencherCombo() {
		InterfaceFornecedor ifornecedor;
		ifornecedor = new FornecedorDAO();
		List<Fornecedor> listaFornecedor = ifornecedor.list();
		int i;

		try {
			for (i = 0; i < listaFornecedor.size(); i++) {
				cbfornecedor.addItem(listaFornecedor.get(i).getNome());
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
		txtnome.setText("");
		txtquantidade.setText("");
		txtvalor.setText("");
		cbfornecedor.setSelectedIndex(-1);

	}
}
