package trabalho05;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author antonio
 */
public class PRIM {
		
    public static int [][] prim(int [][] matrizSistema){		
        ArrayList<Boolean> verticesVerificados = new ArrayList<Boolean>();
        ArrayList<Integer> distanciaRelativa = new ArrayList<Integer>();
        ArrayList<Integer> nosVizinhos = new ArrayList<Integer>();

        for(Integer contador = 0; contador < matrizSistema.length; contador++){
            verticesVerificados.add(false);
            nosVizinhos.add(0);
            distanciaRelativa.add(Integer.MAX_VALUE);
        }

        distanciaRelativa.set(0, new Integer(0));
        Integer pontoAvaliado = 0;


        for(Integer contadorPontosAvaliados = 0; contadorPontosAvaliados < matrizSistema[0].length; contadorPontosAvaliados++){
            for(Integer contadorVizinhos = 0; contadorVizinhos < matrizSistema[0].length; contadorVizinhos++){
                if((verticesVerificados.get(contadorVizinhos)) || (contadorVizinhos == pontoAvaliado)){
                    continue;
                }

                if((matrizSistema[pontoAvaliado][contadorVizinhos] > 0) &&
                    ((matrizSistema[pontoAvaliado][contadorVizinhos] < 
                    distanciaRelativa.get(contadorVizinhos)))){

                    distanciaRelativa.set(contadorVizinhos, matrizSistema[pontoAvaliado][contadorVizinhos]);
                    nosVizinhos.set(contadorVizinhos, pontoAvaliado);
                }
            }

            verticesVerificados.set(pontoAvaliado, true);

            pontoAvaliado = new Integer(0);
            Integer distanciaComparada = new Integer(Integer.MAX_VALUE);

            for(Integer contador = 1; contador < verticesVerificados.size(); contador++){
                if(verticesVerificados.get(contador)){
                    continue;
                }

                if(distanciaRelativa.get(contador) < distanciaComparada){
                    distanciaComparada = distanciaRelativa.get(contador);
                    pontoAvaliado = contador;
                }
            }
        }

        int [][] matrizResposta = new int [matrizSistema[0].length][matrizSistema[0].length];

        for(int contador = 1; contador < nosVizinhos.size(); contador++){
                matrizResposta[contador][nosVizinhos.get(contador)] = matrizSistema[contador][nosVizinhos.get(contador)];
                matrizResposta[nosVizinhos.get(contador)][contador] = matrizResposta[contador][nosVizinhos.get(contador)];
        }

        return matrizResposta;
    }
	
        
    public static void preencheMatrizDeAdjacencia(int[][] matrizDeAdjacencia, int numeroDeVertices, Scanner input) {
        int jInicial = 0;
        for (int i = 0; i < numeroDeVertices; i++) {//linhas
            for (int j = jInicial; j < numeroDeVertices ; j++) {//colunas
                if(i == j) {
                    matrizDeAdjacencia[i][j] = 0;
                    continue;
                }
                
                matrizDeAdjacencia[i][j] = input.nextInt();         
                matrizDeAdjacencia[j][i] = matrizDeAdjacencia[i][j];
            }
            jInicial++;
        }
    }
    
 
	public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
        int numeroDeVertices = input.nextInt();
        int[][] matrizSistema = new int[numeroDeVertices][numeroDeVertices];
        preencheMatrizDeAdjacencia(matrizSistema, numeroDeVertices, input);
            
        int[][] matrizResultado = prim(matrizSistema);
            
            for(int contadorHorizontal = 0; contadorHorizontal < matrizResultado.length; contadorHorizontal++){
                for(int contadorVertical = 0; contadorVertical < matrizResultado.length; contadorVertical++){

                    System.out.print(matrizResultado[contadorHorizontal][contadorVertical] + " ");
                }

                System.out.println();
            }
	}
}
