package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Panel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JRadioButton;

import controller.ListaMoedas;
import java.awt.Toolkit;

public class MainGUI {
	
	private JFrame frame;
	private ListaMoedas listaMoedas = new ListaMoedas();
	Boolean isVarias = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse-workspace\\ParidadesEmJava\\icon.ico"));
		frame.setTitle("Resgatar Paridades 2024");
		frame.setBounds(100, 100, 535, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel tiposMoedas = new JPanel();
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel qtdMeses = new JPanel();
		frame.getContentPane().add(qtdMeses);
		
		JRadioButton btnApenasUmMes = new JRadioButton("Apenas um mês");
		btnApenasUmMes.setSelected(true);
		qtdMeses.add(btnApenasUmMes);
		
		JRadioButton btnMaisDeUmMes = new JRadioButton("Mais de um mês");
		qtdMeses.add(btnMaisDeUmMes);
		
		ButtonGroup btngroup = new ButtonGroup();
		
		btngroup.add(btnMaisDeUmMes);
		btngroup.add(btnApenasUmMes);
		
		Panel defMes = new Panel();
		frame.getContentPane().add(defMes);
		defMes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_mesInicial = new JPanel();
		defMes.add(panel_mesInicial);
		panel_mesInicial.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel textoMesInicial = new JLabel("Mês inicial");
		textoMesInicial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_mesInicial.add(textoMesInicial);
		
		JSpinner mesInicial = new JSpinner();
		mesInicial.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mesInicial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_mesInicial.add(mesInicial);
		
		JPanel panel_mesFinal = new JPanel();
		defMes.add(panel_mesFinal);
		
		JLabel textoMesFinal = new JLabel("Mês final");
		textoMesFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_mesFinal.add(textoMesFinal);
		
		JSpinner mesFinal = new JSpinner();
		mesFinal.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mesFinal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_mesFinal.add(mesFinal);
		
		JPanel numMes = new JPanel();
		defMes.add(numMes);
		
		JLabel textoMesFinal_1 = new JLabel("Mês");
		textoMesFinal_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numMes.add(textoMesFinal_1);
		
		JSpinner mesFinalRng = new JSpinner();
		mesFinalRng.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		mesFinalRng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		numMes.add(mesFinalRng);
		frame.getContentPane().add(tiposMoedas);
		tiposMoedas.setLayout(new GridLayout(0, 2, 0, 0));
		
		JCheckBox USD = new JCheckBox("USD");
		USD.setSelected(true);
		USD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(USD);
		
		JCheckBox CHF = new JCheckBox("CHF");
		CHF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(CHF);
		
		JCheckBox EUR = new JCheckBox("EUR");
		EUR.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(EUR);
		
		JCheckBox GBP = new JCheckBox("GBP");
		GBP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(GBP);
		
		JCheckBox CAD = new JCheckBox("CAD");
		CAD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(CAD);
		
		JCheckBox JPY = new JCheckBox("JPY");
		JPY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(JPY);
		
		JCheckBox SEK = new JCheckBox("SEK");
		SEK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(SEK);
		
		JCheckBox AUD = new JCheckBox("AUD");
		AUD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(AUD);
		
		JCheckBox DKK = new JCheckBox("DKK");
		DKK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiposMoedas.add(DKK);
		
		JPanel gerarMoedas = new JPanel();
		FlowLayout fl_gerarMoedas = new FlowLayout(FlowLayout.CENTER, 5, 2);
		gerarMoedas.setLayout(fl_gerarMoedas);
		
		JButton gerarDados = new JButton();
		gerarDados.setFont(new Font("Tahoma", Font.PLAIN, 17));
		gerarDados.setEnabled(true);
		gerarMoedas.add(gerarDados);
		gerarDados.setText("Gerar");
		gerarDados.setToolTipText("Gerar resultados");
		
		panel_mesInicial.setVisible(false);
        panel_mesFinal.setVisible(false);
		
		btnApenasUmMes.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // btnApenasUmMes está selecionado
		            numMes.setVisible(true);
		            panel_mesInicial.setVisible(false);
		            panel_mesFinal.setVisible(false);
					isVarias = false;
		        }
		    }
		});
		
		btnMaisDeUmMes.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            // btnMaisDeUmMes está selecionado
		        	numMes.setVisible(false);
		        	panel_mesInicial.setVisible(true);
		        	panel_mesFinal.setVisible(true);
		        	isVarias = true;
		        }
		    }
		});
		
		// Enviar
		gerarDados.addActionListener(new ActionListener() {
			List<String> opcoes = new ArrayList<>();
			
			public void actionPerformed(ActionEvent e) {
				int numMesInicial = (int) mesInicial.getValue();
				int numMesFinal = (int) mesFinal.getValue();
				
				gerarDados.setEnabled(false);
				mesInicial.setEnabled(false);
				mesFinal.setEnabled(false);
				mesFinalRng.setEnabled(false);
				btnApenasUmMes.setEnabled(false);
				btnMaisDeUmMes.setEnabled(false);
				
				if (isVarias) { // Vários
					if (numMesInicial > numMesFinal) {
						JOptionPane.showMessageDialog(null, "Um dos valores estão incorretos",
								"Valores Incorretos", JOptionPane.ERROR_MESSAGE);
						
						System.err.println("Não funcionou, mês inicial é maior que mês final");	
					} else {
						
						if (USD.isSelected()) {
							opcoes.add("USD");
						}
						if (CHF.isSelected()) {
							opcoes.add("CHF");
						}
						if (EUR.isSelected()) {
							opcoes.add("EUR");
						}
						if (GBP.isSelected()) {
							opcoes.add("GBP");
						}
						if (CAD.isSelected()) {
							opcoes.add("CAD");
						}
						if (JPY.isSelected()) {
							opcoes.add("JPY");
						}
						if (SEK.isSelected()) {
							opcoes.add("SEK");
						}
						if (AUD.isSelected()) {
							opcoes.add("AUD");
						}
						if (DKK.isSelected()) {
							opcoes.add("DKK");
						}
						
						listaMoedas.salvarLista(mesInicial.getValue(), mesFinal.getValue(), opcoes);
						
						SwingUtilities.invokeLater(new Runnable() {
		                    public void run() {
		                    	Tabela tabelaFrame = new Tabela(listaMoedas.getListaMoedas(), listaMoedas.getDataLista(), listaMoedas.getCotacaoCompraLista(), listaMoedas.getCotacaoVendaLista());
		                    	tabelaFrame.setVisible(true);
		                    	frame.setVisible(false);	                    	
		                    }
		                });
							
						}
					}
				
				else { // Um apenas
										
					if (USD.isSelected()) {
						opcoes.add("USD");
					}
					if (CHF.isSelected()) {
						opcoes.add("CHF");
					}
					if (EUR.isSelected()) {
						opcoes.add("EUR");
					}
					if (GBP.isSelected()) {
						opcoes.add("GBP");
					}
					if (CAD.isSelected()) {
						opcoes.add("CAD");
					}
					if (JPY.isSelected()) {
						opcoes.add("JPY");
					}
					if (SEK.isSelected()) {
						opcoes.add("SEK");
					}
					if (AUD.isSelected()) {
						opcoes.add("AUD");
					}
					if (DKK.isSelected()) {
						opcoes.add("DKK");
					}
					
					listaMoedas.salvarLista(mesFinalRng.getValue(), opcoes);
					
					SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                    	Tabela tabelaFrame = new Tabela(listaMoedas.getListaMoedas(), listaMoedas.getDataLista(), listaMoedas.getCotacaoCompraLista(), listaMoedas.getCotacaoVendaLista());
	                    	tabelaFrame.setVisible(true);
	                    	frame.setVisible(false);	                    	
	                    }
	                });
					}
				}
			});
		
		frame.getContentPane().add(gerarMoedas);
	}
}