package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade;

public class Trabalhador {

    private Float salario;

    private Integer dependentes;

    public Trabalhador() {
    }

    public Trabalhador(float salario, int dependentes) {
        this.salario = salario;
        this.dependentes = dependentes;
    }

    /**
     *  Gettres & Setters
     * */
    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Integer getDependentes() {
        return dependentes;
    }

    public void setDependentes(Integer dependentes) {
        this.dependentes = dependentes;
    }
}
