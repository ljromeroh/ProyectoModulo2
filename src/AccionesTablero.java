
public class AccionesTablero {
    public static int cantidadAnimales(String[][][] tablero){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidad = 0;

        // Imprime estado de celdas
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablero[i][j][k] == "A" ){
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }
    public static int cantidadPlantas(String[][][] tablero){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidad = 0;

        // Imprime estado de celdas
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablero[i][j][k] == "P" ){
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }

    public static int[][] creaListaDeEspaciosDisponibles(String[][][] arrayTablero, int rowElemento, int colElemento){

        int numeroFilasTablero = arrayTablero.length;
        int numeroColumnasTablero = arrayTablero[0].length;
        int elemento = 0;
        int espaciosDisponibles = 0;

        espaciosDisponibles = mueveElemento.EspaciosDisponibles(arrayTablero, rowElemento, colElemento);
        int[][] posicionesDisponibles = new int[espaciosDisponibles][3];

        // Posicion 01
        if (((rowElemento - 1) >= 0) && (arrayTablero[rowElemento - 1][colElemento][0] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento - 1;
            posicionesDisponibles[elemento][1] = colElemento;
            posicionesDisponibles[elemento][2] = 0;
            elemento++;
        }
        if (((rowElemento - 1) >= 0) && (arrayTablero[rowElemento - 1][colElemento][1] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento - 1;
            posicionesDisponibles[elemento][1] = colElemento;
            posicionesDisponibles[elemento][2] = 1;
            elemento++;
        }

        // Posición 02
        if (((colElemento + 1) < numeroColumnasTablero) && (arrayTablero[rowElemento][colElemento + 1][0] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento;
            posicionesDisponibles[elemento][1] = colElemento + 1;
            posicionesDisponibles[elemento][2] = 0;
            elemento++;
        }
        if (((colElemento + 1) < numeroColumnasTablero) && (arrayTablero[rowElemento][colElemento + 1][1] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento;
            posicionesDisponibles[elemento][1] = colElemento + 1;
            posicionesDisponibles[elemento][2] = 1;
            elemento++;
        }

        // Posición 03
        if (((rowElemento + 1) < numeroFilasTablero) && (arrayTablero[rowElemento + 1][colElemento][0] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento + 1;
            posicionesDisponibles[elemento][1] = colElemento;
            posicionesDisponibles[elemento][2] = 0;
            elemento++;
        }
        if (((rowElemento + 1) < numeroFilasTablero) && (arrayTablero[rowElemento + 1][colElemento][1] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento + 1;
            posicionesDisponibles[elemento][1] = colElemento;
            posicionesDisponibles[elemento][2] = 1;
            elemento++;
        }

        // Posición 04
        if (((colElemento - 1) >= 0) && (arrayTablero[rowElemento][colElemento - 1][0] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento;
            posicionesDisponibles[elemento][1] = colElemento - 1;
            posicionesDisponibles[elemento][2] = 0;
            elemento++;
        }
        if (((colElemento - 1) >= 0) && (arrayTablero[rowElemento][colElemento - 1][1] == ".")) {
            posicionesDisponibles[elemento][0] = rowElemento;
            posicionesDisponibles[elemento][1] = colElemento - 1;
            posicionesDisponibles[elemento][2] = 1;
            elemento++;
        }

        return posicionesDisponibles;
    }
}
