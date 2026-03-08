//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package SistemaBanco;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private int noCuenta;
    private static int contadorId = 0;
    private String nombre;
    private double saldo;
    private List<Movimiento> movimientos;

    public Cuenta(String nombre, double saldo) {
        this.noCuenta = ++contadorId;
        this.nombre = nombre;
        this.saldo = saldo;
        this.movimientos = new ArrayList();
    }

    public int getNoCuenta() {
        return this.noCuenta;
    }
    public double getSaldo() {
        return this.saldo;
    }
    public String getNombre() {
        return this.nombre;
    }
    public List<Movimiento> getMovimientos() {
        return this.movimientos;
    }

    public void registrarMovimiento(Movimiento movimiento) {
        this.movimientos.add(movimiento);
    }

    public boolean retiro(double monto) {
        if (!(monto > this.saldo) && !(monto <= (double)0.0F)) {
            this.saldo -= monto;
            this.registrarMovimiento(new Movimiento(TipoMovimiento.RETIRO, monto));
            return true;
        } else {
            throw new IllegalArgumentException("Monto invalido");
        }
    }

    public boolean deposito(double monto) {
        if (monto <= (double)0.0F) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        } else {
            this.saldo += monto;
            this.registrarMovimiento(new Movimiento(TipoMovimiento.DEPOSITO, monto));
            return true;
        }
    }

    public boolean pagarServicio(double monto) {
        if(monto <= (double)0.0F){
            throw new IllegalArgumentException("El monto no puede ser negativo");
        } else if(monto > this.saldo){
            throw new IllegalArgumentException("Saldo insuficiente");
        }else {
           this.saldo -= monto;
            return true;
        }
    }

}
