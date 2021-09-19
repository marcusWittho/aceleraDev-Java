package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    List<Carro> estacionados = new ArrayList<>(10);

    public void estacionar(Carro carro) {
        if (!permissaoParaEstacionar(carro)) throw new EstacionamentoException("Permissão não pôde ser concedida.");

        if (carrosEstacionados() < 10) {
            estacionados.add(carro);
        } else {
            if (permissoesParaPessoaIdosa()) throw new EstacionamentoException("Não há vaga disponível.");

            for (Carro carroEstacionado: estacionados){
                if (carroEstacionado.getMotorista().getIdade() <= 55) {
                    estacionados.remove(carroEstacionado);
                    estacionados.add(carro);
                    break;
                }
            }
        }
    }

    public int carrosEstacionados() {
        return estacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return estacionados.contains(carro);
    }

    public boolean permissaoParaEstacionar(Carro carro) throws EstacionamentoException, NullPointerException
    {
        if (carro.getMotorista() == null) throw new EstacionamentoException("Não é permitido carro autônomo.");

        if (carro.getMotorista().getIdade() >= 18 && carro.getMotorista().getHabilitacao() != null && carro.getMotorista().getPontos() <= 20) {
            return true;
        } else {
            throw new EstacionamentoException("CNH inváliida, quantidade de pontos excedida.");
        }
    }

    public boolean permissoesParaPessoaIdosa() {
        for (Carro carroEstacionado: estacionados) {
            if (carroEstacionado.getMotorista().getIdade() < 55) {
                return false;
            }
        }

        return true;
    }
}
