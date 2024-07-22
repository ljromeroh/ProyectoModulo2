
public class mueveElemento {
    public static int EspaciosDisponibles(String[][][] tablero, int rowElemento, int colElemento ){
        int numeroFilasTablero = tablero.length;
        int numeroColumnasTablero = tablero[0].length;
        int espaciosDisponibles = 0;

        // Posicion 01
        if (((rowElemento - 1) >= 0) && (tablero[rowElemento-1][colElemento][0] == ".") )
        { espaciosDisponibles++;}
        if (((rowElemento - 1) >= 0) && (tablero[rowElemento-1][colElemento][1] == ".") )
        { espaciosDisponibles++;}

        // Posición 02
        if (((colElemento + 1) < numeroColumnasTablero) && (tablero[rowElemento][colElemento+1][0] == ".") )
        { espaciosDisponibles++;}
        if (((colElemento + 1) < numeroColumnasTablero) && (tablero[rowElemento][colElemento+1][1] == ".") )
        { espaciosDisponibles++;}

        // Posición 03
        if (((rowElemento + 1) < numeroFilasTablero) && (tablero[rowElemento+1][colElemento][0] == ".") )
        { espaciosDisponibles++;}
        if (((rowElemento + 1) < numeroFilasTablero) && (tablero[rowElemento+1][colElemento][1] == ".") )
        { espaciosDisponibles++;}

        // Posición 04
        if (((colElemento - 1) >= 0) && (tablero[rowElemento][colElemento-1][0] == ".") )
        { espaciosDisponibles++;}
        if (((colElemento - 1) >= 0) && (tablero[rowElemento][colElemento-1][1] == ".") )
        { espaciosDisponibles++;}

        return espaciosDisponibles;
    }


}
