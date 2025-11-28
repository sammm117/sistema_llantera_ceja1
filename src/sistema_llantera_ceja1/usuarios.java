package sistema_llantera_ceja1;

// --- IMPORTACIONES ---
// Librerías gráficas para ventanas, botones y diseños
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField; // Importante: Campo de texto que oculta los caracteres

/**
 * Clase: Usuarios (Login)
 * Función: Es la primera pantalla que ve el usuario. Solicita una contraseña para acceder al sistema.
 * Seguridad: Usa un campo de contraseña enmascarado y valida credenciales simples.
 */
public class usuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Panel principal
    private JPanel contentPane;
    
    // Campo especial para contraseñas (muestra ***** en lugar del texto)
    private JPasswordField passwordField;
    
    // Etiqueta para mostrar mensajes de error (Sustituye a los JOptionPane)
    private JLabel lblMensaje; 

    // --- MÉTODO MAIN ---
    // Punto de entrada: Ejecuta la ventana de Login al iniciar la app
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

    // --- CONSTRUCTOR ---
    // Configura el diseño visual y la lógica de los botones
    public usuarios() {

        // 1. Configuración de la ventana
        setResizable(false); // Evita que el usuario cambie el tamaño de la ventana
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la app al pulsar la X
        setBounds(100, 100, 636, 620); // Tamaño y posición
        
        // 2. Configuración del panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // 3. Panel interior con color de fondo
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128)); // Color "Teal" (Verde azulado)
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null); // Layout absoluto para coordenadas manuales

        // --- COMPONENTES GRÁFICOS ---

        // Título Principal
        JLabel lblTitulo = new JLabel("Inicio de Sesión");
        lblTitulo.setBounds(10, 40, 582, 45);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);

        // Instrucciones
        JLabel lblInstr = new JLabel("Ingrese su contraseña");
        lblInstr.setBounds(10, 110, 582, 45);
        lblInstr.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstr.setForeground(new Color(0, 0, 0));
        lblInstr.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        panel.add(lblInstr);

        // Etiqueta "Contraseña:"
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(59, 166, 131, 45);
        lblPass.setForeground(new Color(0, 0, 0));
        lblPass.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        panel.add(lblPass);

        // Campo de entrada de contraseña
        passwordField = new JPasswordField();
        passwordField.setBounds(184, 171, 240, 34);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        panel.add(passwordField);

        // Botón Ingresar
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(184, 246, 240, 54);
        btnIngresar.setBackground(new Color(192, 192, 192)); // Color gris claro
        btnIngresar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        panel.add(btnIngresar);

        // --- ETIQUETA DE MENSAJES DE ERROR ---
        // Se coloca debajo del botón. Inicia vacía.
        // Si la contraseña falla, aquí aparecerá el texto en rojo.
        lblMensaje = new JLabel(""); 
        lblMensaje.setBounds(20, 205, 582, 30);
        lblMensaje.setForeground(new Color(255, 0, 0)); // Color rojo para errores
        lblMensaje.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblMensaje);

        // --- LÓGICA DE VALIDACIÓN (BOTÓN) ---
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Contraseña correcta definida en código (Hardcoded)
                // Nota: En un sistema real, esto debería venir de la Base de Datos
                String contraseñaEsperada = "12345";
                
                // Obtener lo que el usuario escribió
                String contraseñaIngresada = new String(passwordField.getPassword());

                // Comparación
                if (contraseñaIngresada.equals(contraseñaEsperada)) {
                    // CASO ÉXITO:
                    // 1. Instanciamos la ventana del menú principal
                    menu menuPrincipal = new menu();
                    // 2. La hacemos visible
                    menuPrincipal.setVisible(true);
                    // 3. Cerramos (dispose) la ventana de login actual
                    dispose();
                } else {
                    // CASO ERROR:
                    // 1. Mostramos mensaje en rojo en la etiqueta (NO ventana emergente)
                    lblMensaje.setText("Contraseña incorrecta, intente de nuevo.");
                    
                    // 2. Limpiamos el campo de contraseña
                    passwordField.setText("");
                    
                    // 3. Regresamos el cursor al campo para volver a escribir
                    passwordField.requestFocus();
                }
            }
        });
    }
}