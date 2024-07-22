
public class AccionesTablaEnergiaVida {
    public static String[][][][] ReduceEnergiaEnTablaDeEnergiaVidasPorReproduccion(String[][][][] tablaEnergiaVida, int[] coordenadasElementoAReducirEnergia){
        // Reducimos Energía Elemento en la tabla de EnergíaVida a los elementos Animal que comparten posicion

        // tablaEnergiaVida [][][][X] Posición 0 = Elemento ( [A] Animal , [P] Planta )
        // tablaEnergiaVida [][][][X] Posición 1 = Energía
        // tablaEnergiaVida [][][][X] Posición 2 = Vida
        // tablaEnergiaVida [][][][X] Posición 3 = Ciclo de Nacimiento

        // coordenadasElementoAReducirEnergia [0]   Posicion Row
        int row = coordenadasElementoAReducirEnergia[0];
        // coordenadasElementoAReducirEnergia [1]   Posicion Col
        int col = coordenadasElementoAReducirEnergia[1];
        // coordenadasElementoAReducirEnergia [2]   Posicion Elemento en Celda (1 o 2)
        int posicion = coordenadasElementoAReducirEnergia[2];

        // Reducir Energía
        int valorEnergia = Integer.parseInt(tablaEnergiaVida[row][col][0][1]) - 1;
        tablaEnergiaVida[row][col][0][1] = String.valueOf(valorEnergia);
        if (valorEnergia == 0) {
            tablaEnergiaVida[row][col][0][0] = ".";
            tablaEnergiaVida[row][col][0][1] = ".";
            tablaEnergiaVida[row][col][0][2] = ".";
            tablaEnergiaVida[row][col][0][3] = ".";
        }
        valorEnergia = Integer.parseInt(tablaEnergiaVida[row][col][1][1]) - 1;
        tablaEnergiaVida[row][col][1][1] = String.valueOf(valorEnergia);
        if (valorEnergia == 0) {
            //System.out.println("Elemento Row: "+row + " Col: "+ col + " Posición: "+ 1 +" debe Morir por no tener Energía");
            tablaEnergiaVida[row][col][1][0] = ".";
            tablaEnergiaVida[row][col][1][1] = ".";
            tablaEnergiaVida[row][col][1][2] = ".";
            tablaEnergiaVida[row][col][1][2] = ".";
        }
        return tablaEnergiaVida;
    }

    public static String[][][][] ReduceEnergiaEnTablaDeEnergiaVidas(String[][][][] tablaEnergiaVida,
                                                                    int[] coordenadasElementoAReducirEnergia){
        // Reducimos Energía Elemento en la tabla de EnergíaVida

        // tablaEnergiaVida [][][][X] Posición 0 = Elemento ( [A] Animal , [P] Planta )
        // tablaEnergiaVida [][][][X] Posición 1 = Energía
        // tablaEnergiaVida [][][][X] Posición 2 = Vida

        // coordenadasElementoAReducirEnergia [0]   Posicion Row
        int row = coordenadasElementoAReducirEnergia[0];
        // coordenadasElementoAReducirEnergia [1]   Posicion Col
        int col = coordenadasElementoAReducirEnergia[1];
        // coordenadasElementoAReducirEnergia [2]   Posicion Elemento en Celda (1 o 2)
        int posicion = coordenadasElementoAReducirEnergia[2];


        // Reducir Energía
        int valorEnergia = Integer.parseInt(tablaEnergiaVida[row][col][posicion][1]) - 1;
        tablaEnergiaVida[row][col][posicion][1]= String.valueOf(valorEnergia);
        if (valorEnergia == 0){
            tablaEnergiaVida[row][col][posicion][0]=".";
            tablaEnergiaVida[row][col][posicion][1]=".";
            tablaEnergiaVida[row][col][posicion][2]=".";
            tablaEnergiaVida[row][col][posicion][3]=".";
        }

        return tablaEnergiaVida;
    }

