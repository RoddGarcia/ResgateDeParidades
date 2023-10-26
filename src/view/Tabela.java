package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import controller.ListaMoedas;
import java.awt.Toolkit;

public class Tabela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTabbedPane tabbedPane;

	String[] coluna = {"Data", "Compra", "Venda"};
	Object[][] row = {};
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaMoedas lM = new ListaMoedas();		
					Tabela frameTabela = new Tabela(lM.getListaMoedas(), lM.getDataLista(), lM.getCotacaoCompraLista(), lM.getCotacaoVendaLista());
					frameTabela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Tabela(ArrayList<String> tipo_moeda, Map<String, ArrayList<String>> dataLista, Map<String, ArrayList<String>> cotacaoCompra, Map<String, ArrayList<String>> cotacaoVenda) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse-workspace\\ParidadesEmJava\\icon.ico"));
		setTitle("Tabela de Paridades Geradas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 583, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        for (String moeda: tipo_moeda) {
        	table = new JTable(criarTabela(moeda, dataLista.get(moeda), cotacaoCompra.get(moeda), cotacaoVenda.get(moeda)));
        	table.setCellSelectionEnabled(true);
        	JScrollPane scrollPane = new JScrollPane(table);
        
        	JPanel panel = new JPanel();
        	panel.setLayout(new BorderLayout());
        	panel.add(scrollPane, BorderLayout.CENTER);

        	tabbedPane.addTab(moeda, panel);
        }
        validate();
        repaint();
    }

    private DefaultTableModel criarTabela(String moedas, ArrayList<String> dataLista, ArrayList<String> cotacaoCompra,
            ArrayList<String> cotacaoVenda) {
        String[] coluna = { "Data", "Compra", "Venda" };
        DefaultTableModel model = new DefaultTableModel(coluna, 0);

        for (int i = 0; i < dataLista.size(); i++) {
            String data = dataLista.get(i);
            String compra = cotacaoCompra.get(i);
            String venda = cotacaoVenda.get(i);
            model.addRow(new Object[] { data, compra, venda });
        }
		return model;
    }
}