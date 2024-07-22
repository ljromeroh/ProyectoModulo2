import java.io.FileWriter;
import java.io.IOException;

public class AutomataCelular {
    public static void main(String[] args) {

        IniciaAutomata();

    }

    public static void IniciaAutomata() {
        final String delimitador = ";";
        String NEXT_LINE = "\n";
        String lineaArchivoCSV = "";

        try {
            FileWriter fw = new FileWriter(Configuracion.nombreDeArchivo, false);

            int[] parametrosDeConfiguracion = new int[10];
            parametrosDeConfiguracion = Configuracion.ParametrosDeConfiguracion();

            int row = parametrosDeConfiguracion[0];
            int col = parametrosDeConfiguracion[1];
            int vidaMeximaAnimales = parametrosDeConfiguracion[2];
            int ciclosDeTiempo = parametrosDeConfiguracion[4];

            //Creando el Tablero
            String[][][] tablero = new String[row][col][2];
            //Creando la Tabla de Energía Vida
            String[][][][] tablaEnergiaVida = new String[row][col][2][4];

            // Agregando Elementos Al Tablero
            tablero = Configuracion.AgregandoElementosIniciales(tablero);

            MuestraEnPantalla.ImprimeTablero(tablero);

            lineaArchivoCSV =   "Tiempo;Animales;Plantas;Nacimientos;Muertes;Eventos";
            fw.append(lineaArchivoCSV).append(NEXT_LINE);
            lineaArchivoCSV = null;

            tablaEnergiaVida = Inicializaciones.InicializaElementos(tablero, tablaEnergiaVida);
            //MuestraEnPantalla.ImprimeEstadoElementos(tablaEnergiaVida);

            lineaArchivoCSV =   "0"+delimitador+AccionesTablero.cantidadAnimales(tablero)+delimitador+
                    AccionesTablero.cantidadPlantas(tablero)+delimitador+"0;0;tablero inicial";
            fw.append(lineaArchivoCSV).append(NEXT_LINE);
            lineaArchivoCSV = null;


            for (int x = 0; x < ciclosDeTiempo; x++) {
                String bitacora = "";
                Inicializaciones.eventosMuerte = "";
                Inicializaciones.cantidadDeNacimientos = 0;
                Inicializaciones.cantidadDeMuertes = 0;
                // Guarda en una lista los elementos Animal a Procesar
                // de esta manera evita procesar dos veces a un animal si se mueve a una celda que aun no se ha procesado.
                int cantidadElementos = ObtieneListaElementosProcesar.ObtieneCantidadDeElementosAProcesar(tablero);
                int[][] listaElementosAProcesar = new int[cantidadElementos][3];
                listaElementosAProcesar = ObtieneListaElementosProcesar.DevuelveListaElementosProcesar(tablero);

                // En este ciclo For se van a procesar los Elementos Animal
                for (int i = 0; i < cantidadElementos; i++) {
                    int rowElemento = listaElementosAProcesar[i][0];
                    int colElemento = listaElementosAProcesar[i][1];
                    int posElemento = listaElementosAProcesar[i][2];

                    if (tablaEnergiaVida[rowElemento][colElemento][posElemento][0] == "A") {
                        int[] pasoParametros = new int[4];
                        pasoParametros[0] = rowElemento;
                        pasoParametros[1] = colElemento;
                        pasoParametros[2] = posElemento;
                        pasoParametros[3] = x; // Ciclo Actual
                        bitacora = MueveAnimal.MueveAnimal(tablero, pasoParametros, tablaEnergiaVida, bitacora);
                    }
                }
                //System.out.println("Antes de Procesar el paso de unidad de tiempo");
                //MuestraEnPantalla.ImprimeEstadoElementos(tablaEnergiaVida);
                tablaEnergiaVida = AccionesTablaEnergiaVida.ReduceVidaPorPasoDeUnidadDeTiempo(tablaEnergiaVida, parametrosDeConfiguracion);

                AccionesTablaEnergiaVida.EliminaElementoDelTableroPorVidaCero(tablero, tablaEnergiaVida);
                //MuestraEnPantalla.ImprimeEstadoElementos(tablaEnergiaVida);
                MuestraEnPantalla.ImprimeTablero(tablero);

                lineaArchivoCSV =   x+delimitador+
                        AccionesTablero.cantidadAnimales(tablero)+delimitador+
                        AccionesTablero.cantidadPlantas(tablero)+delimitador+
                        Inicializaciones.cantidadDeNacimientos+delimitador+
                        Inicializaciones.cantidadDeMuertes+delimitador+
                        bitacora+
                        Inicializaciones.eventosMuerte;
                fw.append(lineaArchivoCSV).append(NEXT_LINE);
                lineaArchivoCSV = null;

            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // Error al crear el archivo, por ejemplo, el archivo
            // está actualmente abierto.
            e.printStackTrace();
        }
    }

}
