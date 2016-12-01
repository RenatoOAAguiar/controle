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
public class AlteraProduto extends JFrame {

	private JPanel contentPane;
	private static Long id;
	private JTextField txtnome;
	private JTextField txtvalor;
	private JTextField txtquantidade;
	private JComboBox<String> cbfornecedor;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlteraProduto frame = new AlteraProduto(id);
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
	public AlteraProduto(final Long id) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				PreencherCombo();
				preencherInformacoes(id);
			}
		});
		setTitle("Alterar Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 342);
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
		btnVoltar.setBounds(10, 11, 91, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblAlteraoDeProdutos = new JLabel("Altera\u00E7\u00E3o de Produtos");
		lblAlteraoDeProdutos.setFont(new Font("Times New Roman", Font.BOLD, 38));
		lblAlteraoDeProdutos.setBounds(162, 0, 374, 57);
		contentPane.add(lblAlteraoDeProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 66, 594, 2);
		contentPane.add(separator);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(58, 126, 46, 14);
		contentPane.add(label_1);
		
		txtnome = new JTextField();
		txtnome.setColumns(10);
		txtnome.setBounds(135, 123, 86, 20);
		contentPane.add(txtnome);
		
		JLabel label_2 = new JLabel("Valor:");
		label_2.setBounds(58, 185, 46, 14);
		contentPane.add(label_2);
		
		txtvalor = new JTextField();
		txtvalor.setColumns(10);
		txtvalor.setBounds(135, 182, 86, 20);
		contentPane.add(txtvalor);
		
		txtquantidade = new JTextField();
		txtquantidade.setColumns(10);
		txtquantidade.setBounds(434, 122, 63, 20);
		contentPane.add(txtquantidade);
		((AbstractDocument) txtquantidade.getDocument()).setDocumentFilter(new FixedLenghtDocument(5));
		((AbstractDocument) txtquantidade.getDocument()).setDocumentFilter(new IeValidator(5));
		
		cbfornecedor = new JComboBox<String>();
		cbfornecedor.setBounds(434, 180, 149, 22);
		contentPane.add(cbfornecedor);
		
		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnlimpar.setBounds(352, 237, 91, 23);
		contentPane.add(btnlimpar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
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
					produto.setId(id);
					produto.setNome(txtnome.getText());
					produto.setValor(Double.parseDouble(txtvalor.getText()));
					produto.setQuantidade(Integer.parseInt(txtquantidade.getText()));
					iProd.update(produto);
					
					JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					
					setVisible(false);
					PrincipalProduto p = new PrincipalProduto();
					p.setVisible(true);
					
				}
				catch (ExcecaoCampoVazio e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlterar.setBounds(153, 237, 91, 23);
		contentPane.add(btnAlterar);
		
		JLabel label = new JLabel("Quantidade:");
		label.setBounds(330, 126, 74, 14);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel("Fornecedor:");
		label_3.setBounds(330, 185, 74, 14);
		contentPane.add(label_3);
		
		txtid = new JTextField();
		txtid.setEnabled(false);
		txtid.setBounds(72, 75, 63, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		setLocationRelativeTo(null);
	}
	
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

	private void limpar() {
		txtnome.setText("");
		txtquantidade.setText("");
		txtvalor.setText("");
		cbfornecedor.setSelectedIndex(-1);

	}
	
	private void preencherInformacoes(Long id) {

		try {
			InterfaceProduto iProd;
			iProd = new ProdutoDAO();
			Produto produto = iProd.getProduto(id);
			cbfornecedor.setSelectedItem(produto.getFornecedor().getNome());
			txtnome.setText(produto.getNome());
			txtid.setText(String.valueOf(produto.getId()));
			txtquantidade.setText(String.valueOf(produto.getQuantidade()));
			txtvalor.setText(String.valueOf(produto.getValor()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
