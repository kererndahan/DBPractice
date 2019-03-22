import java.sql.*;

public class DBActions {
    private static Connection con;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        connectToDB();
        //update ("aa","33","cc1");
        delete ("aa");
        select();
        con.close();
    }

    private static void connectToDB() throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver"); // not MANDATORT IN NEW MY SQL SERVERS
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC", "root", "XMP0512ie");
    }

    public static void insert(String name, String age, String breed) throws SQLException, ClassNotFoundException {
        //connectToDB();
        String statementToExecute = "";
        statementToExecute = "INSERT INTO finelproj.dogs(name,age,breed)VALUES (?,?,?);";
        PreparedStatement insert = con.prepareStatement(statementToExecute);
        insert.setString(1,name);
        insert.setString(2,age);
        insert.setString(3,breed);
        insert.executeUpdate();
        insert.close();
    }

    public static void update(String name, String age, String breed) throws SQLException, ClassNotFoundException {
        //connectToDB();
        String statementToExecute = "";
        statementToExecute = "UPDATE finelproj.dogs SET name =?,age=?,breed=? WHERE name = ?;";
        PreparedStatement update = con.prepareStatement(statementToExecute);
        update.setString(1,name);
        update.setString(2,age);
        update.setString(3,breed);
        update.setString(4,name);
        update.executeUpdate();
        update.close();
    }
    public static void delete(String name) throws SQLException, ClassNotFoundException {
        //connectToDB();
        String statementToExecute = "";
        statementToExecute = "DELETE FROM finelproj.dogs WHERE name =?;";
        PreparedStatement delete = con.prepareStatement(statementToExecute);
        delete.setString(1,name);
        delete.executeUpdate();
        delete.close();
    }

    private static void select() throws SQLException {
        //Execute a query
        String statementToExecute = "";
        Statement stmt = con.createStatement();
        statementToExecute = "SELECT * FROM finelproj.dogs;";
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while (rs.next()) {
            //Retrieve by column name
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String breed = rs.getString("breed");
            System.out.println("Name: " + name);
            System.out.println(", Age: " + age);
            System.out.println(", breed: " + breed);
        }
        rs.close();
        stmt.close();
    }
}
