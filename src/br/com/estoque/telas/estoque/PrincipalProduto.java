package br.com.estoque.telas.estoque;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.estoque.dao.ProdutoDAO;
import br.com.estoque.model.Produto;
import br.com.estoque.negocio.InterfaceProduto;
import br.com.estoque.telas.Principal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.Font;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class PrincipalProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtfornecedor;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnGerarRelatorio;
	private JButton btnIncluir;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private List<Produto> dados;
	private Long idFornecedor;
	private JLabel lblListagemDeProdutos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalProduto frame = new PrincipalProduto();
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
	public PrincipalProduto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);
				btnGerarRelatorio.setVisible(false);
				txtnome.requestFocus();
			}
		});
		setTitle("Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Principal p = new Principal(0);
				p.setVisible(true);
			}
		});
		btnVoltar.setBounds(21, 11, 91, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(140, 105, 46, 14);
		contentPane.add(lblNome);
		
		txtnome = new JTextField();
		txtnome.setBounds(214, 102, 86, 20);
		contentPane.add(txtnome);
		txtnome.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(346, 105, 68, 14);
		contentPane.add(lblFornecedor);
		
		txtfornecedor = new JTextField();
		txtfornecedor.setBounds(445, 102, 86, 20);
		contentPane.add(txtfornecedor);
		txtfornecedor.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		rootPane.setDefaultButton(btnConsultar);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAlterar.setVisible(false);
				btnExcluir.setVisible(false);
				preencheTable();
			}
		});
		
		modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		btnConsultar.setBounds(171, 133, 91, 23);
		contentPane.add(btnConsultar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idFornecedor = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
				AlteraProduto a = new AlteraProduto(idFornecedor);
				setVisible(false);
				a.setVisible(true);
			}
		});
		btnAlterar.setBounds(314, 133, 91, 23);
		contentPane.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idFornecedor = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
				try {
					InterfaceProduto iProd;
					iProd = new ProdutoDAO();
					Produto produto = new Produto();
					produto.setId(idFornecedor);
					iProd.remove(produto);
					
					JOptionPane.showMessageDialog(null, "Registro Excluído com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
					preencheTable();
				} catch (Exception e3) {
					e3.printStackTrace();
				}

				
			}
		});
		btnExcluir.setBounds(450, 133, 91, 23);
		contentPane.add(btnExcluir);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CadastraProduto cad = new CadastraProduto();
					setVisible(false);
					cad.setVisible(true);
			}
		});
		btnIncluir.setBounds(648, 73, 91, 23);
		contentPane.add(btnIncluir);
		
		btnGerarRelatorio = new JButton("Gerar Relat\u00F3rio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					geraRelatorio();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (JRException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnGerarRelatorio.setBounds(613, 447, 128, 23);
		contentPane.add(btnGerarRelatorio);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 180, 697, 252);
		contentPane.add(scrollPane);
		

		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblListagemDeProdutos = new JLabel("Listagem de Produtos");
		lblListagemDeProdutos.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblListagemDeProdutos.setBounds(166, 0, 457, 54);
		contentPane.add(lblListagemDeProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 65, 751, 6);
		contentPane.add(separator);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				btnAlterar.setVisible(true);
				btnExcluir.setVisible(true);
			}
		});
	}
	
	/**
	 * Método responsável por preencher a JTable
	 * 
	 */
	@SuppressWarnings("deprecation")
	private void preencheTable() {

		InterfaceProduto iProd;
		iProd = new ProdutoDAO();
		String nome = txtnome.getText();
		String fornecedor = txtfornecedor.getText();
		List<Produto> listaProduto = null;
		int i;

		String[] columnNames = { "Id","Nome", "Quantidade", "Valor","Data Cadastro", "Fornecedor" };

		modelo.setNumRows(0);
		try {
			modelo.setColumnIdentifiers(columnNames);
			listaProduto = iProd.list(nome, fornecedor);
			if(listaProduto.size() > 0){
				for (i = 0; i < listaProduto.size(); i++) {
					modelo.addRow(new String[] { 
							String.valueOf(listaProduto.get(i).getId()),
							listaProduto.get(i).getNome(),
							String.valueOf(listaProduto.get(i).getQuantidade()),
							String.valueOf(listaProduto.get(i).getValor()),
							listaProduto.get(i).getDataAlteracao().toLocaleString().substring(0, 10),
							listaProduto.get(i).getFornecedor().getNome() });
				}
				table.setModel(modelo);
				dados = listaProduto;
				btnGerarRelatorio.setVisible(true);
			}
			else{
				btnGerarRelatorio.setVisible(false);
				JOptionPane.showMessageDialog(null,"Resultado não encontrado para a consulta!" ,"Atenção!",JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Método responsável por gerar o relátorio dos dados apresentados na JTable
	 * 
	 * @throws JRException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void geraRelatorio() throws JRException, SQLException, ClassNotFoundException {
        InputStream arquivo= this.getClass().getResourceAsStream("/br/com/estoque/relatorios/relatorioProduto.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(arquivo);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(dados));
        JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
        jrviewer.setVisible(true);
    }
}
