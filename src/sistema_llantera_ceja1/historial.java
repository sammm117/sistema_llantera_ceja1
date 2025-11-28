package sistema_llantera_ceja1;

// --- SECCIÓN DE IMPORTACIONES ---
// Librerías para la interfaz gráfica (Ventanas, Botones, Tablas, Paneles)
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

// Librerías para detectar acciones del ratón (Clics)
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Librerías para la conexión a Base de Datos (SQL)
import java.sql.*; 

public class historial extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Componentes de la interfaz
    private JPanel contentPane;
    private JLabel lblHistorial;
    private JTable table; // La tabla visual donde se mostrarán los datos

    // --- CREDENCIALES DE BASE DE DATOS ---
    // Configuración para conectar a MySQL (Asegúrate que el puerto 3306 sea correcto)
    private final String URL = "jdbc:mysql://localhost:3306/sistema_llantera";
    private final String USER = "root";
    private final String PASS = "steven"; 

    // --- MÉTODO MAIN ---
    // Punto de entrada de la aplicación, inicia la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    historial frame = new historial();
                    frame.setVisible(true); // Hace visible la ventana
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // --- CONSTRUCTOR DE LA CLASE ---
    // Aquí se construye toda la parte visual de la ventana
    public historial() {
        // Configuración básica: cerrar al salir, tamaño y posición
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1130, 600);
        
        // Configuración del panel principal (Fondo color Verde Azulado / Teal)
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // --- BOTÓN DE MENÚ ---
        JButton btnmenu = new JButton("Menu");
        // Acción: Al hacer clic, cierra esta ventana y abre el menú principal
        btnmenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu menuPrincipal = new menu();
                menuPrincipal.setVisible(true);
                dispose(); // Cierra la ventana actual de Historial
            }
        });
        btnmenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        
        // --- TÍTULO ---
        lblHistorial = new JLabel("Historial de Ventas");
        lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
        lblHistorial.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lblHistorial.setForeground(Color.WHITE); 
        
        // --- TABLA Y SCROLL ---
        // JScrollPane permite hacer scroll si hay muchos registros
        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14)); 
        
        // -------------------------------------------------------------
        // EVENTO IMPORTANTE: DETECTAR CLIC EN LA TABLA
        // -------------------------------------------------------------
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 1. Obtener el índice de la fila que el usuario seleccionó
                int fila = table.getSelectedRow();
                
                // Si la fila es válida (mayor o igual a 0)
                if (fila >= 0) {
                    // 2. Extraer los datos de esa fila específica columna por columna
                    // getValueAt(fila, columna) devuelve un Objeto, lo convertimos a String
                    String id      = table.getValueAt(fila, 0).toString();
                    String nombre  = table.getValueAt(fila, 1).toString();
                    String fecha   = table.getValueAt(fila, 2).toString();
                    String hora    = table.getValueAt(fila, 3).toString();
                    String servicio = table.getValueAt(fila, 4).toString(); 
                    String cantidad = table.getValueAt(fila, 5).toString();
                    String total    = table.getValueAt(fila, 6).toString();
                    
                    // 3. Crear una instancia de la ventana 'Ordene_de_servicio'
                    Ordene_de_servicio ventanaOrden = new Ordene_de_servicio();
                    
                    // 4. Usar el método especial 'cargarDatosDesdeHistorial' para enviar
                    // los datos que sacamos de la tabla hacia los campos de texto de la otra ventana
                    ventanaOrden.cargarDatosDesdeHistorial(id, nombre, fecha, hora, servicio, cantidad, total);
                    
                    // 5. Mostrar la nueva ventana y cerrar el historial
                    ventanaOrden.setVisible(true);
                    dispose();
                }
            }
        });
        // -------------------------------------------------------------

        // Agrega la tabla dentro del panel con scroll
        scrollPane.setViewportView(table);

        // --- DISEÑO (GROUP LAYOUT) ---
        // Define las posiciones de los componentes en la ventana
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE) 
                        .addGroup(gl_contentPane.createSequentialGroup()
                            .addComponent(btnmenu, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblHistorial, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(17)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblHistorial, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnmenu, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE) 
                    .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);

        // --- CARGA DE DATOS ---
        // Al terminar de dibujar la ventana, llamamos a la BD para llenar la tabla
        cargarTabla();
    }

    // --- MÉTODO PARA LLENAR LA TABLA DESDE MYSQL ---
    private void cargarTabla() {
        // Definimos el modelo de la tabla
        // Sobreescribimos 'isCellEditable' para que el usuario NO pueda editar la tabla escribiendo en ella
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Agregamos los encabezados de las columnas
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Servicio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        modelo.addColumn("Estado");

        // Asignamos este modelo a nuestra tabla visual
        table.setModel(modelo);

        // Consulta SQL para traer todos los registros
        String sql = "SELECT * FROM ordenes"; 

        try {
            // 1. Cargar el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. Establecer conexión
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            // 3. Crear el objeto para ejecutar sentencias
            Statement st = con.createStatement();
            // 4. Ejecutar consulta y obtener resultados (ResultSet)
            ResultSet rs = st.executeQuery(sql);

            // 5. Recorrer los resultados fila por fila
            while (rs.next()) {
                // Crear un arreglo de objetos para guardar los datos de una fila
                Object[] filas = new Object[8];
                
                // Extraer datos de la BD usando los nombres exactos de las columnas en MySQL
                filas[0] = rs.getInt("id_clientes");
                filas[1] = rs.getString("nombre_cliente");
                filas[2] = rs.getString("dia");
                filas[3] = rs.getString("hora");
                filas[4] = rs.getString("servicio");
                filas[5] = rs.getString("cantidad");
                filas[6] = rs.getDouble("total");
                filas[7] = rs.getString("estado");

                // Agregar la fila al modelo de la tabla
                modelo.addRow(filas);
            }
            
            // Actualizar la tabla visual con los datos cargados
            table.setModel(modelo);
            
            // Cerrar conexión para liberar recursos
            con.close();

        } catch (Exception e) {
            // Si hay error (ej. BD apagada), mostrar mensaje en consola o ventana
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar historial: " + e.getMessage());
        }
    }
}