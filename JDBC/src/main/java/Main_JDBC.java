import java.sql.*;

public class Main_JDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "bestuser";
        String pass = "bestuser";


        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

          //  statement.execute("UPDATE Courses SET name = 'Веб-разработчик с нуля до PRO'WHERE id = 1");
            // обновляем строку

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {

                Course course = new Course(); // формат по JDBC
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));

                String courseName = resultSet.getString("name");
                System.out.println(courseName);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
