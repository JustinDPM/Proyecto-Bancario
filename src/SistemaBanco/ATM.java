package SistemaBanco;
import java.util.ArrayList;

public class ATM {
    private static final int[] DENOMINACIONES = {1000, 500, 200, 100, 50, 20};

    public ATM(){}

    public boolean retiro(Tarjeta tarjeta, int monto, String nipIngresado) {
        tarjeta.validarNip(nipIngresado);
        if (monto % 100 != 0) {
            throw new IllegalArgumentException("Solo multiplos de 100");
        }
        return tarjeta.getCuenta().retiro(monto);
    }

    public boolean deposito(Tarjeta tarjeta, int monto, String nipIngresado) {
        tarjeta.validarNip(nipIngresado);
        if (monto % 100 != 0 ) {
            throw new IllegalArgumentException("Debe ser multiplo de 100");
        }
        if (monto < 200) {
            throw new IllegalArgumentException("Debe ser mayor o igual a 200");
        }
        return tarjeta.getCuenta().deposito(monto);
    }

    public boolean pagarServicio(double totalPagar, double totalIngresado) {
        if(totalPagar <= 0){
            throw new IllegalArgumentException("El monto a pagar no puede ser negativo.");
        }
        if(totalPagar>totalIngresado){
            throw new IllegalArgumentException("El monto ingresado es insuficiente");
        }
        return true;        
    }

    public boolean billetesValidos(int billete) {

        for (int denom : DENOMINACIONES) {
            if (billete == denom) {
                return true;
            }
        }

        throw new IllegalArgumentException("Billete no valido");
    }

    public double calcularCambio(double totalIngresado, double totalPagar){
        if (totalIngresado == totalPagar){
            return 0.0;
        }
        double cambio = totalIngresado - totalPagar;
        return cambio;
    }

    public int calcularTotal(ArrayList<Integer> billetes){
        int total = 0;
        for (int n : billetes){
            total += n;
        }
        return total;
    }
}