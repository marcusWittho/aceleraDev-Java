package br.com.codenation.calculadora;


public class CalculadoraSalario {

  public long calcularSalarioLiquido(double salarioBase) {
    //Use o Math.round apenas no final do método para arredondar o valor final.
    //Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

    if (salarioBase < 1039.00) return (long) 0.0;

    if (salarioBase <= 1500.00) {
      salarioBase *= 0.92;
    } else if (salarioBase > 1500.00 && salarioBase < 4000.01) {
      salarioBase *= 0.91;
    } else {
      salarioBase *= 0.89;
    }

    if (salarioBase > 3000) salarioBase = calcularInss(salarioBase);

    return Math.round(salarioBase);
  }

  //Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
  private double calcularInss(double salarioBase) {
    double salarioDepoisDosTributos = 0.0;

    if (salarioBase > 3000.00 && salarioBase <= 6000.00) {
      salarioDepoisDosTributos = salarioBase * 0.925;
    } else {
      salarioDepoisDosTributos = salarioBase * 0.85;
    }

    return Math.round(salarioDepoisDosTributos);
  }
}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/