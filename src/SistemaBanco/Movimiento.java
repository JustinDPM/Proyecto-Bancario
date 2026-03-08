
package SistemaBanco;

import java.time.LocalDateTime;

public class Movimiento {
    private TipoMovimiento tipo;
    private double monto;
    private LocalDateTime fecha;

    public Movimiento(TipoMovimiento tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public String toString() {
        return "tipo: " + tipo + " | monto: $" + monto + " | fecha: " + fecha;
    }
}
