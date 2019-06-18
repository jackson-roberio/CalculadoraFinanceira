package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras;

/**
 * Regra de negócio responsável por definir o valor da base do INSS,
 * podendo ser até o teto do INSS {@value #LIMITE_INSS} ou o valor do salário.
 *
 * @author Jackson Roberio
 **/
public class BaseInssDefinirValor {

    private static final double LIMITE_INSS = 5839.45;

    public static double calcular(double salario){
        if (salario > LIMITE_INSS)
            return LIMITE_INSS;
        else
            return salario;
    }
}
