package sistema_llantera_ceja1;

// --- IMPORTACIONES SQL ---
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase: Conexion
 * Función: Gestionar la conexión con la base de datos MySQL.
 * Patrón: Utiliza un enfoque estático (Singleton) para reutilizar la misma conexión
 * en lugar de abrir una nueva cada vez que se hace una consulta.
 */
public class conexion {

    // --- CONFIGURACIÓN DE ACCESO (CREDENCIALES) ---
    // URL: Dirección donde vive la base de datos.
    // IMPORTANTE: Verifica que "proyecto" sea el nombre real de tu BD. 
    // En códigos anteriores usabas "sistema_llantera". Si falla, cambia esto.
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto"; 
    
    // Usuario por defecto de XAMPP/WAMP suele ser "root"
    private static final String USER = "root";
    
    // Tu contraseña configurada en MySQL
    private static final String PASSWORD = "steven";

    // Variable estática que guardará la conexión activa para toda la app
    private static Connection conexion = null;

    // --- MÉTODO PARA OBTENER LA CONEXIÓN (OPEN) ---
    public static Connection getConexion() {
        try {
            // Verificamos si NO hay conexión o si la anterior se cerró
            if (conexion == null || conexion.isClosed()) {
                
                // 1. Cargar el Driver: Es el "traductor" entre Java y MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // 2. Establecer conexión: Usamos las credenciales de arriba
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                
                System.out.println("Conexión establecida con éxito.");
            }
        } catch (ClassNotFoundException e) {
            // Error: No se agregó la librería mysql-connector-java al proyecto
            System.out.println("Error: No se encontró el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            // Error: Base de datos apagada, nombre incorrecto o contraseña mal
            System.out.println("Error de conexión SQL: " + e.getMessage());
        }
        // Retornamos el objeto conexión para que las otras clases lo usen
        return conexion;
    }

    // --- MÉTODO PARA CERRAR LA CONEXIÓN (CLOSE) ---
    // Es buena práctica cerrar la conexión al salir de la app para liberar memoria
    public static void cerrarConexion() {
        try {
            // Solo cerramos si existe y está abierta
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}