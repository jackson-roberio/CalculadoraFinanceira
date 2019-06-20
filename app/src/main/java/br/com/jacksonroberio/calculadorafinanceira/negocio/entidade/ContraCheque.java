package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade;

import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras.AliquotaInssDefinePorcentagem;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras.BaseInssDefinirValor;
import br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras.DefineAliquotaEDeducaoIRPF;

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

    /**-------------------------------
     *      Regras INSS
     *------------------------------*/
    //Podendo ser 8%, 9% ou 11%
    public float getAlicotaInss() {
        return AliquotaInssDefinePorcentagem.calcularPorcentgagem(getSalarioBruto());
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
        return (getAlicotaInss() / 100) * getBaseInss();
    }

    public String getInssString(){
        return "R$ " + transformarString(getInss());
    }


    /**-------------------------------
     *      Regras IRPF
     *------------------------------*/
    public  float getBaseIRPF(){
        return getSalarioBruto() - getInss() - (trabalhador.getDependentes() * 189.59f);
    }

    public String getBaseIRPFString(){
        return "R$" + transformarString(getBaseIRPF());
    }

    //Para calculos internos, dividir por 100 para facilitar os calculos, visto que esse valor é uma porcentagem.
    public float getAliquotaIRPF(){
        return DefineAliquotaEDeducaoIRPF.getAliquota(getBaseIRPF());
    }

    public String getAliquotaIRPFString(){
        return transformarString(getAliquotaIRPF()) + "%";
    }

    public float getDeducaoIRPF(){
        return DefineAliquotaEDeducaoIRPF.getDeducao(getBaseIRPF());
    }

    public String getDeducaoIRPFString(){
        return "R$ " + transformarString(getDeducaoIRPF());
    }

    public float getValorIRPF(){
        return (getBaseIRPF() * (getAliquotaIRPF() / 100)) - getDeducaoIRPF();
    }

    public String getValorIRPFString(){
        return "R$ " + transformarString(getValorIRPF());
    }

    public float getSalarioLiquido(){
        return getSalarioBruto() - getInss() - getValorIRPF();
    }

    public String getSalarioLiquidoString(){
        return "R$ " + transformarString(getSalarioLiquido());
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

}
