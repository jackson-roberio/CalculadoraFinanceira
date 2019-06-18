package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade;

public class Trabalhador {

    private double salario;

    private int dependentes;



    /**
     *  Gettres & Setters
     * */
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }
}
