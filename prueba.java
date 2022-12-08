package transporte;

import java.util.Scanner;

public class prueba
{
    public static void main(final String[] args) {
        // Declaración de matrices y vectores que se usarán en el código
        final int[][] a = new int[10][10]; // matriz de costos
        final int[][] b = new int[10][10]; // matriz de la solución óptima del problema
        final int[][] t = new int[10][10]; // matriz auxiliar
        final int[] c = new int[10]; // vector de oferta de las fabricas
        final int[] r = new int[10]; // vector de demanda de los almacenes
        int cc = 0; // oferta total de las fabricas
        int rr = 0; // demanda total de los almacenes
        int ct = 0; // costo total
        int sm = 0; // menor costo de una celda de la matriz de costos
        int m = 0; // fila del menor costo
        int n = 0; // columna del menor costo

        // Creamos un objeto Scanner para leer datos ingresados por el usuario
        final Scanner sc = new Scanner(System.in);

        // Pedimos al usuario el número de oferta
        System.out.println("Ingrese el numero de oferta ");
        final int s = sc.nextInt();

        // Pedimos al usuario el número de demanda
        System.out.println("Ingrese el numero de demanda ");
        final int d = sc.nextInt();

        // Pedimos al usuario la demanda de los almacenes
        System.out.println("Ingrese la demanda de los almacenes ");
        for (int k = 0; k < d; ++k) {
            r[k] = sc.nextInt();
            rr += r[k];
        }
        // Pedimos al usuario la oferta de las fabricas
        System.out.println("Ingrese la oferta de las fabricas ");
        for (int k = 0; k < s; ++k) {
            c[k] = sc.nextInt();
            cc += c[k];
        }
        // Verificamos si el problema está balanceado
        if (rr != cc) {
            System.out.println("Perdon,el problema no esta balanceado ");
            System.exit(0);
        }
         // Pedimos al usuario los costos
        System.out.println("Ingrese los costos ");
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < d; ++j) {
                a[i][j] = sc.nextInt();
            }
        }
        //Inicializamos la variable sm con un valor grande
        sm = 1000;
        final int deg = s + d - 1;
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < d; ++j) {
                if (sm > a[i][j] && a[i][j] > 0) {
                    sm = a[i][j];
                    t[i][j] = sm;
                    m = i;
                    n = j;
                }
            }
        }
        if (c[m] > r[n]) {
            c[m] -= r[n];
            b[m][n] = r[n];
            r[n] = 0;
            for (int q = 0; q < s; ++q) {
                a[q][n] = 0;
            }
        }
        else if (c[m] < r[n]) {
            r[n] -= c[m];
            b[m][n] = c[m];
            c[m] = 0;
            for (int q = 0; q < s; ++q) {
                a[m][q] = 0;
            }
        }
        else {
            b[m][n] = r[n];
            int q;
            for (q = 0; q < s; ++q) {
                a[q][n] = 0;
            }
            a[m][q] = 0;
            c[m] = (r[n] = 0);
        }
        sm = 1000;
        for (int i = 0; i < s; ++i) {
            for (int j = 0; j < d; ++j) {
                if (b[i][j] > 0) {
                    ct += t[i][j] * b[i][j];
                }
            }
        }
        System.out.println("El costo es: " + ct);
    }
}