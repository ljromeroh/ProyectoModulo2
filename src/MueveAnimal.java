
public class MueveAnimal {
    public static String MueveAnimal(String[][][] tablero, int[] pasoParametros, String[][][][] tablaEnergiaVida, String bitacora){

        int rowElemento = pasoParametros[0];
        int colElemento = pasoParametros[1];
        int posInicial = pasoParametros[2];
        int cicloActual = pasoParametros[3];
        int numeroFilasTablero = tablero.length;
        int numeroColumnasTablero = tablero[0].length;
        int espaciosDisponibles = 0;
        int elemento = 0;

        espaciosDisponibles = mueveElemento.EspaciosDisponibles(tablero, rowElemento, colElemento);
        if (espaciosDisponibles == 0) {
            //System.out.println("Se queda en el mismo espacio Row: "+ rowElemento + " Columna: "+ colElemento);
            MuestraEnPantalla.ImprimeTablero(tablero);
        }
        else {
            // Llena un Array de posiciones disponibles para desplazarse y mediante el uso de Random se elige
            // cual será la nueva posición del Elemento.

            int[][] posicionesDisponibles = new int[espaciosDisponibles][3];
            posicionesDisponibles = AccionesTablero.creaListaDeEspaciosDisponibles(tablero, rowElemento, colElemento);

            int nuevaPosicionAleatoriaAsignada = (int) ((Math.random() * espaciosDisponibles));
            int[] nuevaPosicion = new int[3];
            nuevaPosicion[0] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][0];
            nuevaPosicion[1] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][1];
            nuevaPosicion[2] = posicionesDisponibles[nuevaPosicionAleatoriaAsignada][2];

            int vecino = 9;
            String elementoVecino = ".";

            if (posicionesDisponibles[nuevaPosicionAleatoriaAsignada][2] == 0) {
                vecino = 1;
            } else if (posicionesDisponibles[nuevaPosicionAleatoriaAsignada][2] == 1) {
                vecino = 0;
            }
            if (tablero[nuevaPosicion[0]][nuevaPosicion[1]][vecino]=="A"){
                elementoVecino = "A";
            } else if ((tablero[nuevaPosicion[0]][nuevaPosicion[1]][vecino]=="P")) {
                elementoVecino = "P";
            } else if ((tablero[nuevaPosicion[0]][nuevaPosicion[1]][vecino]==".")) {
                elementoVecino = ".";
            }
            // Moviendo el Elemento Animal a su nueva posición
            tablero[nuevaPosicion[0]][nuevaPosicion[1]][nuevaPosicion[2]]="A";
            // Desjando el espacio que usaba el animal antes de moverse disponible
            tablero [rowElemento][colElemento][posInicial] = ".";

            // Agregando las coordenadas que se van a actualizar (Anteriores) y (Nuevas)
            // para actualizar la tabla de EnergiaVidas
            String[] coordenadasElementosAMover = new String[6];
            coordenadasElementosAMover[0] = String.valueOf(rowElemento);
            coordenadasElementosAMover[1] = String.valueOf(colElemento);
            coordenadasElementosAMover[2] = String.valueOf(posInicial);

            coordenadasElementosAMover[3] = String.valueOf(nuevaPosicion[0]);
            coordenadasElementosAMover[4] = String.valueOf(nuevaPosicion[1]);
            coordenadasElementosAMover[5] = String.valueOf(nuevaPosicion[2]);

            tablaEnergiaVida = AccionesTablaEnergiaVida.ActualizaPosicionEnTablaDeEnergiaVidas(tablaEnergiaVida, coordenadasElementosAMover );

            //La tabla de EnergiaVidas se ha actualizado

            if (elementoVecino == "A") {
                //"Si tiene energia mayor a 1 se reproduce y solo pierde energia por la reproduccion no por el salto"
                //"si tiene energía 1 no se reproduce y muere"

                int energiaAnimal = AccionesTablaEnergiaVida.CuantaEnergiaTieneAnimal(tablaEnergiaVida, nuevaPosicion);
                if (energiaAnimal >=2){
                    // se Reproduce

                    int[] posicionNuevoAnimal = new int[3];
                    posicionNuevoAnimal = AccionesTablaEnergiaVida.PosicionNuevoAnimal(tablero, nuevaPosicion);
                    tablaEnergiaVida = AccionesTablaEnergiaVida.InicializaNuevoAnimal(tablaEnergiaVida, posicionNuevoAnimal, cicloActual);
                    tablero =  AccionesTablaEnergiaVida.AgregaNuevoAnimal(tablero,posicionNuevoAnimal);

                    tablaEnergiaVida = AccionesTablaEnergiaVida.ReduceEnergiaEnTablaDeEnergiaVidasPorReproduccion(tablaEnergiaVida, nuevaPosicion);
                    tablero = AccionesTablaEnergiaVida.EliminaElementoDelTableroPorEnergiaCero(tablero, tablaEnergiaVida);
                    Inicializaciones.cantidadDeNacimientos++;
                    bitacora = bitacora+" Nacimiento en celda ["+nuevaPosicion[0]+","+nuevaPosicion[1]+","+nuevaPosicion[2]+"] /";
                } else {
                    // no se Reproduce
                    tablaEnergiaVida = AccionesTablaEnergiaVida.ReduceEnergiaEnTablaDeEnergiaVidas(tablaEnergiaVida, nuevaPosicion);
                    tablero = AccionesTablaEnergiaVida.EliminaElementoDelTableroPorEnergiaCero(tablero, tablaEnergiaVida);
                }

            } else if (elementoVecino == "P") {
                //Absorve energía de la planta se alimenta en 1 y pierde 1 --- lo que es igual a --no pasa nada--
                //Sin embargo la planta disminuye 1 de Energía
                //Esto se hace para que al Vecino que es la Planta sea a la que se le disminuya la energía

                if (nuevaPosicion[2] == 0 ){
                    nuevaPosicion[2] = 1;
                } else if (nuevaPosicion[2] == 1) {
                    nuevaPosicion[2] = 0;
                }

                tablaEnergiaVida = AccionesTablaEnergiaVida.ReduceEnergiaEnTablaDeEnergiaVidas(tablaEnergiaVida, nuevaPosicion);
                tablero = AccionesTablaEnergiaVida.EliminaElementoDelTableroPorEnergiaCero(tablero, tablaEnergiaVida);

            } else {
                //Ha caido en casilla sin vecino -- y pierde energía

                tablaEnergiaVida = AccionesTablaEnergiaVida.ReduceEnergiaEnTablaDeEnergiaVidas(tablaEnergiaVida, nuevaPosicion);
                tablero = AccionesTablaEnergiaVida.EliminaElementoDelTableroPorEnergiaCero(tablero, tablaEnergiaVida);
            }

            MuestraEnPantalla.ImprimeTablero(tablero);

        }

        return bitacora;
    }

}
