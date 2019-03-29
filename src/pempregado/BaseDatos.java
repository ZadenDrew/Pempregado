package pempregado;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

    public String driver = "jdbc:oracle:thin:";
    public String host = "localhost.localdomain";
    public String porto = "1521";
    public String sid = "orcl";
    public String usuario = "hr";
    public String password = "hr";
    public String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager            
    public Connection conn;
    public PreparedStatement st, stm;

    public BaseDatos() {
    }

    public void connect() {
        try {

            conn = DriverManager.getConnection(url);
            // conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conectado.");
            }
        } catch (SQLException exsq) {
            System.out.println("Error: " + exsq);

        }

    }

    public void disconnect() {
        try {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            System.out.println("Error: aaaaaaaa" + ex);
        }
    }

    public void listaEmpregados() {
        try {
            // connect();
            ResultSet result;

            st = conn.prepareStatement("SELECT id,emp,salario FROM empregadosbdor");
            result = st.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int salario = result.getInt("salario");
                java.sql.Struct emp = (java.sql.Struct) result.getObject(2);
                Object[] empregado = emp.getAttributes();
                String nome = (String) empregado[0];
                BigDecimal edade = (BigDecimal) empregado[1];
                int edad = edade.intValue();
                System.out.println("ID :\t" + id + "\tNome:\t" + nome + "  \t" + edad + "\t" + salario);
            }
            System.out.println("La tabla se muestra con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }

    public void insireEmpregado() {

        try {

            st = conn.prepareStatement("insert into empregadosbdor values (?,TIPO_EMP(?,?),?)");
            st.setInt(1, 4);
            st.setString(2, "juan");
            st.setInt(3, 250);
            st.setInt(4, 1600);

            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }

    }

    public void actualizaSalario(Integer id, Integer mas) {

        int salariof = 0;
        int salfinal = 0;
        try {

            ResultSet result;

            stm = conn.prepareStatement("SELECT salario FROM empregadosbdor where id =" + id);
            result = stm.executeQuery();
            while (result.next()) {
                salariof = result.getInt("salario");
                salfinal = (salariof + mas);

                st = conn.prepareStatement("UPDATE empregadosbdor set salario = '" + salfinal + "' where id='" + id + "'");
                st.executeUpdate();
            }

            System.out.println("La fila ha sido modificada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }

    public void mostrarXid(Integer id) {

        try {

            ResultSet result;

            stm = conn.prepareStatement("SELECT emp,salario FROM empregadosbdor where id =" + id);
            result = stm.executeQuery();
            while (result.next()) {

                int salario = result.getInt("salario");
                java.sql.Struct emp = (java.sql.Struct) result.getObject(1);
                Object[] empregado = emp.getAttributes();
                String nome = (String) empregado[0];
                BigDecimal edade = (BigDecimal) empregado[1];
                int edad = edade.intValue();
                System.out.println("ID :\t" + id + "\tNome:\t" + nome + "  \t" + edad + "\t" + salario);
            }

            System.out.println("La fila ha sido mostrada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

    }

    public void amosarDatosEmpregado() {
        ResultSet result = null;

        try {
            PreparedStatement st = conn.prepareStatement("SELECT id,emp,salario FROM empregadosbdor where salario=1800");
            result = st.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int salario = result.getInt("salario");
                java.sql.Struct emp = (java.sql.Struct) result.getObject(2);

                Object[] empregado = emp.getAttributes();
                String nome = (String) empregado[0];
                BigDecimal edade = (BigDecimal) empregado[1];
                int num = edade.intValue();

                System.out.println("Id: " + id + " Nome: " + nome + " Edade: " + edade + " Salario: " + salario);
            }

        } catch (SQLException ex) {
            System.out.println("erro " + ex);
        }
    }

    public void amosarEmpleadoId() {
        ResultSet result = null;

        try {

            PreparedStatement st = conn.prepareStatement("SELECT id,emp,salario FROM empregadosbdor where id=3");

            result = st.executeQuery();

            while (result.next()) {

                int id = result.getInt("id");

                int salario = result.getInt("salario");

                java.sql.Struct emp = (java.sql.Struct) result.getObject(2);

                Object[] empregado = emp.getAttributes();

                String nome = (String) empregado[0];

                BigDecimal edade = (BigDecimal) empregado[1];

                int num = edade.intValue();

                System.out.println("Id: " + id + " Nome: " + nome + " Edade: " + edade + " Salario: " + salario);

            }

        } catch (SQLException ex) {

            System.out.println("erro " + ex);

        }

    }

}
