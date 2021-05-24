package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Representa un arreglo de elementos int, y plantea diversos metodos de ordenamiento
 *  a traves del patron Strategy.
 *  @author Ing. Valerio Frittelli.
 *  @version Agosto de 2011.
 */
public class Arreglo
{
    public static final int BUBBLE = 0;
    public static final int SELECTION = 1;
    public static final int INSERTION = 2;
    public static final int QUICK = 3;
    public static final int HEAP = 4;
    public static final int SHELL = 5;
    public static final int MERGE = 6;

    // el arreglo a ordenar
    private long[] v;
    private long[] original;
    
    // el objeto que contiene el metodo de ordenamiento a aplicar
    private Ordenable ordenador;

    void cargarDesdeArchivo(String path) {
        File myObj = new File(path);
        ArrayList<Long> arrList = new ArrayList<Long>();
        try (Scanner myReader = new Scanner(myObj)) {
                myReader.useDelimiter("[ .,/[\\r\\n;]\\[\\]'\\(\\)\\-\":;0-9!?@*]");
                
                while(myReader.hasNext()){
                    Long dato = Long.parseLong(myReader.nextLine());
                    if (dato != null)
                        arrList.add(dato);
                }
            } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Arreglo.class.getName()).log(Level.SEVERE, null, ex);
        }
        v = new long[arrList.size()];
        original = new long[arrList.size()];
  
        // ArrayList to Array Conversion
        for (int i = 0; i < arrList.size(); i++)
            {
                v[i] = arrList.get(i);    
                original[i] = arrList.get(i);    
            }
        }

    long size() {
        return v.length;
    }

    // clase privada para el patron Strategy: ordenamiento por intercambio directo
    private class Intercambio implements Ordenable
    {
        private long inversiones = 0;
        
        // ordenamiento de Intercambio (Burbuja)
        public void ordenar()
        {
              boolean ordenado = false;
              int n = v.length;
              inversiones = 0;
              for( int i=0; i<n-1 && !ordenado; i++ )
              {
                    ordenado = true;
                    for( int j=0; j<n-i-1; j++ )
                    {
                         if( v[j] > v[j+1] )
                         {
                               long aux = v[j];
                               v[j] = v[j+1];
                               v[j+1] = aux;
                               ordenado = false;
                               inversiones++;
                         }
                    }
              }           
        }

        @Override
        public long getInversiones() {
            return inversiones;
        }
    }
    
    // clase privada para el patron Strategy: ordenamiento por seleccion directa
    private class Seleccion implements Ordenable
    {
        private long inversiones = 0;
        //private PrintStream logFile;

        // ordenamiento de Seleccion
        public void ordenar()
        {
            //try {
                //logFile = new PrintStream("C:\\logSeleccionDirecta.txt");
                int n = v.length;
                inversiones = this.contar();
                for(int i = 0; i < n - 1; i++ )
                {
                    for( int j = i + 1; j < n; j++ )
                    {
                        if( v[i] > v[j] )
                        {
                            long aux = v[i];
                            v[i] = v[j];
                            v[j] = aux;
                            //inversiones++;
                        }
                    }
                }
            //    logFile.println("\n" + this.toString());
            //} catch (FileNotFoundException ex) {
            //    Logger.getLogger(Arreglo.class.getName()).log(Level.SEVERE, null, ex);
            //}
        }

        public long contar(){
            int n = v.length;
            long c = 0;
            
            for (int i = 0; i<n-1; i++){
                for (int j = i + 1; j < n; j++){
                    if(v[i] > v[j]){
                        //logFile.println("inversion: " + j + " con " + i + ", [" + v[j] + ", " + v[i] + "]");
                        c++;
                    }
                }
            }
            
            return c;
        }
        
        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for ( int i=0; i < v.length; i++)
            {
                sb.append(v[i]);
                if(i < v.length - 1){
                 sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
        
        @Override
        public long getInversiones() {
            return inversiones;
        }
    }

    // clase privada para el patron Strategy: ordenamiento por insercion directa
    private class Insercion implements Ordenable
    {
        // ordenamiento de Insercion
        @Override
        public void ordenar()
        {
              int n = v.length;
              for( int j = 1; j < n; j++ )
              {
                    int k;
                    long y = v[j];
                    for( k = j-1; k >= 0 && y < v[k]; k-- )
                    {
                        v[k+1]= v[k];
                    }
                    v[k+1]= y;
              }
        }

        @Override
        public long getInversiones() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    // clase privada para el patron Strategy: ordenamiento Quick Sort
    private class QuickSort implements Ordenable
    {
        // ordenamiento Quick Sort
        @Override
        public void ordenar()
        {
              ordenar( 0, v.length - 1 );
        }
        
        private void ordenar (int izq, int der)
        {
              int i = izq, j = der;
              long y;
              long x = v[(izq + der) / 2];
              do 
              {
                    while( v[i] < x && i < der ) { i++; }
                    while( x < v[j] && j > izq ) { j--; }
                    if( i <= j )
                    {
                          y = v[i];
                          v[i] = v[j];
                          v[j] = y;
                          i++;
                          j--;
                    }
              }
              while( i <= j );
              if( izq < j ) ordenar( izq, j );
              if( i < der ) ordenar( i, der );
        }        

        @Override
        public long getInversiones() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    // clase privada para el patron Strategy: ordenamiento Heap Sort
    private class HeapSort implements Ordenable
    {
        // ordenamiento Heap Sort
        @Override
        public void ordenar()
        {
               int n = v.length;
        
               // crear el grupo inicial...
               for( int i = 1; i < n; i++ )
               {
                    long e = v[i];
                    int s = i;
                    int f = (s-1)/2;
                    while( s>0 && v[f] < e )
                    {
                          v[s] = v[f];
                          s = f;
                          f = (s-1)/2;
                    }
                    v[s] = e;
               }
        
               // se extrae la raiz, y se reordena el vector y el grupo...
               for(int i = n-1; i>0; i-- )
               {
                    long valori = v[i];
                    v[i] = v[0];
                    int f = 0, s;
                    if( i == 1 ) s = -1; else s = 1;
                    if( i > 2 && v[2] > v[1] )  s = 2;
                    while( s >= 0 && valori < v[s] )
                    {
                          v[f] = v[s];
                          f = s;
                          s = 2*f + 1;
                          if( s + 1 <= i - 1 && v[s] < v[s+1] ) s++;
                          if( s > i - 1 ) s = -1;
                    }
                    v[f] = valori;
               }
        }

        @Override
        public long getInversiones() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    // clase privada para el patron Strategy: ordenamiento Shell Sort
    private class ShellSort implements Ordenable
    {
        // ordenamiento Shell Sort
        public void ordenar()
        {
               int h, n = v.length;
               for( h = 1; h <= n / 9; h = 3*h + 1 );
               for ( ; h > 0; h /= 3 )
               {
                     for (int j = h; j < n; j++)
                     {
                          int k;
                          long y = v[j];
                          for( k = j - h; k >= 0 && y < v[k]; k-=h )
                          {
                                v[k+h] = v[k];
                          }
                          v[k+h] = y;
                     }
               }
        }

        @Override
        public long getInversiones() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    // clase privada para el patron Strategy: ordenamiento Merge Sort
    private class MergeSort implements Ordenable
    {
        // vector auxiliar para almacenar las copias temporales
        private long temp [];
        private long inversiones;
        //private PrintStream logFile;
        
        // ordenamiento Merge Sort
        public void ordenar()
        {
            //try {
                //logFile = new PrintStream("C:\\logMerge.txt");
                int n = v.length;
                temp = new long[n];
                inversiones = 0;
                //logFile.println("Empezo");
                sort(0, n - 1);
                //logFile.println("Termino");
                //logFile.close();
            //} catch (IOException ex) {
                //Logger.getLogger(Arreglo.class.getName()).log(Level.SEVERE, null, ex);
            //}
        }
        
        
        private void sort(int izq, int der)
        {
            if(izq < der) 
            {
                int centro = (izq + der) / 2;
                sort(izq, centro);
                sort(centro + 1, der);
                merge(izq, centro, der);
            }    
        }       
       
        public String toString(long[] vec, int izq, int der){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for ( int i=izq; i <= der; i++)
            {
                sb.append(vec[i]);
                if(i != der){
                    sb.append(", ");
                }
            }
            
            sb.append("]");
            return sb.toString();
        }
       public String toString(int izq, int der){
           return this.toString(v, izq, der);
        }
    
    @Override
    public String toString(){
       return this.toString(v, 0, v.length-1);
    }

    private void merge(int izq, int centro, int der)
        {
            for(int i = izq; i <= der; i++) { temp[i] = v[i]; }
            int i = izq, j = centro + 1, k = izq;
            //logFile.println("\n" + this.toString());
            //logFile.println("\nizq: " + izq + "(" + v[izq] + "), centro: " + centro + "(" + v[centro] + "), der: " + der + "(" + v[der] + ")");
            //logFile.println("v: " + this.toString(izq, centro) + ", " + this.toString(centro + 1, der));
            long totalInv = 0;
            while(i <= centro && j <= der)
            {
                if(temp[i] <= temp[j])
                {
                    v[k] = temp[i];
                    i++;
                }
                else
                {
                    //logFile.println("inversion(j, i): " + j + " con " + i + ", [" + temp[j] + ", " + temp[i] + "]");
                    int lengthIzq = centro + 1;
                    int posIzq = i;

                    totalInv += (lengthIzq - posIzq);
                    //logFile.println("inversiones: " + (lengthIzq - posIzq));

                    v[k] = temp[j];
                    j++;
                }
                k++;
            }   
            inversiones += totalInv;
            //logFile.println("total inversiones: " + totalInv);
            while(i <= centro) 
            {
                v[k] = temp[i];
                k++;
                i++;
            }
            //logFile.println("v: " + this.toString(izq, der));
        }
        
        public int contar (long[] vec, int izq, int der){
            int c = 0;
            for(int i=izq; i<der;i++){
                if(vec[der] > vec[i]){
                    c++;
                }
            }
            return c;
        }
        @Override
        public long getInversiones() {
            return inversiones;
        }
    }
    
    /**
     *  Crea un arreglo de tamaÃ±o n, y le asigna un ordenador que implementa 
     *  el algoritmo Quick Sort. Si n es menor o igual cero, el arreglo se crea
     *  de tamaÃ±o 100.
     *  @param n el tamaÃ±o del arreglo a crear.
     */
    public Arreglo( int n )
    {
        if( n <= 0 ) n = 100;
        v = new long[n];
        original = new long[n];
        ordenador = new QuickSort();
    }

    public Arreglo( int[] vec )
    {
        if( vec.length <= 0 )
        {
            v = new long[100];
            original = new long[100];
        }else
        {
            v = new long[vec.length];
            original = new long[vec.length];
            for( int i=0; i< vec.length; i++ )
            {
                 v[i] = vec[i];
                 original[i] = v[i];
            }
        };
        ordenador = new QuickSort();
    }

    /**
     *  Genera un arreglo con valores aleatorios. 
     */
    public void generar ()
    {
        for( int i=0; i<v.length; i++ )
       {
            v[i] = (int) Math.round(100 * Math.random());
            original[i] = v[i];
       }
    }

    
    public void volverAlOriginal(){
        System.arraycopy(original, 0, v, 0, v.length);
    }
    /**
     *  Verifica si el arreglo esta ordenado.
     *  @return true si esta ordenado - false si no lo esta.
     */
    public boolean verificar ()
    {
       for ( int i=0; i < v.length - 1; i++)
       {
            if (v[i] > v[i+1]) return false;
       }
       return true;
    }
    
    /**
     * Cambia el metodo de ordenamiento que debe aplicarse sobre el arreglo.
     * @param method un valor que identifica a la version deseada del metodo ordenar().
     */
    public void setOrdenador( int method )
    {
        Ordenable ord = null;    
        switch( method )
        {
            case BUBBLE:    ord = new Intercambio(); break;           
            case SELECTION: ord = new Seleccion(); break;
            case INSERTION: ord = new Insercion(); break;
            case QUICK:     ord = new QuickSort(); break;
            case HEAP:      ord = new HeapSort(); break;
            case SHELL:     ord = new ShellSort(); break;
            case MERGE:     ord = new MergeSort(); break;
        }
        if( ord != null ) ordenador = ord;
    }
    
    /**
     * Ordena el arreglo.
     */
    public void ordenar()
    {
        ordenador.ordenar();
    }
    
    public long getInversiones(){
        return ordenador.getInversiones();
    }
    
    public long ordenar(int method){
        this.setOrdenador( method );
        long t1 = System.currentTimeMillis();
        this.ordenar();
        long t2 = System.currentTimeMillis();
        long tf = t2 - t1;
        
        return tf;
    }
   
    @Override
    public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("[");
       for ( int i=0; i < v.length; i++)
       {
           sb.append(v[i]);
           if(i < v.length - 1){
            sb.append(", ");
           }
       }
       sb.append("]");
       return sb.toString();
    }
}
