package sistema_llantera_ceja1;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	public menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setForeground(new Color(0, 64, 128));
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(0, 128, 128));
		contentPane_1.setBounds(0, 0, 906, 587);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel = new JLabel("Sistema de Guardado de Clientes \"Llantera Ceja\"");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(0, 29, 704, 79);
		contentPane_1.add(lblNewLabel);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnClientes.setBounds(259, 176, 165, 45);
		contentPane_1.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                clientes ventanaCliente = new clientes();
	                ventanaCliente.setVisible(true);
	            }
	        });
		
		JButton btnOrdenesDeServicios = new JButton("Ordenes de servicios");
		btnOrdenesDeServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordene_de_servicio ventanaOrden = new Ordene_de_servicio();
				ventanaOrden.setVisible(true);
			}
		});
		btnOrdenesDeServicios.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnOrdenesDeServicios.setBounds(206, 284, 284, 45);
		contentPane_1.add(btnOrdenesDeServicios);
		
		JButton btnHistorial = new JButton("Historial");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historial ventanaHistorial = new historial();
				ventanaHistorial.setVisible(true);
			}
		});
		btnHistorial.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		btnHistorial.setBounds(259, 384, 165, 45);
		contentPane_1.add(btnHistorial);

	}
}