    public static int[] PosicionNuevoAnimal(String[][][] tablero, int[] posicionMadre){
        int rowMadre =  posicionMadre[0];
        int colMadre =  posicionMadre[1];
        int espaciosDisponibles = 0;

        espaciosDisponibles = mueveElemento.EspaciosDisponibles(tablero, rowMadre, colMadre);

        int[][] posicionesDisponibles = new int[espaciosDisponibles][3];
        posicionesDisponibles = AccionesTablero.creaListaDeEspaciosDisponibles(tablero, rowMadre, colMadre);

        int nuevaPosicionAleatoriaAsignada = (int) ((Math.random() * espaciosDisponibles));

        int[] nuevaPosicion = new int[3];
        nuevaPosicion[0] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][0];
        nuevaPosicion[1] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][1];
        nuevaPosicion[2] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][2];

        return nuevaPosicion;

    }

    public static String[][][][] InicializaNuevoAnimal(String[][][][] tablaEnergiaVida, int[] nuevaPosicion, int cicloActual){
        int[] parametrosDeConfiguracion = new int[10];
        parametrosDeConfiguracion= Configuracion.ParametrosDeConfiguracion();

        tablaEnergiaVida[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]][0] = "A";
        tablaEnergiaVida[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]][1] = String.valueOf(parametrosDeConfiguracion[5]);
        tablaEnergiaVida[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]][2] = "0";
        tablaEnergiaVida[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]][3] = String.valueOf(cicloActual);
        return tablaEnergiaVida;
    }

    public static int CuantaEnergiaTieneAnimal(String[][][][] tablaEnergiaVida, int[] posicionAnimal){
        int energia = 0;
        energia = Integer.parseInt(tablaEnergiaVida[posicionAnimal[0]][posicionAnimal[1]][posicionAnimal[2]][1]);
        return energia;
    }

    public static String[][][] AgregaNuevoAnimal(String[][][] tablero, int[] nuevaPosicion){
        tablero[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]] = "A";
        return tablero;
    }

    public static String[][][] EliminaElementoDelTableroPorEnergiaCero(String[][][] tablero, String[][][][] tablaEnergiaVida){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidadAnimalesEliminados = 0;
        int cantidadPlantasEliminadas = 0;

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k=0; k<2; k++){
                    if (tablero[i][j][k] == "A" & tablaEnergiaVida[i][j][k][0] == "."){
                        //System.out.println("Tablero Row: "+ i + " Col: "+j + " Pos: "+k + " Valor: "+ tablero[i][j][k]);
                        //System.out.println("Tabla Energía Vida Row: "+ i + " Col: "+j + " Pos: "+k + " Valor Pos 0: "+tablaEnergiaVida[i][j][k][0]);
                        Inicializaciones.eventosMuerte = Inicializaciones.eventosMuerte + "Muere Animal en ["+ i + ","+j+","+k+"] / ";
                        Inicializaciones.cantidadDeMuertes = Inicializaciones.cantidadDeMuertes++;
                        tablero[i][j][k] = ".";
                        cantidadAnimalesEliminados++;
                    } else if (tablero[i][j][k] == "P" & tablaEnergiaVida[i][j][k][0] == ".") {
                        //System.out.println("Tablero Row: "+ i + " Col: "+j + " Pos: "+k + " Valor: "+ tablero[i][j][k]);
                        //System.out.println("Tabla Energía Vida Row: "+ i + " Col: "+j + " Pos: "+k + " Valor Pos 0: "+tablaEnergiaVida[i][j][k][0]);
                        Inicializaciones.eventosMuerte = Inicializaciones.eventosMuerte + "Muere Planta en ["+ i + ","+j+","+k+"] / ";
                        tablero[i][j][k] = ".";
                        cantidadPlantasEliminadas++;
                    }
                }
            }
        }
        //Configuracion.cantidadDeMuertes = cantidadAnimalesEliminados;
        //System.out.println("Se Eliminaron : "+ cantidadAnimalesEliminados +" Animales y "+cantidadPlantasEliminadas +" Plantas");
        return tablero;
    }

    public static String[][][] EliminaElementoDelTableroPorVidaCero(String[][][] tablero, String[][][][] tablaEnergiaVida){
        int numeroFilas = tablero.length;
        int numeroColumnas = tablero[0].length;
        int cantidadEliminados = 0;

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k=0; k<2; k++){
                    if (tablero[i][j][k] == "A" & tablaEnergiaVida[i][j][k][0] == "."){
                        //System.out.println("Tablero Row: "+ i + " Col: "+j + " Pos: "+k + " Valor: "+ tablero[i][j][k]);
                        //System.out.println("Tabla Energía Vida Row: "+ i + " Col: "+j + " Pos: "+k + " Valor Pos 0: "+tablaEnergiaVida[i][j][k][0]);
                        Inicializaciones.eventosMuerte = Inicializaciones.eventosMuerte + "Muere Animal en ["+ i + ","+j+","+k+"] / ";

                        tablero[i][j][k] = ".";
                        cantidadEliminados++;
                    }
                }
            }
        }
        //System.out.println("Se Eliminaron : "+ cantidadEliminados +" Animales de la lista");
        Inicializaciones.cantidadDeMuertes = Inicializaciones.cantidadDeMuertes + cantidadEliminados;
        return tablero;
    }


    public static String[][][][] ReduceVidaPorPasoDeUnidadDeTiempo(String[][][][] tablaEnergiaVida, int[] parametrosDeConfiguracion){
        // Cuando se ha completado el recorrido completo de las casillas del tablero
        // se considera que ha pasado una unidad de tiempo
        // por lo que en este momento:
        // A) Todos los elementos Animal se les Reduce una unidad de vida por el paso del tiempo
        // B) Todos los elementos Planta se les Aumenta una unidad de Energía por el paso del tiempo

        // tablaEnergiaVida [][][][X] Posición 0 = Elemento ( [A] Animal , [P] Planta )
        // tablaEnergiaVida [][][][X] Posición 1 = Energía
        // tablaEnergiaVida [][][][X] Posición 2 = Vida

        //parametrosDeConfiguracion[0] = 5; // Renglones del Tablero
        //parametrosDeConfiguracion[1] = 5; // Columnas del Tablero
        //parametrosDeConfiguracion[2] = 5; // Vida Máxima de los Animales
        //parametrosDeConfiguracion[3] = 4; // Energía tope de las plantas
        //parametrosDeConfiguracion[4] = 6; // Ciclos de Tiempo a Ejecutar

        int numeroFilas = tablaEnergiaVida.length;
        int numeroColumnas = tablaEnergiaVida[0].length;

        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                for (int k = 0; k < 2; k++) {
                    if (tablaEnergiaVida[i][j][k][0] == "A") {
                        int vida = Integer.parseInt(tablaEnergiaVida[i][j][k][2]) + 1;
                        if (vida > parametrosDeConfiguracion[2]){
                            //System.out.println("Animal debe morir: Row "+i+" Col: "+j+" Posición: "+k +" Por Vida = "+vida + " Energia = "+tablaEnergiaVida[i][j][k][1]);
                            tablaEnergiaVida[i][j][k][0]=".";
                            tablaEnergiaVida[i][j][k][1]=".";
                            tablaEnergiaVida[i][j][k][2]=".";
                            tablaEnergiaVida[i][j][k][3]=".";
                        }
                        tablaEnergiaVida[i][j][k][2] = String.valueOf(vida);
                    } else if (tablaEnergiaVida[i][j][k][0] == "P") {
                        int energia = Integer.parseInt(tablaEnergiaVida[i][j][k][1]) + 1;
                        if (energia > parametrosDeConfiguracion[3] ) {
                            energia = parametrosDeConfiguracion[3];
                        }
                        tablaEnergiaVida[i][j][k][1] = String.valueOf(energia);
                    }
                }
            }
        }

        return tablaEnergiaVida;
    }



    public static String[][][][] ActualizaPosicionEnTablaDeEnergiaVidas(String[][][][] tablaEnergiaVida, String[] coordenadasElementosAMover ){
        // coordenadasElementosAMover [0]      Posicion Anterior Row
        int anteriorRow = Integer.parseInt(coordenadasElementosAMover[0]);
        // coordenadasElementosAMover [1]      Posicion Anterior Col
        int anteriorCol = Integer.parseInt(coordenadasElementosAMover[1]);
        // coordenadasElementosAMover [2]      Posicion Anterior Posicion en la Celda (1 o 2)
        int anteriorPos = Integer.parseInt(coordenadasElementosAMover[2]);

        // coordenadasElementosAMover [3]      Posicion Nueva Row
        int nuevoRow = Integer.parseInt(coordenadasElementosAMover[3]);
        // coordenadasElementosAMover [4]      Posicion Nueva Col
        int nuevoCol = Integer.parseInt(coordenadasElementosAMover[4]);
        // coordenadasElementosAMover [5]      Posicion nueva Posicion en la Celda (1 o 2)
        int nuevoPos = Integer.parseInt(coordenadasElementosAMover[5]);

        tablaEnergiaVida[nuevoRow][nuevoCol][nuevoPos][0] = tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][0];
        tablaEnergiaVida[nuevoRow][nuevoCol][nuevoPos][1] = tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][1];
        tablaEnergiaVida[nuevoRow][nuevoCol][nuevoPos][2] = tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][2];
        tablaEnergiaVida[nuevoRow][nuevoCol][nuevoPos][3] = tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][3];

        tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][0] = ".";
        tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][1] = ".";
        tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][2] = ".";
        tablaEnergiaVida[anteriorRow][anteriorCol][anteriorPos][3] = ".";

        return tablaEnergiaVida;
    }

}
