package SistemaBanco;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Cuenta cuenta = new Cuenta("Justin Perez", 5000);
        Tarjeta tarjeta = new Tarjeta("1234", "1111222233334444", cuenta);
        ATM atm = new ATM();

        int opcion1;

        do {

            System.out.println("\n----- CAJERO AUTOMATICO -----");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Pagar servicio");
            System.out.println("5. Ver movimientos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion1 = sc.nextInt();
            sc.nextLine();

            try{
                switch (opcion1) {

                    case 1:
                        System.out.println("Saldo actual: $" + cuenta.getSaldo());
                        break;

                    case 2:
                        System.out.print("Ingrese NIP: ");
                        String nipDep = sc.nextLine();
                        tarjeta.validarNip(nipDep);
                        System.out.print("Monto a depositar: ");
                        int montoDep = sc.nextInt();

                        if (atm.deposito(tarjeta, montoDep, nipDep)) {
                            System.out.println("Deposito realizado");
                        } else {
                            System.out.println("Error en el deposito");
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese NIP: ");
                        String nipRet = sc.next();
                        tarjeta.validarNip(nipRet);
                        System.out.print("Monto a retirar: ");
                        int montoRet = sc.nextInt();

                        if (atm.retiro(tarjeta, montoRet, nipRet)) {
                            System.out.println("Retiro realizado");
                        } else {
                            System.out.println("Error en el retiro");
                        }
                        break;

                    case 4:
                        System.out.println("---PAGO DE SERVICIO---");

                        System.out.println("1. Pago con cuenta");
                        System.out.println("2. Pago con cajero");
                        System.out.println("Ingrese una opcion: ");
                        int opcion2 = sc.nextInt();

                        if(opcion2 == 1){
                            System.out.println("Ingrese  NIP: ");
                            String nipSer =  sc.next();
                            tarjeta.validarNip(nipSer);

                            System.out.print("Nombre del servicio: ");
                            String servicioCuenta = sc.next();

                            System.out.print("Monto del servicio: $");
                            double montoServicioCuenta = sc.nextDouble();

                            if(cuenta.pagarServicio(montoServicioCuenta)){
                                System.out.println("\n---COMPROBANTE DE PAGO ");
                                System.out.println("Servicio: " + servicioCuenta);
                                System.out.println("Monto pagado: $" + montoServicioCuenta);
                                System.out.println("Pago realizado exitosamente");
                            }
                        }

                        if(opcion2 == 2){

                            try {
                                System.out.print("Nombre del servicio: ");
                                String servicio = sc.next();

                                System.out.print("Monto del servicio: $");
                                double montoServicio = sc.nextDouble();

                                System.out.println("\nIngrese los billetes para pagar");
                                System.out.println("Ingrese 0 para terminar:");

                                ArrayList<Integer> billetesIngresados = new ArrayList<>();
                                int total = 0;

                                while (true) {
                                    System.out.print("Billete: $");
                                    int billete = sc.nextInt();

                                    if(billete == 0){
                                        break;
                                    }

                                    if(atm.billetesValidos(billete)){
                                        billetesIngresados.add(billete);
                                    }

                                    if (atm.calcularTotal(billetesIngresados) >= montoServicio) {
                                        total = atm.calcularTotal(billetesIngresados);
                                        break;
                                    }
                                }
                                sc.nextLine();

                                System.out.println("Total ingresado: $" + total);

                                if (atm.pagarServicio(montoServicio, total)) {
                                    double cambio = atm.calcularCambio(total, montoServicio);

                                    System.out.println("\n---COMPROBANTE DE PAGO ");
                                    System.out.println("Servicio: " + servicio);
                                    System.out.println("Monto pagado: $" + montoServicio);
                                    System.out.println("Total ingresado: $" + total);

                                    if (cambio > 0) {
                                        System.out.println("Cambio a devolver: $" + cambio);
                                        System.out.println("¡No olvide su cambio!");
                                    } else {
                                        System.out.println("Pago exacto - No hay cambio");
                                    }

                                    System.out.println("Pago realizado exitosamente");
                                }

                            } catch (IllegalArgumentException e) {
                                System.out.println("Error en el pago: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error inesperado: " + e.getMessage());
                                sc.nextLine();
                            }
                        }
                        break;

                    case 5:

                        System.out.println("\n----- MOVIMIENTOS -----");

                        for (Movimiento m : cuenta.getMovimientos()) {
                            System.out.println(m);
                        }

                        break;

                    case 6:

                        System.out.println("Gracias por usar el cajero");

                        break;

                    default:

                        System.out.println("Opcion invalida");

                }
            } catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }


        } while (opcion1 != 6);

        sc.close();
    }
}