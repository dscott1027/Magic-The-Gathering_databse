import java.sql.DriverManager;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.SQLException;
import java.sql.Statement;
class sqlMethods{
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/mtgdatabase?useTimezone=true&serverTimezone=EST";
        String username = "root";
        String password = "DinosaurMan911";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}