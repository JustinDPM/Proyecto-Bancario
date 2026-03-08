package SistemaBanco;
import java.util.Objects;

public class Tarjeta {
    private String nip;
    private String noTarjeta;
    private Cuenta cuenta;

    public Tarjeta(String nip, String noTarjeta, Cuenta cuenta) {
        if (!nip.matches("\\d{4}")) {
            throw new IllegalArgumentException("El nip debe tener 4 digitos");
        }
        this.noTarjeta = noTarjeta;
        this.nip = nip;
        this.cuenta = cuenta;
    }

    public boolean validarNip(String nip) {
        if (!Objects.equals(this.nip, nip)) {
            throw new IllegalArgumentException("NIP incorrecto");
        }
        return true;
    }

    public String getNoTarjeta() {
        return this.noTarjeta;
    }

    public Cuenta getCuenta() {
        return this.cuenta;
    }
}
