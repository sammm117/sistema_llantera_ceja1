package sistema_llantera_ceja1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAsdas;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarios frame = new usuarios();
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
	public usuarios() {
		setResizable(false);
		setTitle("usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inicio de Sesión ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 41, 582, 45);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre De Usuario ");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(62, 198, 210, 45);
		panel.add(lblNewLabel_1);
		
		txtAsdas = new JTextField();
		txtAsdas.setBackground(new Color(255, 255, 255));
		txtAsdas.setFont(new Font("Papyrus", Font.PLAIN, 22));
		txtAsdas.setBounds(277, 193, 251, 34);
		panel.add(txtAsdas);
		txtAsdas.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contrase\u00F1a ");
		lblNewLabel_1_1.setToolTipText("");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblNewLabel_1_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(62, 269, 210, 45);
		panel.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setFont(new Font("Papyrus", Font.PLAIN, 22));
		passwordField.setBounds(277, 264, 251, 34);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Credenciales esperadas
				String usuarioEsperado = "steven";
				String contraseñaEsperada = "12345";

				String usuarioIngresado = txtAsdas.getText();
				String contraseñaIngresada = new String(passwordField.getPassword());

				if (usuarioIngresado.equals(usuarioEsperado) && contraseñaIngresada.equals(contraseñaEsperada)) {
					// Credenciales correctas -> abrir menú principal
					menu menuPrincipal = new menu();
					menuPrincipal.setVisible(true);
					dispose(); // Cierra esta ventana
				} else {
					// Credenciales incorrectas -> mostrar error y limpiar contraseña
					JOptionPane.showMessageDialog(usuarios.this,
							"Usuario o contraseña incorrectos.\nIntroduce las credenciales correctas.",
							"Error de autenticación",
							JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
					passwordField.requestFocus();
				}
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnNewButton.setBounds(203, 359, 190, 54);
		panel.add(btnNewButton);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos de inicio de secion \r\n");
		lblIngreseLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosDatos.setForeground(new Color(255, 255, 255));
		lblIngreseLosDatos.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblIngreseLosDatos.setBackground(new Color(255, 255, 255));
		lblIngreseLosDatos.setBounds(-1, 125, 582, 45);
		panel.add(lblIngreseLosDatos);

	}
}
