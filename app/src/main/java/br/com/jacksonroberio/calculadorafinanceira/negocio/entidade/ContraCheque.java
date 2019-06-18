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


    /**
     * Getters & Setters
     **/
    //Podendo ser 8%, 9% ou 11%
    public double getAlicotaInss() {
        return AliquotaInssDefinirPorcentagem.calcularPorcentgagem(getSalarioBruto());
    }

    /***
     * Podendo ser o próprio salaário ou o topo do INSS
     */
    public double getBaseInss() {
        return BaseInssDefinirValor.calcular(getSalarioBruto());
    }

    /**
     * Valor do INSS
     *
     * É o multiplicação da alicaota do INSS e da base do INSS.
     **/
    public double getInss() {
        return getAlicotaInss() * getBaseInss();
    }

    public  double getBaseIRPF(){
        return getSalarioBruto() - getInss() - (trabalhador.getDependentes() * 189.59);
    }

    public double getAliquotaIRPF(){
        return 0;
    }

    /**
     * @return o salário bruto, de acordo com o definido no objeto {@link Trabalhador}
     **/
    public double getSalarioBruto(){
        return this.trabalhador.getSalario();
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

}
