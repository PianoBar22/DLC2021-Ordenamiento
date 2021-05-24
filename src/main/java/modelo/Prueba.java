package modelo;
import java.util.Scanner;

/**
 * Prueba de la clase Arreglo.
 * @author Ing. Valerio Frittelli.
 * @version Marzo 2021.
 */
public class Prueba
{
    private static Arreglo v;
    private static int n, op;
    private static long t1, t2, tf;
    
    /**
     *  Punto de entrada de la aplicacion
     */
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        v = new Arreglo (100);
        v.generar();
        
    	do
    	{
    	    System.out.println ("\nOpciones de Ordenamiento");
    	    System.out.println ("0. Generar el Arreglo");
            System.out.println ("1. Nuevo Arreglo");
            System.out.println ("2. Cargar lote 1 (25000 reg)");
            System.out.println ("3. Cargar lote 2 (50000 reg)");
            System.out.println ("4. Cargar lote 3 (100000 reg)");
    	    System.out.println ("5. Intercambio Directo (Burbuja)");
    	    System.out.println ("6. Seleccion Directa");
    	    System.out.println ("7. Insercion Directa");
    	    System.out.println ("8. Quick Sort");
    	    System.out.println ("9. Heap Sort");
    	    System.out.println ("10. Shell Sort");
    	    System.out.println ("11. Merge Sort");    	    
    	    System.out.println ("12. Verificar si esta ordenado");
            System.out.println ("13. Volver al original");            
    	    System.out.println ("14. Generar ejemplo");
    	    System.out.println ("15. Salir");
            System.out.print ("Ingrese opcion: ");
	    op = sc.nextInt();
    	    switch (op)
    	    {
                        case 0:
                                 System.out.print("Se vuelve a generar el vector...");
                                 v.generar();
                                 System.out.print("\nVector generado...");
                                 break;
                        case 1:
                                System.out.println ("Ingrese cantidad del elementos del vector: ");
                                n = sc.nextInt();
                                v = new Arreglo (n);
                                v.generar();
                                break;
                        case 2:
                            v.cargarDesdeArchivo("C:\\UTN\\DLC\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\lote01.txt");
                                break;
                        case 3:
                            v.cargarDesdeArchivo("C:\\UTN\\DLC\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\lote02.txt");
                                break;
                        case 4:
                            v.cargarDesdeArchivo("C:\\UTN\\DLC\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\Ficha 04 [Divide y Vencerás - Teorema Maestro]\\lote03.txt");
                                break;
                        case 5:
                                 System.out.print("Se ordena el vector por Intercambio...\n");
                                 System.out.println(v.toString());
                                 tf = v.ordenar(Arreglo.BUBBLE);
                                 System.out.println(v.toString());
                                 System.out.println("\nCantidad inversiones: " + v.getInversiones());
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 6:
                                 System.out.print("Se ordena el vector por Seleccion...\n");
                                 System.out.println(v.toString());
                                 tf = v.ordenar(Arreglo.SELECTION);
                                 System.out.println(v.toString());
                                 System.out.println("\nCantidad inversiones: " + v.getInversiones());
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 7:
                                 System.out.print("Se ordena el vector por Insercion... ");
                                 tf = v.ordenar(Arreglo.INSERTION);
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 8:
                                 System.out.print("Se ordena el vector por Quick Sort...");
                                 tf = v.ordenar(Arreglo.QUICK);
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 9:
                                 System.out.print("Se ordena el vector por Heap Sort...");
                                 tf = v.ordenar(Arreglo.HEAP);
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 10:
                                 System.out.print("Se ordena el vector por Shell Sort...");
                                 tf = v.ordenar(Arreglo.SHELL);
                                 System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                                 break;

                        case 11:
                            System.out.print("Se ordena el vector por Merge Sort...\n");
                            System.out.println(v.toString());
                            tf = v.ordenar(Arreglo.MERGE);
                            System.out.println(v.toString());
                            System.out.println("\nCantidad inversiones: " + v.getInversiones());
                            System.out.println("\nlongitud del vector: " + v.size());
                            System.out.print("\nVector ordenado en: " + tf + " milisegundos...");
                            break;

                        case 12:
                            System.out.println("Se verifica si esta ordenado...");
                            if(v.verificar()) { System.out.println("Esta ordenado..."); }
                            else { System.out.println ("No esta ordenado..."); }
                            break;

                        case 13:
                            System.out.print("Se vuelve al vector original...");
                            v.volverAlOriginal();
                            System.out.print("\nVector generado...");
                            break;
                        case 14: 
                            v = new Arreglo (new int[] {4, 5, 2, 6, 8, 3, 7, 9});
                            break;
                        case 15: ;
    	    }
            v.volverAlOriginal();
    	 }
    	 while (op != 15);
        }
}
