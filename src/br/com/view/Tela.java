package br.com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.standalone.evl.VVMiddleware;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Tela extends JFrame {

	private VVMiddleware conformidadeStandalone = new VVMiddleware();
	private JPanel contentPane;
	JTextPane textPane = new JTextPane();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public Tela() {
		setTitle("Ferramenta V&V");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 10, 700, 562); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSynthesis = new JButton("Modelo camada Synthesis");
		btnSynthesis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation/src/"
													+ "br/com/standalone/models/Synthesis/Modelos/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
	
				conformidadeStandalone.setModeloSynthesis(jf.getSelectedFile().getPath());
			}
		});
		btnSynthesis.setBounds(39, 28, 228, 25);
		contentPane.add(btnSynthesis);
		
		
		
		JButton btnController = new JButton("Modelo camada Controller");
		btnController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation"
											+ "/src/br/com/standalone/models/Controller/"
											+ "Modelos/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
	
				conformidadeStandalone.setModeloController(jf.getSelectedFile().getPath());
			}
		});
		btnController.setBounds(39, 92, 228, 25);
		contentPane.add(btnController); 
		
		
		
		JButton btnBroker = new JButton("Modelo camada Broker");
		btnBroker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/"
									+ "VerificationAndValidation/src/br/com/standalone"
									+ "/models/Broker/Modelos/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
				conformidadeStandalone.setModeloBroker(jf.getSelectedFile().getPath());
			}
		});
		btnBroker.setBounds(39, 152, 228, 25);
		contentPane.add(btnBroker);
		
		
		
		
		JButton btnRelacionamentoSynthesis = new JButton("Modelo relacionamento Synthesis");
		btnRelacionamentoSynthesis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation/src/"
						+ "br/com/standalone/models/relacionamento/modelo/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
	
				conformidadeStandalone.setModeloRelacionamentoSynthesis(jf.getSelectedFile().getPath());
			}
		});
		btnRelacionamentoSynthesis.setBounds(381, 28, 294, 25);
		contentPane.add(btnRelacionamentoSynthesis);
		
		
		
		
		
		
		JButton btnRelacionamentoController = new JButton("Modelo relacionamento Controller");
		btnRelacionamentoController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRelacionamentoController.setBounds(381, 92, 294, 25);
		btnRelacionamentoController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation/src/"
						+ "br/com/standalone/models/relacionamento/modelo/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
	
				conformidadeStandalone.setModeloRelacionamentoController(jf.getSelectedFile().getPath());
			}
		});
		contentPane.add(btnRelacionamentoController);
		
	
		
		
		
		JButton btnRelacionamentoBroker = new JButton("Modelo relacionamento Broker");
		btnRelacionamentoBroker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation/"
												+ "src/br/com/standalone/models/relacionamento/modelo/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
	
				conformidadeStandalone.setModeloRelacionamentoBroker(jf.getSelectedFile().getPath());
			}
		});
		btnRelacionamentoBroker.setBounds(381, 152, 294, 25);
		contentPane.add(btnRelacionamentoBroker);
		
		
		
		
		JButton btnFeatureModel = new JButton("Feature Model");
		btnFeatureModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser("/home/marcelo/workspace/VerificationAndValidation/"
						+ "src/br/com/standalone/models/feature/modelo/");
				jf.setMultiSelectionEnabled(false);
				jf.showOpenDialog(null);
				conformidadeStandalone.setFeatureModel(jf.getSelectedFile().getPath());
			}
		});
		btnFeatureModel.setBounds(449, 230, 137, 25);
		contentPane.add(btnFeatureModel);
		
		

		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conformidadeStandalone.execute();
					textPane.setText(conformidadeStandalone.getMsg());
					
				} catch (Exception e1) {
					e1.printStackTrace();
				};
			}
		});
		btnValidar.setBounds(96, 230, 117, 25);
		contentPane.add(btnValidar);
		
		
		textPane.setBounds(39, 291, 636, 231);
		contentPane.add(textPane);
	}
}
