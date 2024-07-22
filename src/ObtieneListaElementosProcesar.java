/**
 * Created by: javierromero
 * Project Name: ProyectoModulo2
 */
public class ObtieneListaElementosProcesar {
    public static int[][] DevuelveListaElementosProcesar (String[][][] tablero ) {
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidadElementos = ObtieneListaElementosProcesar.ObtieneCantidadDeElementosAProcesar(tablero);

        int[][] elementosAProcesar = new int[cantidadElementos][3];
        int contadorElementos = 0;
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablero[i][j][k] == "A") {
                        elementosAProcesar[contadorElementos][0] = i;
                        elementosAProcesar[contadorElementos][1] = j;
                        elementosAProcesar[contadorElementos][2] = k;
                        contadorElementos++;
                    }
                }
            }
        }
        return elementosAProcesar;
    }

    public static int ObtieneCantidadDeElementosAProcesar(String[][][] tablero ){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidadElementos = 0;

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablero[i][j][k] == "A") {
                        cantidadElementos++;
                    }
                }
            }
        }
        return cantidadElementos;
    }
}
