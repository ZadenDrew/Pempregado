package pempregado;

/**
 *
 * @author oracle
 */
public class Pempregado {

    public static void main(String[] args) {

        BaseDatos bd = new BaseDatos();
        bd.connect();
        bd.listaEmpregados();
        //bd.insireEmpregado();
        bd.amosarDatosEmpregado();
        bd.actualizaSalario(2,100);
        bd.mostrarXid(3);
        bd.disconnect();
    }

}
