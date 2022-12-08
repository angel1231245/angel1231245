package transporte;

import java.util.Formatter;

public class Variable
{
    // Atributo que representa el stock de la variable.
    private int stock;
    // Atributo que representa la cantidad requerida de la variable.
    private int required;
    // Atributo que representa el valor de la variable.
    private double value;
    
    public Variable() {
        this.stock = 0;
        this.required = 0;
    }
    
    public Variable(final int stock, final int required) {
        this.stock = stock;
        this.required = required;
    }
    // Método que devuelve el valor del atributo "stock".
    public int getStock() {
        return this.stock;
    }
    // Método que establece el valor del atributo "stock".
    public void setStock(final int stock) {
        this.stock = stock;
    }
    // Método que devuelve el valor del atributo "required".
    public int getRequired() {
        return this.required;
    }
    // Método que establece el valor del atributo "required".
    public void setRequired(final int required) {
        this.required = required;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        final Formatter f = new Formatter();
        f.format("x[%d,%d]=%f", this.stock + 1, this.required + 1, this.value);
        return f.toString();
    }
}