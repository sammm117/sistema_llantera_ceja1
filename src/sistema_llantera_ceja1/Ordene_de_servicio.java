package sistema_llantera_ceja1;

// --- SECCIÓN DE IMPORTACIONES ---
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
// Librerías SQL para base de datos
import java.sql.*;
// Librerías para validación de fechas
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Clase: Ordene_de_servicio
 * Función: Permite crear nuevas órdenes, buscar clientes existentes, 
 * calcular costos automáticamente y validar que los datos sean correctos antes de guardar.
 */
public class Ordene_de_servicio extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Panel principal donde se colocan los elementos
    private JPanel contentPane;
    
    // --- CAMPOS DE TEXTO (Inputs) ---
    private JTextField textIdCliente;   // ID del cliente (se usa para buscar o guardar)
    private JTextField textDia;         // Fecha de la orden
    private JTextField textHora;        // Hora de la orden
    private JTextField textCantidad;    // Cantidad de llantas/servicios
    private JTextField textTotal;       // Costo total (se calcula solo)
    private JTextField textServicio;    // Número del servicio (1, 2, 3, 4)
    private JTextField nombre_cliente;  // Nombre del cliente (editable para buscar)
    
    // Etiqueta inferior para dar feedback al usuario (Errores en rojo, Éxitos en verde)
    private JLabel lblMensaje; 

    // --- MÉTODO MAIN ---
    // Inicia la ejecución de esta ventana específica
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ordene_de_servicio frame = new Ordene_de_servicio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // --- CONSTRUCTOR ---
    // Aquí se diseña la interfaz gráfica
    public Ordene_de_servicio() {
        // Configuración de la ventana (tamaño, cierre, no redimensionable)
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 715, 660);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128)); // Color Teal de fondo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto (coordenadas x,y)

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Ordenes de Servicio");
        lblTitulo.setBounds(116, 11, 562, 58);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        contentPane.add(lblTitulo);

        // --- CAMPO ID CLIENTE ---
        JLabel lblIdCliente = new JLabel("ID Cliente:"); 
        lblIdCliente.setBounds(10, 186, 216, 40);
        lblIdCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblIdCliente);

        textIdCliente = new JTextField();
        textIdCliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textIdCliente.setBounds(133, 190, 66, 33);
        contentPane.add(textIdCliente);

        // --- BOTÓN BUSCAR ---
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(526, 192, 120, 33);
        btnBuscar.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        contentPane.add(btnBuscar);

        // --- CAMPO NOMBRE ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(207, 186, 100, 40);
        lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblNombre);

        nombre_cliente = new JTextField();
        nombre_cliente.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        nombre_cliente.setBounds(317, 190, 199, 33);
        // IMPORTANTE: Se permite escribir aquí para buscar por nombre
        nombre_cliente.setEditable(true); 
        contentPane.add(nombre_cliente);

        // --- CAMPO FECHA ---
        JLabel lblDia = new JLabel("Fecha:");
        lblDia.setBounds(10, 259, 79, 40);
        lblDia.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblDia);

        textDia = new JTextField();
        textDia.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textDia.setBounds(133, 263, 200, 33);
        textDia.setToolTipText("DD/MM/AAAA"); // Ayuda visual al pasar el mouse
        contentPane.add(textDia);

        // --- CAMPO HORA ---
        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(10, 310, 57, 40);
        lblHora.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblHora);

        textHora = new JTextField();
        textHora.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textHora.setBounds(133, 314, 200, 33);
        contentPane.add(textHora);

        // --- CAMPO SERVICIO (1-4) ---
        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setBounds(10, 354, 100, 40);
        lblServicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblServicio);

        textServicio = new JTextField();
        textServicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textServicio.setBounds(133, 358, 200, 33);
        contentPane.add(textServicio);

        // --- CAMPO CANTIDAD ---
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10, 398, 100, 40);
        lblCantidad.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblCantidad);

        textCantidad = new JTextField();
        textCantidad.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textCantidad.setBounds(134, 402, 199, 33);
        contentPane.add(textCantidad);

        // --- CAMPO TOTAL (Calculado) ---
        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(10, 449, 66, 40);
        lblTotal.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblTotal);

        textTotal = new JTextField();
        textTotal.setBounds(133, 446, 200, 33);
        textTotal.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        textTotal.setEditable(false); // No editable, se calcula solo
        contentPane.add(textTotal);

        // --- BOTÓN REGISTRAR ---
        JButton btnEnviar = new JButton("Registrar Orden");
        btnEnviar.setBounds(134, 504, 216, 45);
        btnEnviar.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(btnEnviar);

        // --- BOTÓN ATRÁS (MENÚ) ---
        JButton atras = new JButton("Atrás");
        atras.addActionListener(e -> {
            menu ventanaMenu = new menu();
            ventanaMenu.setVisible(true);
            dispose();
        });
        atras.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
        atras.setBounds(10, 11, 100, 40);
        contentPane.add(atras);

        // --- LISTA DE PRECIOS VISUAL ---
        JLabel lblBalanceo = new JLabel("1) Balanceo - $100");
        lblBalanceo.setBounds(384, 263, 239, 40);
        lblBalanceo.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblBalanceo);

        JLabel lblRotacion = new JLabel("2) Rotacion - $200");
        lblRotacion.setBounds(384, 310, 192, 40);
        lblRotacion.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblRotacion);

        JLabel lblParchado = new JLabel("3) Parchado - $75");
        lblParchado.setBounds(384, 354, 216, 40);
        lblParchado.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblParchado);

        JLabel lblMontaje = new JLabel("4) Montaje  - $50");
        lblMontaje.setBounds(384, 398, 195, 40);
        lblMontaje.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        contentPane.add(lblMontaje);

        // Instrucciones visuales
        JLabel lblInst1 = new JLabel("Dependiendo del número del servicio");
        lblInst1.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        lblInst1.setBounds(10, 107, 636, 40);
        contentPane.add(lblInst1);

        JLabel lblInst2 = new JLabel("Seleccione el número para identificar el pedido");
        lblInst2.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        lblInst2.setBounds(10, 143, 636, 40);
        contentPane.add(lblInst2);

        // --- ETIQUETA DE MENSAJES ---
        // Aquí mostramos errores o éxito sin usar popups
        lblMensaje = new JLabel("");
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        lblMensaje.setForeground(Color.YELLOW);
        lblMensaje.setBounds(10, 223, 681, 30);
        contentPane.add(lblMensaje);

        // --- LISTENERS (ACCIONES) ---
        
        // 1. Botón Buscar: Llama a la función buscarCliente()
        btnBuscar.addActionListener(e -> buscarCliente());
        
        // 2. Botón Enviar: Llama a registrarOrden() para validar y guardar
        btnEnviar.addActionListener(e -> registrarOrden());

        // 3. Cálculo Automático:
        // Cada vez que el usuario suelta una tecla en 'textServicio', recalculamos el total
        textServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcularTotal();
            }
        });
        
        // Cada vez que el usuario suelta una tecla en 'textCantidad', recalculamos el total
        textCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcularTotal();
            }
        });
    }

    // --- MÉTODOS DE AYUDA VISUAL Y LÓGICA ---

    // Método para pintar una caja de texto: Rosa si hay error, Blanco si está bien
    private void marcar(JTextField f, boolean error) {
        f.setBackground(error ? Color.PINK : Color.WHITE);
    }

    // Método que calcula el precio Total (Precio del servicio * Cantidad)
    private void calcularTotal() {
        try {
            int servicio = Integer.parseInt(textServicio.getText());
            int cantidad = Integer.parseInt(textCantidad.getText());
            int precio;
            
            // Asigna precio según el número de servicio ingresado
            switch (servicio) {
                case 1: precio = 100; break;
                case 2: precio = 200; break;
                case 3: precio = 75; break;
                case 4: precio = 50; break;
                default: precio = 0;
            }
            // Si el precio es válido, muestra el total
            if (precio == 0) textTotal.setText("");
            else textTotal.setText(String.valueOf(precio * cantidad));
        } catch (Exception ex) {
            // Si hay letras o está vacío, limpia el total
            textTotal.setText("");
        }
    }

    // Método que valida si una fecha es REAL (evita el 30 de febrero, etc.)
    private boolean esFechaLogica(String fecha) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false); // Modo estricto activado
            formato.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // --- BÚSQUEDA INTELIGENTE DE CLIENTE ---
    private void buscarCliente() {
        lblMensaje.setText(""); // Limpiar mensajes anteriores
        marcar(textIdCliente, false);
        marcar(nombre_cliente, false);
        
        String idTxt = textIdCliente.getText().trim();
        String nombreTxt = nombre_cliente.getText().trim();

        if (idTxt.isEmpty() && nombreTxt.isEmpty()) {
            lblMensaje.setForeground(Color.YELLOW);
            lblMensaje.setText("Ingrese un Nombre o un ID para buscar.");
            marcar(textIdCliente, true);
            marcar(nombre_cliente, true);
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            PreparedStatement ps;
            ResultSet rs;

            // ESTRATEGIA 1: BÚSQUEDA POR NOMBRE (Prioridad Alta)
            if (!nombreTxt.isEmpty()) {
                // Busca coincidencias que empiecen con el texto ingresado
                ps = conn.prepareStatement("SELECT * FROM clientes WHERE nombre LIKE ?");
                ps.setString(1, nombreTxt + "%");
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Si encuentra, autocompleta el ID y corrige el nombre
                    textIdCliente.setText(rs.getString("id_cliente")); 
                    nombre_cliente.setText(rs.getString("nombre"));
                    
                    lblMensaje.setForeground(Color.GREEN);
                    lblMensaje.setText("Cliente encontrado por nombre.");
                    return; 
                } else {
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("No se encontró cliente con ese nombre.");
                    marcar(nombre_cliente, true);
                    return;
                }
            }

            // ESTRATEGIA 2: BÚSQUEDA POR ID (Solo si no se escribió nombre)
            if (!idTxt.isEmpty()) {
                if (!idTxt.matches("\\d+")) { // Valida que sea número
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("El ID debe ser numérico.");
                    marcar(textIdCliente, true);
                    return;
                }

                ps = conn.prepareStatement("SELECT * FROM clientes WHERE id_cliente = ?");
                ps.setInt(1, Integer.parseInt(idTxt));
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    nombre_cliente.setText(rs.getString("nombre"));
                    lblMensaje.setForeground(Color.GREEN);
                    lblMensaje.setText("Cliente encontrado por ID.");
                } else {
                    nombre_cliente.setText("");
                    marcar(textIdCliente, true);
                    lblMensaje.setForeground(Color.RED);
                    lblMensaje.setText("Cliente no encontrado por ID.");
                }
            }
        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Error BD: " + ex.getMessage());
        }
    }

    // --- VALIDACIÓN DE TODOS LOS CAMPOS ---
    private boolean validarCampos() {
        boolean ok = true;
        lblMensaje.setText(""); 

        // Valida que el ID sea numérico
        if (!textIdCliente.getText().matches("\\d+")) {
            marcar(textIdCliente, true);
            ok = false;
        } else marcar(textIdCliente, false);

        // Valida que se haya seleccionado un cliente
        if (nombre_cliente.getText().trim().isEmpty()) {
            marcar(nombre_cliente, true);
            lblMensaje.setText("Busque un cliente primero.");
            ok = false;
        } else marcar(nombre_cliente, false);

        // Valida FECHA (Formato DD/MM/AAAA y lógica)
        String fechaInput = textDia.getText().trim();
        // Regex para formato visual
        if (!fechaInput.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$")) {
            marcar(textDia, true);
            if(ok) lblMensaje.setText("Formato fecha incorrecto (DD/MM/AAAA).");
            ok = false;
        } 
        // Validación de existencia en calendario
        else if (!esFechaLogica(fechaInput)) {
            marcar(textDia, true);
            if(ok) lblMensaje.setText("La fecha no existe.");
            ok = false;
        } 
        // Validación de Año >= 2025
        else {
            String[] partes = fechaInput.split("/");
            int anio = Integer.parseInt(partes[2]);
            if (anio < 2025) {
                marcar(textDia, true);
                if(ok) lblMensaje.setText("El año debe ser 2025 o posterior.");
                ok = false;
            } else {
                marcar(textDia, false);
            }
        }

        // Valida Hora (Formato 24h HH:MM)
        if (!textHora.getText().matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
            marcar(textHora, true);
            if(ok) lblMensaje.setText("Formato hora incorrecto (HH:MM).");
            ok = false;
        } else marcar(textHora, false);

        // Valida Servicio (1 al 4)
        if (!textServicio.getText().matches("^[1-4]$")) {
            marcar(textServicio, true);
            ok = false;
        } else marcar(textServicio, false);

        // Valida Cantidad (Mayor a 0)
        if (!textCantidad.getText().matches("^[1-9]\\d*$")) {
            marcar(textCantidad, true);
            ok = false;
        } else marcar(textCantidad, false);

        // Valida Total (No debe estar vacío)
        if (textTotal.getText().trim().isEmpty()) {
            marcar(textTotal, true);
            ok = false;
        } else marcar(textTotal, false);

        // Mensaje genérico si hay errores
        if (!ok && lblMensaje.getText().isEmpty()) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Corrige los campos en rojo.");
        }
        return ok;
    }

    // --- REGISTRAR ORDEN (Controlador) ---
    private void registrarOrden() {
        // Primero validamos todo
        if (!validarCampos()) return; 

        try {
            // Obtenemos los datos limpios de las cajas de texto
            int idCliente = Integer.parseInt(textIdCliente.getText());
            String nombre = nombre_cliente.getText();
            String dia = textDia.getText();
            String hora = textHora.getText();
            String servicio = textServicio.getText();
            int cantidad = Integer.parseInt(textCantidad.getText());
            double total = Double.parseDouble(textTotal.getText());

            // Llamamos al método que guarda en BD
            guardarOrden(idCliente, nombre, dia, hora, servicio, cantidad, total);

            // Cambiamos a la siguiente ventana (Estado del pedido)
            Estado_del_pedido estado = new Estado_del_pedido();
            estado.setVisible(true);
            dispose(); // Cerramos esta ventana

        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Error procesando datos: " + ex.getMessage());
        }
    }

    // --- GUARDAR EN BASE DE DATOS (Modelo) ---
    private void guardarOrden(int idCliente, String nombreCliente, String dia, String hora,
                              String servicio, int cantidad, double total) {
        // Query SQL paramétrico para evitar inyección SQL
        String sql = "INSERT INTO ordenes (id_clientes, nombre_cliente, dia, hora, servicio, cantidad, total, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, 'Pendiente')";
        try {
            Connection conn = ConexionBD.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            // Asignamos valores a los signos de interrogación (?)
            ps.setInt(1, idCliente);
            ps.setString(2, nombreCliente);
            ps.setString(3, dia);
            ps.setString(4, hora);
            ps.setString(5, servicio);
            ps.setInt(6, cantidad);
            ps.setDouble(7, total);
            // Ejecutamos la inserción
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (Exception ex) {
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setText("Error SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // --- MÉTODO PÚBLICO: PARA RECIBIR DATOS DESDE OTRA VENTANA (Historial) ---
    public void cargarDatosDesdeHistorial(String id, String nombre, String dia, String hora, 
                                          String servicio, String cantidad, String total) {
        textIdCliente.setText(id);
        nombre_cliente.setText(nombre);
        textDia.setText(dia);
        textHora.setText(hora);
        textServicio.setText(servicio);
        textCantidad.setText(cantidad);
        textTotal.setText(total);
        lblMensaje.setForeground(Color.GREEN);
        lblMensaje.setText("Datos cargados del historial.");
    }

    // --- CLASE INTERNA PARA CONEXIÓN RÁPIDA ---
    public static class ConexionBD {
        private static final String URL = "jdbc:mysql://localhost:3306/sistema_llantera";
        private static final String USUARIO = "root";
        private static final String CONTRASEÑA = "steven"; 
        
        public static Connection getConexion() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        }
    }
}