package sistema_llantera_ceja1;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Estado_del_pedido extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Estado_del_pedido frame = new Estado_del_pedido();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Estado_del_pedido() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 683, 620);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEstadoDelServicio = new JLabel("Estado del Servicio");
        lblEstadoDelServicio.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstadoDelServicio.setForeground(new Color(255, 255, 255));
        lblEstadoDelServicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lblEstadoDelServicio.setBounds(9, 11, 660, 58);
        contentPane.add(lblEstadoDelServicio);

        JButton btnNewButton = new JButton("Atras");
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        btnNewButton.setBounds(9, 11, 101, 50);
        btnNewButton.addActionListener(e -> {
            menu menuPrincipal = new menu();
            menuPrincipal.setVisible(true);
            dispose();
        });
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("\r\nAsignar un estado del pedido correspondiente.");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        lblNewLabel.setBounds(0, 80, 659, 58);
        contentPane.add(lblNewLabel);
        
        JLabel lblAsignaElEstado = new JLabel("Se asignara el estado del servicio mediante el numero de la opcion");
        lblAsignaElEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsignaElEstado.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        lblAsignaElEstado.setBounds(0, 121, 669, 58);
        contentPane.add(lblAsignaElEstado);
        
        JLabel lblAsignarUnEstado = new JLabel("1).Servicio en proceso \r\n");
        lblAsignarUnEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblAsignarUnEstado.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        lblAsignarUnEstado.setBounds(0, 190, 247, 58);
        contentPane.add(lblAsignarUnEstado);
        
        JLabel lblServicioTerminado = new JLabel("2).Servicio terminado ");
        lblServicioTerminado.setHorizontalAlignment(SwingConstants.CENTER);
        lblServicioTerminado.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        lblServicioTerminado.setBounds(0, 239, 237, 58);
        contentPane.add(lblServicioTerminado);
        
        JLabel lblGuardarServicio = new JLabel("3).Guardar servicio \r\n");
        lblGuardarServicio.setHorizontalAlignment(SwingConstants.CENTER);
        lblGuardarServicio.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        lblGuardarServicio.setBounds(10, 285, 197, 58);
        contentPane.add(lblGuardarServicio);
        
        JButton btnNewButton_1 = new JButton("Cobrar ");
        btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        btnNewButton_1.setBounds(276, 311, 165, 32);
        contentPane.add(btnNewButton_1);
        
        textField = new JTextField();
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        textField.setBounds(276, 216, 165, 32);
        contentPane.add(textField);
        textField.setColumns(10);
        
        // Agregar filtro de documento para validar entrada (solo 1, 2 o 3, y máximo un carácter)
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[123]") && fb.getDocument().getLength() == 0) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[123]") && fb.getDocument().getLength() - length == 0) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        JButton btnNewButton_1_1 = new JButton("Enviar\r\n");
        btnNewButton_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
        btnNewButton_1_1.setBounds(276, 265, 165, 32);
        btnNewButton_1_1.addActionListener(e -> {
            String estado = textField.getText();
            if (estado.matches("[123]")) {
                // Aquí va el código para guardar en la base de datos
                // Ejemplo genérico: Asumiendo una base de datos SQLite y una tabla "pedidos" con columnas "id" y "estado"
                // Ajusta la URL de la DB, la consulta SQL y los parámetros según tu configuración real
                try {
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:tu_base_de_datos.db"); // Cambia esto por tu URL de DB
                    PreparedStatement stmt = conn.prepareStatement("UPDATE pedidos SET estado = ? WHERE id = ?");
                    stmt.setInt(1, Integer.parseInt(estado));
                    stmt.setInt(2, 1); // Ejemplo: ID del pedido, cámbialo según sea necesario
                    stmt.executeUpdate();
                    conn.close();
                    JOptionPane.showMessageDialog(this, "Estado guardado exitosamente.");
                    textField.setText(""); // Limpiar el campo después de guardar
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al guardar el estado.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese solo 1, 2 o 3.");
            }
        });
        contentPane.add(btnNewButton_1_1);
    }
	 private void guardarClientesEnBD(String estado_actual) {
	        String url = "jdbc:mysql://localhost:3306/proyecto";
	        String usuario = "root";
	        String contraseña = "steven";
	        String sql = "INSERT INTO registro_cliente (estado_actual) VALUES (?)";

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
	            PreparedStatement ps = conexion.prepareStatement(sql);
	            ps.setString(1, estado_actual);
	            ps.executeUpdate();
	            ps.close();
	            conexion.close();
	            JOptionPane.showMessageDialog(this, "cliente guardado correctamente.");
	        } catch (ClassNotFoundException | SQLException e) {
	            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
	        }
	    }

}
