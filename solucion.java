package transporte;

import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

public class solucion
{
    // Array para almacenar la cantidad requerida de cada destino
    double[] required; // demanda
    // Array para almacenar la cantidad disponible en cada origen
    double[] stock; // oferta
    // Matriz para almacenar los costos de transporte entre cada origen y destino
    double[][] cost; // costos de transporte
    // Lista enlazada para almacenar las variables de la solución
    LinkedList<Variable> feasible; // lista de variables factibles
    // Tamaño de la lista de origen
    int stockSize; //tamaño de la oferta
    // Tamaño de la lista de destino
    int requiredSize; // tamaño de la demanda
    
    public solucion(final int stockSize, final int requiredSize) {
        // Inicialización de la lista enlazada
        this.feasible = new LinkedList<Variable>();
        this.stockSize = stockSize;
        this.requiredSize = requiredSize;
        // Inicialización de los arreglos
        this.stock = new double[stockSize];
        this.required = new double[requiredSize];
        this.cost = new double[stockSize][requiredSize];
        // Inicialización de la lista enlazada con objetos Variable
        for (int i = 0; i < requiredSize + stockSize - 1; ++i) {
            this.feasible.add(new Variable());
        }
    }
    // Método para establecer la cantidad disponible en un origen
    public void setStock(final double value, final int index) {
        this.stock[index] = value;
    }
    // Método para establecer la cantidad requerida en un destino
    public void setRequired(final double value, final int index) {
        this.required[index] = value;
    }
    // Método para establecer el costo de transporte entre un origen y un destino
    public void setCost(final double value, final int stock, final int required) {
        this.cost[stock][required] = value;
    }
        /*
    Este método implementa el algoritmo de esquina noroeste para resolver el problema de transporte.
    Devuelve el tiempo en segundos que tarda en ejecutarse.
    */
    // Método para calcular la solución usando el método de la esquina noroeste
    public double northWestCorner() {
         // Obtención del tiempo actual en nanosegundos
        final long start = System.nanoTime();
        int k = 0;
        // Matriz booleana para marcar las celdas ya usadas
        final boolean[][] isSet = new boolean[this.stockSize][this.requiredSize];
        // Inicialización de la matriz en falso
        for (int j = 0; j < this.requiredSize; ++j) {
            for (int i = 0; i < this.stockSize; ++i) {
                isSet[i][j] = false;
            }
        }
        for (int j = 0; j < this.requiredSize; ++j) {
            for (int i = 0; i < this.stockSize; ++i) {
                if (!isSet[i][j]) {
                    final double min = Math.min(this.required[j], this.stock[i]);
                    this.feasible.get(k).setRequired(j);
                    this.feasible.get(k).setStock(i);
                    this.feasible.get(k).setValue(min);
                    ++k;
                    final double[] required = this.required;
                    final int n = j;
                    required[n] -= min;
                    final double[] stock = this.stock;
                    final int n2 = i;
                    stock[n2] -= min;
                    if (this.stock[i] == 0.0) {
                        for (int l = 0; l < this.requiredSize; ++l) {
                            isSet[i][l] = true;
                        }
                    }
                    else {
                        for (int l = 0; l < this.stockSize; ++l) {
                            isSet[l][j] = true;
                        }
                    }
                }
            }
        }
        return (System.nanoTime() - start) * 1.0E-9;
    }
    
    public double leastCostRule() {
        final long start = System.nanoTime();
        int k = 0;
        final boolean[][] isSet = new boolean[this.stockSize][this.requiredSize];
        for (int j = 0; j < this.requiredSize; ++j) {
            for (int i = 0; i < this.stockSize; ++i) {
                isSet[i][j] = false;
            }
        }
        int l = 0;
        int m = 0;
        final Variable minCost = new Variable();
        while (k < this.stockSize + this.requiredSize - 1) {
            minCost.setValue(Double.MAX_VALUE);
            for (int m2 = 0; m2 < this.stockSize; ++m2) {
                for (int n = 0; n < this.requiredSize; ++n) {
                    if (!isSet[m2][n] && this.cost[m2][n] < minCost.getValue()) {
                        minCost.setStock(m2);
                        minCost.setRequired(n);
                        minCost.setValue(this.cost[m2][n]);
                    }
                }
            }
            l = minCost.getStock();
            m = minCost.getRequired();
            final double min = Math.min(this.required[m], this.stock[l]);
            this.feasible.get(k).setRequired(m);
            this.feasible.get(k).setStock(l);
            this.feasible.get(k).setValue(min);
            ++k;
            final double[] required = this.required;
            final int n2 = m;
            required[n2] -= min;
            final double[] stock = this.stock;
            final int n3 = l;
            stock[n3] -= min;
            if (this.stock[l] == 0.0) {
                for (int l2 = 0; l2 < this.requiredSize; ++l2) {
                    isSet[l][l2] = true;
                }
            }
            else {
                for (int l2 = 0; l2 < this.stockSize; ++l2) {
                    isSet[l2][m] = true;
                }
            }
        }
        return (System.nanoTime() - start) * 1.0E-9;
    }
    
    public double getSolution() {
        double result = 0.0;
        for (final Variable x : this.feasible) {
            result += x.getValue() * this.cost[x.getStock()][x.getRequired()];
        }
        return result;
    }
    
    public static void main(final String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the problem size: ");
        final int s = scanner.nextInt();
        final int r = scanner.nextInt();
        final solucion test = new solucion(s, r);
        System.out.println("Please enter the stocks capacity: ");
        for (int i = 0; i < test.stockSize; ++i) {
            final double x = scanner.nextDouble();
            test.setStock(x, i);
        }
        System.out.println("Please enter the requirements: ");
        for (int i = 0; i < test.requiredSize; ++i) {
            final double x = scanner.nextDouble();
            test.setRequired(x, i);
        }
        System.out.println("Please enter the transportation costs: ");
        for (int i = 0; i < test.stockSize; ++i) {
            for (int j = 0; j < test.requiredSize; ++j) {
                final double x = scanner.nextDouble();
                test.setCost(x, i, j);
            }
        }
        test.leastCostRule();
        for (final Variable t : test.feasible) {
            System.out.println(t);
        }
        System.out.println("Target function: " + test.getSolution());
    }
}