package sistema_llantera_ceja1;

// --- IMPORTACIONES ---
// Librerías gráficas (Ventanas, Botones, Textos)
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;

// Librerías para conexión a Base de Datos (JDBC)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase: Clientes
 * Función: Ventana de registro para nuevos clientes.
 * Características: Valida datos (nombre, teléfono, correo) antes de guardar en MySQL.
 */
public class clientes extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Panel principal
    private JPanel contentPane;
    
    // Campos de texto para la entrada de datos
    private JTextField C_nombre;
    private JTextField C_telefono;
    private JTextField C_correo;
    
    // Etiqueta global para mostrar mensajes de error o éxito (sin usar ventanas emergentes)
    private JLabel lblMensaje; 

    // --- MÉTODO MAIN ---
    // Punto de entrada: Inicia y hace visible la ventana
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

    // --- CONSTRUCTOR ---
    // Aquí se define el diseño visual de la ventana
    public clientes() {
        // Configuración básica de la ventana (Cerrar al salir, tamaño, posición)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 577, 650); // Altura ajustada para que quepa el mensaje de error
        
        // Configuración del panel contenedor
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto (coordenadas X, Y manuales)

        // Panel interior con color de fondo (Verde Azulado / Teal)
        JPanel contentPane_1 = new JPanel();
        contentPane_1.setLayout(null);
        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane_1.setBackground(new Color(0, 128, 128));
        contentPane_1.setBounds(0, 0, 563, 620);
        contentPane.add(contentPane_1);

        // --- BOTÓN DE NAVEGACIÓN (ATRÁS) ---
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre el menú principal y cierra esta ventana de registro
                menu ventanaMenu = new menu();
                ventanaMenu.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(btnAtras)).dispose();
            }
        });
        btnAtras.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnAtras.setBounds(10, 11, 101, 50);
        contentPane_1.add(btnAtras);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lblTitulo.setBounds(10, 11, 542, 58);
        contentPane_1.add(lblTitulo);

        // --- CAMPO: NOMBRE ---
        JLabel lblNombre = new JLabel("Nombre del cliente");
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        lblNombre.setBounds(36, 130, 202, 58);
        contentPane_1.add(lblNombre);

        C_nombre = new JTextField();
        C_nombre.setBounds(248, 148, 227, 33);
        contentPane_1.add(C_nombre);

        // --- CAMPO: TELÉFONO ---
        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        lblTelefono.setBounds(36, 207, 103, 58);
        contentPane_1.add(lblTelefono);

        C_telefono = new JTextField();
        C_telefono.setBounds(149, 225, 194, 33);
        contentPane_1.add(C_telefono);

        // --- CAMPO: CORREO ---
        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        lblCorreo.setBounds(36, 300, 157, 58);
        contentPane_1.add(lblCorreo);

        C_correo = new JTextField();
        C_correo.setBounds(125, 318, 194, 33);
        contentPane_1.add(C_correo);

        // --- BOTONES DE ACCIÓN ---
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnAgregar.setBounds(248, 407, 140, 50);
        contentPane_1.add(btnAgregar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnLimpiar.setBounds(100, 407, 120, 50);
        contentPane_1.add(btnLimpiar);
        
        // --- ETIQUETA DE FEEDBACK (Sustituye a los JOptionPane) ---
        lblMensaje = new JLabel("");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 14));
        lblMensaje.setForeground(new Color(255, 0, 0)); // Rojo por defecto para errores
        lblMensaje.setBounds(12, 362, 540, 40); // Posicionada debajo de los campos
        contentPane_1.add(lblMensaje);

        // ----------------------------------------------------
        // LÓGICA DEL BOTÓN LIMPIAR
        // ----------------------------------------------------
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Borra el texto de todos los campos
                C_nombre.setText("");
                C_telefono.setText("");
                C_correo.setText("");
                lblMensaje.setText(""); // Borra el mensaje de error/éxito

                // Restaura el color blanco de fondo en caso de haber errores previos
                C_nombre.setBackground(Color.WHITE);
                C_telefono.setBackground(Color.WHITE);
                C_correo.setBackground(Color.WHITE);
            }
        });

        // ----------------------------------------------------
        // LÓGICA DEL BOTÓN AGREGAR (CON VALIDACIONES)
        // ----------------------------------------------------
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Obtener texto y quitar espacios extra (.trim())
                String nombre = C_nombre.getText().trim();
                String telefono = C_telefono.getText().trim();
                String correo = C_correo.getText().trim();

                // Reset visual antes de validar (volver a blanco y borrar mensajes)
                C_nombre.setBackground(Color.WHITE);
                C_telefono.setBackground(Color.WHITE);
                C_correo.setBackground(Color.WHITE);
                lblMensaje.setText(""); 

                // VALIDACIÓN 1: NOMBRE
                // Regex: Busca letras + espacio + letras. Obliga a poner al menos nombre y apellido.
                if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+\\s+[A-Za-zÁÉÍÓÚáéíóúñÑ]+$")) {
                    C_nombre.setBackground(Color.PINK); // Pintar de rojo/rosa
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Error: El nombre debe tener al menos nombre y apellido.");
                    return; // Detener la ejecución aquí
                }

                // VALIDACIÓN 2: TELÉFONO
                // Regex: Busca exactamente 10 dígitos numéricos (\\d{10})
                if (!telefono.matches("^\\d{10}$")) {
                    C_telefono.setBackground(Color.PINK);
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Error: El teléfono debe ser de 10 dígitos.");
                    return;
                }

                // VALIDACIÓN 3: CORREO
                // Regex: Valida estructura texto@texto.dominio
                if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    C_correo.setBackground(Color.PINK);
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Error: Formato de correo inválido.");
                    return;
                }

                // Si pasa todas las validaciones, procedemos a guardar en BD
                guardarCliente(nombre, telefono, correo);
            }
        });
    }

    // ----------------------------------------------------
    // MÉTODO PARA INSERTAR EN BASE DE DATOS
    // ----------------------------------------------------
    private void guardarCliente(String nombre, String telefono, String correo) {

        // Credenciales de conexión
        String url = "jdbc:mysql://localhost:3306/sistema_llantera";
        String usuario = "root";
        String contraseña = "steven"; 

        // Consulta SQL parametrizada (los ? evitan inyección SQL)
        String sql = "INSERT INTO clientes (nombre, telefono, correo) VALUES (?, ?, ?)";

        try {
            // 1. Cargar Driver
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            // 2. Establecer conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            // 3. Preparar la sentencia SQL
            PreparedStatement ps = conexion.prepareStatement(sql);

            // 4. Asignar valores a los signos de interrogación
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, correo);

            // 5. Ejecutar la inserción
            ps.executeUpdate();

            // 6. Cerrar recursos
            ps.close();
            conexion.close();

            // Mensaje de ÉXITO (En verde)
            lblMensaje.setForeground(new Color(50, 205, 50)); // Verde lima
            lblMensaje.setText("¡Éxito! Cliente guardado correctamente.");
            
            // Limpiar campos para permitir un nuevo registro rápido
            C_nombre.setText("");
            C_telefono.setText("");
            C_correo.setText("");

        } catch (SQLException | ClassNotFoundException e) {
            // Manejo de ERRORES (En rojo)
            lblMensaje.setForeground(Color.RED); 
            
            // Verificar si el error es porque el teléfono ya existe (Clave única)
            if (e.getMessage().contains("Duplicate entry")) {
                lblMensaje.setText("Error: Ese teléfono ya está registrado.");
            } else {
                lblMensaje.setText("Error Base de Datos: Verifique la conexión.");
                System.out.println(e.getMessage()); // Imprimir error técnico en consola
            }
        }
    }
}