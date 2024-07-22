
public class MuestraEnPantalla {
    public static void ImprimeTablero(String[][][] tablero){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;

        for (int i = 0; i < numeroFilas; i++) {
            System.out.print("|");
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k=0; k<2; k++){
                    if (tablero[i][j][k] == null) {
                        tablero[i][j][k] = ".";
                    }
                }
                System.out.print(" "+ tablero[i][j][0] +","+tablero[i][j][1] + " |");
            }
            System.out.println();

        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static void ImprimeEstadoElementos( String[][][][] tablaEnergiaVida){
        int numeroFilas = tablaEnergiaVida.length;
        int numeroColumnas = tablaEnergiaVida[0].length;

        // Imprime estado de celdas
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablaEnergiaVida[i][j][k][0] == "A" || tablaEnergiaVida[i][j][k][0] == "P" ){
                        System.out.print("Posición Row: " + i + " Col: " + j + " Posición: " + k);
                        System.out.print(" Elemento: " + tablaEnergiaVida[i][j][k][0]);
                        System.out.print("   Energía: " + tablaEnergiaVida[i][j][k][1]);
                        System.out.print("   Vida: " + tablaEnergiaVida[i][j][k][2]);
                        System.out.println(" Nace en el Cliclo: "+ tablaEnergiaVida[i][j][k][3]);
                    }
                }
            }
        }
        // Fin de imprime estado de celdas
    }




}
