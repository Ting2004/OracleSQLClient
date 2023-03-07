import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "scott",
                "tiger"
        );




        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("SQL>");
            String sql = scanner.nextLine();
            if (sql.equals("exit"))
                break;

            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnLabel(i) + "   ");
                }
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        Object object = rs.getObject(i);
                        if (object == null)
                            System.out.print("null   ");
                        else
                            System.out.print(object + "   ");
                    }
                    System.out.println();
                }
                rs.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        conn.close();
    }

}
