import java.sql.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        // Parámetros de conexión a la base de datos
        String url = "jdbc:postgresql://localhost:5432/test1_xml_json";
        String user = "postgres";
        String password = "clave123";

        // Consulta SQL para obtener los datos en formato JSON
        String sql = "SELECT obtener_mascotas_json()";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Procesar el resultado JSON
            if (rs.next()) {
                String jsonResult = rs.getString(1);

                // Guardar el JSON en un archivo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("mascotas.json"))) {
                    writer.write(jsonResult);
                    System.out.println("JSON guardado en mascotas.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}