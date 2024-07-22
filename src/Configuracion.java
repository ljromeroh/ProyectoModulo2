
public class Configuracion {
    public static String nombreDeArchivo = "archivo.csv";


    public static int[] ParametrosDeConfiguracion(){
        int[] parametrosDeConfiguracion = new int[10];

        parametrosDeConfiguracion[0] = 5; // Renglones del Tablero
        parametrosDeConfiguracion[1] = 5; // Columnas del Tablero
        parametrosDeConfiguracion[2] = 5; // Vida Máxima de los Animales
        parametrosDeConfiguracion[3] = 4; // Energía tope de las plantas
        parametrosDeConfiguracion[4] = 16; // Ciclos de Tiempo a Ejecutar
        parametrosDeConfiguracion[5] = 5; // Energía Inicial de los Animales

        return parametrosDeConfiguracion;
    }

    public static String[][][] AgregandoElementosIniciales(String[][][] tablero){
        // Agregando Elementos Al Tablero
        tablero[2][2][0] = "P";
        tablero[3][3][0] = "P";
        tablero[3][1][0] = "A";
        tablero[3][1][1] = "A";
        tablero[2][0][0] = "A";
        tablero[2][0][1] = "A";
        tablero[2][1][1] = "A";
        tablero[1][1][0] = "A";
        tablero[1][1][1] = "A";
        tablero[4][3][1] = "A";
        return tablero;
    }

}
