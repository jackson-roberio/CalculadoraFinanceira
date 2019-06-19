package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade;

import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras.AliquotaInssDefinirPorcentagem;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras.BaseInssDefinirValor;

/**
 * Classe responsável por calcular o valor do contracheque, definindo o valor
 * liquido a ser pago ao funcionário, de acordo com o valor de salário e dependentes.
 *
 * @see Trabalhador
 * @author Jackson Roberio
 **/
public class ContraCheque {

    private Trabalhador trabalhador;


    public ContraCheque() {
    }

    public ContraCheque(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }


    private String transformarString(float valor){
        return String.format("%.02f", valor);
    }


    /**
     * Getters & Setters
     **/
    //Podendo ser 8%, 9% ou 11%
    public float getAlicotaInss() {
        return AliquotaInssDefinirPorcentagem.calcularPorcentgagem(getSalarioBruto());
    }

    public String getAlicotaInssString(){
        return transformarString(getAlicotaInss()) + "%";
    }

    /***
     * Podendo ser o próprio salaário ou o topo do INSS
     */
    public float getBaseInss() {
        return BaseInssDefinirValor.calcular(getSalarioBruto());
    }

    public String getBaseInssString(){
        return "R$ " +transformarString(getBaseInss());
    }

    /**
     * Valor do INSS
     *
     * É o multiplicação da alicaota do INSS e da base do INSS.
     **/
    public float getInss() {
        return getAlicotaInss() * getBaseInss();
    }

    public String getInssString(){
        return "R$ " + transformarString(getInss());
    }


    public  float getBaseIRPF(){
        return getSalarioBruto() - getInss() - (trabalhador.getDependentes() * 189.59f);
    }

    public double getAliquotaIRPF(){
        return 0;
    }

    /**
     * @return o salário bruto, de acordo com o definido no objeto {@link Trabalhador}
     **/
    public float getSalarioBruto(){
        return this.trabalhador.getSalario();
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

}
