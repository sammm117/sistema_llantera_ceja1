package sistema_llantera_ceja1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class Ordene_de_servicio extends JFrame {

private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField textDia;
private JTextField textHora;
private JTextField textCantidad;
private JTextField textTotal;

/**
 * Launch the application.
 */
public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                Ordene_de_servicio frame = new Ordene_de_servicio();
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
public Ordene_de_servicio() {
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 663, 620);
    contentPane = new JPanel();
    contentPane.setForeground(new Color(0, 0, 0));
    contentPane.setBackground(new Color(0, 128, 128));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JButton btnAtras = new JButton("Atras");
    btnAtras.setBackground(new Color(192, 192, 192));
    btnAtras.setForeground(new Color(0, 0, 0));
    btnAtras.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            menu menuPrincipal = new menu();
            menuPrincipal.setVisible(true);
            dispose();
        }
    });
    btnAtras.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    btnAtras.setBounds(10, 11, 101, 50);
    contentPane.add(btnAtras);

    JLabel lblTitulo = new JLabel("Ordenes de Servicio");
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setBackground(new Color(0, 0, 0));
    lblTitulo.setForeground(new Color(192, 192, 192));
    lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
    lblTitulo.setBounds(0, 11, 639, 58);
    contentPane.add(lblTitulo);

    JLabel lblClientes = new JLabel("Clientes ");
    lblClientes.setForeground(new Color(192, 192, 192));
    lblClientes.setBackground(new Color(0, 0, 0));
    lblClientes.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblClientes.setBounds(10, 112, 94, 58);
    contentPane.add(lblClientes);

    JLabel lblDia = new JLabel("Dia");
    lblDia.setForeground(new Color(192, 192, 192));
    lblDia.setBackground(new Color(0, 0, 0));
    lblDia.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblDia.setBounds(10, 181, 47, 58);
    contentPane.add(lblDia);

    JLabel lblServicios = new JLabel("Servicios");
    lblServicios.setForeground(new Color(192, 192, 192));
    lblServicios.setBackground(new Color(0, 0, 0));
    lblServicios.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblServicios.setBounds(10, 250, 202, 58);
    contentPane.add(lblServicios);

    JLabel lblCantidad = new JLabel("Cantidad");
    lblCantidad.setForeground(new Color(192, 192, 192));
    lblCantidad.setBackground(new Color(0, 0, 0));
    lblCantidad.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblCantidad.setBounds(10, 319, 101, 58);
    contentPane.add(lblCantidad);

    JLabel lblHora = new JLabel("Hora");
    lblHora.setForeground(new Color(192, 192, 192));
    lblHora.setBackground(new Color(0, 0, 0));
    lblHora.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblHora.setBounds(282, 181, 69, 58);
    contentPane.add(lblHora);

    JComboBox<String> comboClientes = new JComboBox<>();
    comboClientes.setBackground(new Color(192, 192, 192));
    comboClientes.setForeground(new Color(0, 0, 0));
    comboClientes.setBounds(141, 129, 210, 34);
    contentPane.add(comboClientes);

    JComboBox<String> comboServicios = new JComboBox<>();
    comboServicios.setForeground(new Color(0, 0, 0));
    comboServicios.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
    comboServicios.setBounds(141, 267, 210, 34);
    contentPane.add(comboServicios);

    // Agregar servicios con su valor
    comboServicios.addItem("Reparación - $200");
    comboServicios.addItem("Refacción - $300");
    comboServicios.addItem("Balanceo - $150");
    comboServicios.addItem("Parche - $100");
    comboServicios.addItem("Sellado - $50");

    textDia = new JTextField();
    textDia.setBackground(new Color(192, 192, 192));
    textDia.setForeground(new Color(0, 0, 0));
    textDia.setColumns(10);
    textDia.setBounds(143, 199, 116, 33);
    contentPane.add(textDia);

    textHora = new JTextField();
    textHora.setBackground(new Color(192, 192, 192));
    textHora.setForeground(new Color(0, 0, 0));
    textHora.setColumns(10);
    textHora.setBounds(361, 199, 129, 33);
    contentPane.add(textHora);

    textCantidad = new JTextField();
    textCantidad.setBackground(new Color(192, 192, 192));
    textCantidad.setForeground(new Color(0, 0, 0));
    textCantidad.setColumns(10);
    textCantidad.setBounds(141, 337, 129, 33);
    contentPane.add(textCantidad);

    // Campo para mostrar el total
    JLabel lblTotal = new JLabel("Total:");
    lblTotal.setForeground(new Color(192, 192, 192));
    lblTotal.setBackground(new Color(0, 0, 0));
    lblTotal.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    lblTotal.setBounds(300, 319, 80, 58);
    contentPane.add(lblTotal);

    textTotal = new JTextField();
    textTotal.setBackground(new Color(192, 192, 192));
    textTotal.setForeground(new Color(0, 0, 0));
    textTotal.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
    textTotal.setEditable(false); // solo lectura
    textTotal.setBounds(380, 337, 150, 33);
    contentPane.add(textTotal);
    
    JButton btnAgCliente = new JButton("AG cliente ");
    btnAgCliente.setBackground(new Color(192, 192, 192));
    btnAgCliente.setForeground(new Color(0, 0, 0));
    btnAgCliente.addActionListener(new ActionListener() {
   
			public void actionPerformed(ActionEvent e) {
				// Abre la clase MenuP y cierra la ventana actual
				clientes menuclientes = new clientes();
				menuclientes.setVisible(true);
				dispose(); // Cierra esta ventana (Citas)
			}
		});
		
    btnAgCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    btnAgCliente.setBounds(383, 125, 147, 34);
    contentPane.add(btnAgCliente);
    
    JButton btnEnviar = new JButton("Enviar ");
    btnEnviar.setBackground(new Color(192, 192, 192));
    btnEnviar.setForeground(new Color(0, 0, 0));
    btnEnviar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				// Abre la clase MenuP y cierra la ventana actual
				Estado_del_pedido menupedidos = new Estado_del_pedido();
				menupedidos.setVisible(true);
				dispose(); // Cierra esta ventana (Citas)
			}
		});
		
    btnEnviar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
    btnEnviar.setBounds(10, 418, 165, 45);
    contentPane.add(btnEnviar);

    // Evento para calcular el total
    ActionListener calcularTotal = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                String servicioSeleccionado = (String) comboServicios.getSelectedItem();
                int precio = 0;

                if (servicioSeleccionado.contains("200")) precio = 200;
                else if (servicioSeleccionado.contains("300")) precio = 300;
                else if (servicioSeleccionado.contains("150")) precio = 150;
                else if (servicioSeleccionado.contains("100")) precio = 100;
                else if (servicioSeleccionado.contains("50")) precio = 50;

                int cantidad = Integer.parseInt(textCantidad.getText());
                int total = precio * cantidad;
                textTotal.setText("$" + total);
            } catch (NumberFormatException ex) {
                textTotal.setText("Error");
            }
        }
    };

    // Calcular al cambiar servicio o cantidad
    comboServicios.addActionListener(calcularTotal);
    textCantidad.addActionListener(calcularTotal);
}
}