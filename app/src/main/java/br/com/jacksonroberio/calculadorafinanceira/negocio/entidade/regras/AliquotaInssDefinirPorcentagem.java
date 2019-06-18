package br.com.jacksonroberio.calculadorafinanceira.negocio.entidade.regras;

/**
* @author Jackson Roberio
*/
public class AliquotaInssDefinirPorcentagem {

    private static final double OITO_PORCENTO = 1751.82;

    private static final double ONZE_PORCENTO = 2919.72;


    /**
     * Retorna o valor da porcentagem em inteiro, visto que ele poder ser
     * um dos resultados:
     *
     * 8% = se o salário for inferior a {@value OITO_PORCENTO};
     * 9% = se o salálrio ficar entre R$ 1751,81 e R$ 2919,72;
     * 11%  = se o salário for superior à {@value ONZE_PORCENTO}
     **/
    public static double calcularPorcentgagem(double salario){
        double resultado;

        if(salario < OITO_PORCENTO)
            resultado = 8;
        else if (salario > ONZE_PORCENTO)
            resultado = 11;
        else
            resultado = 9;

        return resultado;
    }

}
