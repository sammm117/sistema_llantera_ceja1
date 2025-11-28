package sistema_llantera_ceja1;

// --- IMPORTACIONES ---
// Librerías de interfaz gráfica (Ventanas, Botones, Textos)
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
// Librerías SQL para la base de datos
import java.sql.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase Estado_del_pedido
 * Función: Permite buscar un pedido por ID o Nombre y cambiar su estado (ej. "En proceso" a "Terminado").
 */
public class Estado_del_pedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // --- CAMPOS DE LA INTERFAZ ---
    // 1. Campo donde el usuario escribe lo que quiere buscar (Editable)
    private JTextField txtInputBusqueda;   
    // 2. Campo donde se muestra el ID encontrado (Solo lectura - Gris)
    private JTextField txtIDEncontrado;    
    // 3. Campo donde se muestra el Nombre encontrado (Solo lectura - Gris)
    private JTextField txtNombreEncontrado;
    
    // Etiqueta para mostrar mensajes de éxito o error al usuario
    private JLabel lblMensaje; 

    // --- CREDENCIALES DE CONEXIÓN MYSQL ---
    private final String URL = "jdbc:mysql://localhost:3306/sistema_llantera";
    private final String USER = "root";
    private final String PASS = "steven";

    // --- MÉTODO MAIN (Punto de entrada) ---
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Estado_del_pedido frame = new Estado_del_pedido();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // --- CONSTRUCTOR DE LA VENTANA ---
    public Estado_del_pedido() {
        // Configuración básica de la ventana
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 608); // Tamaño ajustado para que quepan todos los componentes
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128)); // Color de fondo (Verde azulado)
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // Usamos layout nulo para posicionar elementos con coordenadas (x, y)
        contentPane.setLayout(null);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Estados del Pedido");
        lblTitulo.setBounds(15, 67, 606, 50);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lblTitulo.setForeground(Color.WHITE);
        contentPane.add(lblTitulo);

        // --- SECCIÓN 1: BÚSQUEDA ---
        JLabel lblBuscar = new JLabel("Buscar:"); 
        lblBuscar.setBounds(15, 137, 100, 42);
        lblBuscar.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
        contentPane.add(lblBuscar);
        
        // Campo donde el usuario escribe el ID o el Nombre
        txtInputBusqueda = new JTextField();
        txtInputBusqueda.setBounds(194, 140, 279, 42);
        txtInputBusqueda.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        txtInputBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
        txtInputBusqueda.setToolTipText("Escriba ID o Nombre aquí"); // Ayuda visual al pasar el mouse
        contentPane.add(txtInputBusqueda);

        // Botón que ejecuta la búsqueda
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(483, 140, 105, 42);
        btnBuscar.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(btnBuscar);

        // --- SECCIÓN 2: RESULTADOS (Solo Lectura) ---
        // Campo para mostrar el ID verificado
        JLabel lblIdEncontrado = new JLabel("ID:");
        lblIdEncontrado.setBounds(15, 189, 100, 34);
        lblIdEncontrado.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
        contentPane.add(lblIdEncontrado);
        
        txtIDEncontrado = new JTextField();
        txtIDEncontrado.setBounds(194, 200, 100, 38);
        txtIDEncontrado.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        txtIDEncontrado.setEditable(false); // IMPORTANTE: No se puede editar manualmente
        txtIDEncontrado.setBackground(new Color(220, 220, 220)); // Color gris para indicar solo lectura
        txtIDEncontrado.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(txtIDEncontrado);

        // Campo para mostrar el Nombre verificado
        JLabel lblNombreEncontrado = new JLabel("Cliente:");
        lblNombreEncontrado.setBounds(15, 233, 100, 34);
        lblNombreEncontrado.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
        contentPane.add(lblNombreEncontrado);
        
        txtNombreEncontrado = new JTextField();
        txtNombreEncontrado.setBounds(194, 248, 280, 38);
        txtNombreEncontrado.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        txtNombreEncontrado.setEditable(false); // Solo lectura
        txtNombreEncontrado.setBackground(new Color(220, 220, 220)); // Gris
        contentPane.add(txtNombreEncontrado);

        // --- SECCIÓN 3: ESTADO ACTUAL Y CAMBIO ---
        // Area de texto que muestra el estado actual en la base de datos
        JLabel lblEstadoActual = new JLabel("Estado actual");
        lblEstadoActual.setBounds(15, 285, 175, 34);
        lblEstadoActual.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
        contentPane.add(lblEstadoActual);

        JTextArea EstadoA = new JTextArea();
        EstadoA.setBounds(194, 304, 280, 38);
        EstadoA.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        EstadoA.setEditable(false); // Solo lectura
        contentPane.add(EstadoA);

        // Lista desplegable (ComboBox) para seleccionar el NUEVO estado
        JComboBox cambio = new JComboBox();
        cambio.setBounds(194, 360, 280, 39);
        cambio.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        cambio.setModel(new DefaultComboBoxModel(new String[]{
                "", "En Proceso", "Guardando Proceso", "Terminado"
        }));
        contentPane.add(cambio);

        // --- BOTONES DE ACCIÓN ---
        // Botón para regresar al menú principal
        JButton Inicio = new JButton("Menu");
        Inicio.setBounds(5, 16, 120, 45);
        Inicio.addActionListener(e -> {
             menu ventanaMenu = new menu();
             ventanaMenu.setVisible(true);
             ((JFrame) SwingUtilities.getWindowAncestor(Inicio)).dispose();
        });
        Inicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(Inicio);

        // Botón para guardar los cambios en la BD
        JButton Actualizar = new JButton("Guardar");
        Actualizar.setBounds(194, 456, 160, 60);
        Actualizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        contentPane.add(Actualizar);

        // Etiqueta de mensajes (Feedback al usuario)
        lblMensaje = new JLabel("");
        lblMensaje.setBounds(25, 410, 606, 40);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        lblMensaje.setForeground(Color.YELLOW);
        contentPane.add(lblMensaje);

        // ===========================================================
        // LÓGICA DEL BOTÓN BUSCAR
        // ===========================================================
        btnBuscar.addActionListener(e -> {
            // Limpieza inicial de campos y colores
            lblMensaje.setText(""); 
            txtInputBusqueda.setBackground(Color.WHITE); 
            txtIDEncontrado.setText(""); 
            txtNombreEncontrado.setText("");
            EstadoA.setText("");
            
            String textoBusqueda = txtInputBusqueda.getText().trim();

            // Validación: ¿Está vacío el campo?
            if (textoBusqueda.isEmpty()) {
                txtInputBusqueda.setBackground(Color.PINK);
                lblMensaje.setForeground(Color.YELLOW);
                lblMensaje.setText("Escriba algo para buscar.");
                return;
            }

            try (Connection cn = DriverManager.getConnection(URL, USER, PASS)) {
                PreparedStatement ps;
                ResultSet rs;
                boolean encontrado = false; // Bandera para saber si encontramos algo

                // --- ESTRATEGIA 1: BUSCAR POR NOMBRE ---
                // Usamos LIKE para buscar nombres parecidos
                ps = cn.prepareStatement("SELECT * FROM ordenes WHERE nombre_cliente LIKE ?");
                ps.setString(1, textoBusqueda + "%"); 
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Si encontramos por nombre, llenamos los campos GRISES
                    txtIDEncontrado.setText(rs.getString("id_clientes")); 
                    txtNombreEncontrado.setText(rs.getString("nombre_cliente"));
                    EstadoA.setText(rs.getString("estado"));
                    
                    lblMensaje.setForeground(Color.GREEN);
                    lblMensaje.setText("Cliente encontrado.");
                    encontrado = true;
                } 
                
                // --- ESTRATEGIA 2: BUSCAR POR ID ---
                // Solo si no encontramos por nombre Y si el texto es un número
                if (!encontrado && textoBusqueda.matches("\\d+")) {
                    ps = cn.prepareStatement("SELECT * FROM ordenes WHERE id_clientes = ?");
                    ps.setInt(1, Integer.parseInt(textoBusqueda));
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        txtIDEncontrado.setText(rs.getString("id_clientes"));
                        txtNombreEncontrado.setText(rs.getString("nombre_cliente"));
                        EstadoA.setText(rs.getString("estado"));
                        
                        lblMensaje.setForeground(Color.GREEN);
                        lblMensaje.setText("Pedido encontrado.");
                        encontrado = true;
                    }
                }

                // Si ninguna estrategia funcionó
                if (!encontrado) {
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("No se encontraron resultados.");
                }

            } catch (Exception ex) {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Error BD: " + ex.getMessage());
            }
        });

        // ===========================================================
        // LÓGICA DEL BOTÓN GUARDAR (ACTUALIZAR)
        // ===========================================================
        Actualizar.addActionListener(e -> {
            lblMensaje.setText("");
            
            // IMPORTANTE: Para guardar, usamos el ID del campo GRIS (txtIDEncontrado)
            // porque ese ID ya fue verificado por la base de datos.
            // NO usamos txtInputBusqueda porque el usuario podría haberlo cambiado.
            String idVerificado = txtIDEncontrado.getText().trim();
            Object selectedItem = cambio.getSelectedItem();
            String nuevoEstado = (selectedItem != null) ? selectedItem.toString() : "";

            // Validaciones antes de guardar
            if (idVerificado.isEmpty()) {
                lblMensaje.setForeground(Color.YELLOW);
                lblMensaje.setText("Primero busque un cliente válido.");
                return;
            }
            if (nuevoEstado.isEmpty()) {
                lblMensaje.setForeground(Color.YELLOW);
                lblMensaje.setText("Seleccione un estado nuevo.");
                return;
            }

            try (Connection cn = DriverManager.getConnection(URL, USER, PASS)) {
                // Sentencia SQL para actualizar (UPDATE)
                String sql = "UPDATE ordenes SET estado = ? WHERE id_clientes = ?";
                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setString(1, nuevoEstado);
                ps.setString(2, idVerificado); 

                int filas = ps.executeUpdate(); // Ejecuta la actualización

                if (filas > 0) {
                    lblMensaje.setForeground(Color.GREEN); // Éxito en verde
                    lblMensaje.setText("¡Estado actualizado con éxito!");
                    EstadoA.setText(nuevoEstado); // Actualizamos visualmente el estado actual
                } else {
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Error al guardar.");
                }

            } catch (Exception ex) {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Error SQL: " + ex.getMessage());
            }
        });
    }
}