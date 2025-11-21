package sistema_llantera_ceja1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

public class clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientes frame = new clientes();
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
	public clientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(0, 128, 128));
		contentPane_1.setBounds(0, 0, 873, 583);
		contentPane.add(contentPane_1);
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu ventanaMenu = new menu();
				ventanaMenu.setVisible(true);
				((JFrame) SwingUtilities.getWindowAncestor(btnAtras)).dispose();
			}
		});
		btnAtras.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnAtras.setBounds(10, 11, 101, 50);
		contentPane_1.add(btnAtras);
		
		JLabel lblTitulo = new JLabel("Clientes \r\n");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		lblTitulo.setBounds(10, 11, 542, 58);
		contentPane_1.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre del cliente");
		lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblNombre.setBounds(36, 130, 202, 58);
		contentPane_1.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(248, 148, 227, 33);
		contentPane_1.add(textField);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblTelefono.setBounds(36, 207, 103, 58);
		contentPane_1.add(lblTelefono);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 225, 194, 33);
		contentPane_1.add(textField_1);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblCorreo.setBounds(36, 300, 157, 58);
		contentPane_1.add(lblCorreo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(125, 318, 194, 33);
		contentPane_1.add(textField_2);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnAgregar.setBounds(248, 407, 140, 50);
		contentPane_1.add(btnAgregar); 
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				String telefono = textField_1.getText();
				String correo = textField_2.getText();
				

				if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
				} else {
					guardarClientesEnBD(nombre, telefono, correo);
					menu siguiente = new menu();
					siguiente.setVisible(true);
					((JFrame) SwingUtilities.getWindowAncestor(btnAgregar)).dispose();
				}
			}
		});

	}
	 private void guardarClientesEnBD(String nombre, String telefono, String correo) {
	        String url = "jdbc:mysql://localhost:3306/proyecto";
	        String usuario = "root";
	        String contraseña = "steven";
	        String sql = "INSERT INTO registro_cliente (nombre, telefono, correo) VALUES (?, ?, ?)";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ps.setString(1, nombre);
	            ps.setString(2, telefono);
	            ps.setString(3, correo);
	            ps.executeUpdate();
	            ps.close();
	            conexion.close();
	            JOptionPane.showMessageDialog(this, "cliente guardado correctamente.");
	        } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
	        }
	    }

}
