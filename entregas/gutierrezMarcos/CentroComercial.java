public class CentroComercial {
    public static void main(String[] args) {
        final int MINUTOS_JORNADA = 720;
        final int NUM_CAJAS = 4;

        int cola = 0;
        boolean[] cajaOcupada = new boolean[NUM_CAJAS];
        int[] itemsClienteCaja = new int[NUM_CAJAS];

        for (int minuto = 1; minuto <= MINUTOS_JORNADA; minuto++) {
            cleanScreen();

            for (int i = 0; i < NUM_CAJAS; i++) {
                cajaOcupada[i] = cajaEstado(itemsClienteCaja[i]);
            }

            if (llegaCliente()) {
                cola++;
                System.out.println("Ha llegado un cliente!");
            }

            System.out.println("Hay una cola de: " + cola);

            for (int i = 0; i < NUM_CAJAS; i++) {
                if (cajaOcupada[i] && itemsClienteCaja[i] > 0) {
                    itemsClienteCaja[i]--;
                    System.out.println("El cliente de la caja " + (i + 1) + " tiene un total de: " + itemsClienteCaja[i]);
                }

                if (cola >= 1 && !cajaOcupada[i]) {
                    System.out.println("Ha pasado a la caja " + (i + 1) + " un cliente");
                    cola--;
                    cajaOcupada[i] = true;
                    itemsClienteCaja[i] = items();
                    System.out.println("El cliente tiene un total de: " + itemsClienteCaja[i]);
                }
            }

            pause(2);
        }
    }

    static boolean cajaEstado(int items) {
        return items > 0;
    }

    static int items() {
        return (int) (Math.random() * 11) + 5;
    }

    static boolean llegaCliente() {
        return Math.random() <= 0.6;
    }

    static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pause(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
