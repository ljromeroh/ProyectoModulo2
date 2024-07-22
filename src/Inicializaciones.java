
public class Inicializaciones {
    public static String eventosMuerte = "";
    public static int   cantidadDeNacimientos = 0;
    public static int   cantidadDeMuertes = 0;

    public static String[][][][] InicializaElementos( String[][][] tablero, String[][][][] tablaEnergiaVida){

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // Inicializa los niveles de vida de los elementos que agregamos manualmente
        // Ubica Animales en el tablero
        // En estado incial los animales tendrán una Energía inicial de **** parametrosDeConfiguracion[5]
        // En estado incial las plantas tendrán una Energia máxima de **** parametrosDeConfiguracion[3]
        // Esta inicialización de Elementos se crea en el cliclo 0

        // tablaEnergiaVida
        // tablaEnergiaVida Posición 0 = Elemento ( [A] Animal , [P] Planta )
        // tablaEnergiaVida Posición 1 = Energía
        // tablaEnergiaVida Posición 2 = Vida
        // tablaEnergiaVida Posición 3 = Ciclo de Nacimiento

        int[] parametrosDeConfiguracion = new int[10];
        parametrosDeConfiguracion= Configuracion.ParametrosDeConfiguracion();

        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablero[i][j][k] == "A") {
                        tablaEnergiaVida[i][j][k][0] = "A";
                        tablaEnergiaVida[i][j][k][1] = String.valueOf(parametrosDeConfiguracion[5]);
                        tablaEnergiaVida[i][j][k][2] = "0"; // Animal inicia con Vida = 0
                        tablaEnergiaVida[i][j][k][3] = "0"; // Ciclo de Nacimiento

                    } else if (tablero[i][j][k] == "P") {
                        tablaEnergiaVida[i][j][k][0] = "P";
                        tablaEnergiaVida[i][j][k][1] = String.valueOf(parametrosDeConfiguracion[3]);
                        tablaEnergiaVida[i][j][k][2] = "."; // Planta no tiene límite de Vida
                        tablaEnergiaVida[i][j][k][3] = "0"; // Ciclo de Aparición de la Planta
                    } else {
                        tablaEnergiaVida[i][j][k][0] = ".";
                        tablaEnergiaVida[i][j][k][1] = ".";
                        tablaEnergiaVida[i][j][k][2] = ".";
                        tablaEnergiaVida[i][j][k][3] = ".";
                    }
                }
            }
        }
        //Fin de inicialización de los elementos
        return tablaEnergiaVida;
    }

}
