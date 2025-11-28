package sistema_llantera_ceja1;

// --- SECCIÓN DE IMPORTACIONES ---
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

/**
 * Clase: Menu
 * Función: Es la pantalla principal (Dashboard) del sistema.
 * Objetivo: Permitir la navegación hacia los diferentes módulos (Clientes, Órdenes, Historial, Estado).
 */
public class menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // --- MÉTODO MAIN ---
    // Punto de entrada de la aplicación. Ejecuta la ventana del menú.
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

    // --- CONSTRUCTOR ---
    // Aquí se define toda la interfaz gráfica y los botones de navegación.
    public menu() {
        // 1. Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al salir
        setBounds(100, 100, 718, 621); // Tamaño y posición
        
        // 2. Configuración del panel contenedor principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto (coordenadas x,y manuales)
        
        // 3. Panel decorativo de fondo
        JPanel contentPane_1 = new JPanel();
        contentPane_1.setLayout(null);
        contentPane_1.setForeground(new Color(0, 64, 128));
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBackground(new Color(0, 128, 128)); // Color "Teal" (Verde azulado)
        contentPane_1.setBounds(0, 0, 704, 587); // Ocupa casi toda la ventana
        contentPane.add(contentPane_1);
        
        // 4. Título del Sistema
        JLabel lblNewLabel = new JLabel("Sistema de Guardado de Clientes \"Llantera Ceja\"");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(255, 255, 255)); // Texto blanco
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setBounds(0, 29, 704, 79);
        contentPane_1.add(lblNewLabel);
        
        // --- BOTONES DE NAVEGACIÓN ---

        // A) BOTÓN CLIENTES
        // Abre la ventana de registro de clientes
        JButton btnClientes = new JButton("Clientes");
        btnClientes.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnClientes.setBounds(259, 176, 165, 45);
        contentPane_1.add(btnClientes);
        
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Instancia la clase clientes y la hace visible
                clientes ventanaCliente = new clientes();
                ventanaCliente.setVisible(true);
                // Nota: Aquí no usamos 'dispose()', por lo que el menú sigue abierto de fondo
            }
        });
        
        // B) BOTÓN ÓRDENES DE SERVICIO
        // Abre la ventana para crear una nueva orden
        JButton btnOrdenesDeServicios = new JButton("Ordenes de servicios");
        btnOrdenesDeServicios.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnOrdenesDeServicios.setBounds(203, 255, 284, 45);
        contentPane_1.add(btnOrdenesDeServicios);
        
        btnOrdenesDeServicios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ordene_de_servicio ventanaOrden = new Ordene_de_servicio();
                ventanaOrden.setVisible(true);
            }
        });
        
        // C) BOTÓN ESTADO DEL PEDIDO
        // Abre la ventana para actualizar el estado (En proceso/Terminado)
        JButton btnEstadoDelPedido = new JButton("Estado del Pedido");
        btnEstadoDelPedido.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnEstadoDelPedido.setBounds(203, 323, 284, 45);
        contentPane_1.add(btnEstadoDelPedido);

        btnEstadoDelPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Estado_del_pedido ventana = new Estado_del_pedido();
                ventana.setVisible(true);
                dispose(); // IMPORTANTE: 'dispose()' cierra el menú para ir a la otra ventana
            }
        });
        
        // D) BOTÓN HISTORIAL
        // Abre la tabla con todas las ventas registradas
        JButton btnHistorial = new JButton("Historial");
        btnHistorial.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnHistorial.setBounds(259, 392, 165, 45);
        contentPane_1.add(btnHistorial);
        
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historial ventanaHistorial = new historial();
                ventanaHistorial.setVisible(true);
            }
        });
    }
}