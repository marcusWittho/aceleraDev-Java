package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<TimeClass> times = new ArrayList<>();
	List<Jogador> jogadores = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException
	{
		if(searchTeam(id) != null) throw new IdentificadorUtilizadoException();

		times.add(new TimeClass(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	private TimeClass searchTeam(Long id) {
		for (TimeClass time: times) {
			if(time.getId().equals(id)) return time;
		}
		return null;
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws IdentificadorUtilizadoException, TimeNaoEncontradoException
	{
		if (searchPlayer(id) != null) throw new IdentificadorUtilizadoException();

		if(searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	private Jogador searchPlayer(Long id) {
		for (Jogador jogador: jogadores) {
			if (jogador.getId().equals(id)) return jogador;
		}
		return null;
	}

	public void definirCapitao(Long idJogador) throws TimeNaoEncontradoException, JogadorNaoEncontradoException {
		if (searchPlayer(idJogador) == null) throw new JogadorNaoEncontradoException();

		for (TimeClass time: times ) {
			for (Jogador jogador: jogadores) {
				if (jogador.getId() == idJogador) {
					jogador.setCapitao(true);
					time.setIdCapitao(jogador.getId());
				} else {
					jogador.setCapitao(false);
				}
			}
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) throws TimeNaoEncontradoException, CapitaoNaoInformadoException
	{
		if (searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		Long idCapitao = 0L;

		for (TimeClass time: times) {
			if (time.getId() == idTime) {
				if (time.getIdCapitao() != 0) {
					idCapitao = time.getIdCapitao();
				} else {
					throw new CapitaoNaoInformadoException();
				}
			}
		}

		return idCapitao;
	}

	public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException
	{
		if (searchPlayer(idJogador) == null) {
			throw new JogadorNaoEncontradoException();
		} else {
			return searchPlayer(idJogador).getNome();
		}
	}

	public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException
	{
		if (searchTeam(idTime) == null) {
			throw new TimeNaoEncontradoException();
		} else {
			return searchTeam(idTime).getNome();
		}
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException
	{
		if (searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		List<Long> playersIds = new ArrayList<>();

		for (TimeClass time: times) {
			if (time.getId() == idTime) {
				for (Jogador jogador: jogadores) {
					playersIds.add(jogador.getId());
				}
			}
		}

		return playersIds;
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException
	{
		if (searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		Long melhorJogador = 0L;

		for (TimeClass time: times) {
			if (time.getId() == idTime) {
				jogadores.sort((jogador1, jogador2) -> jogador2.getNivelHabilidade().compareTo(jogador1.getNivelHabilidade()));
				melhorJogador = jogadores.get(0).getId();
			}
		}

		return melhorJogador;
	}

	public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException
	{
		if (searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		Long idJogadorMaisVelho = 0L;

		for (TimeClass time: times) {
			if (time.getId() == idTime) {
				jogadores.sort((jogador1, jogador2) -> jogador2.getDataNascimento().compareTo(jogador1.getDataNascimento()));
				Collections.reverse(jogadores);
				idJogadorMaisVelho = jogadores.get(0).getId();
			}
		}

		return idJogadorMaisVelho;
	}

	public List<Long> buscarTimes() {
		List<Long> timesLista = new ArrayList<>();
		for (TimeClass time: times) {
			timesLista.add(time.getId());
		}

		timesLista.sort(Comparator.naturalOrder());
		return timesLista;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException
	{
		if (searchTeam(idTime) == null) throw new TimeNaoEncontradoException();

		Long jogadorId = 0L;

		for (TimeClass time: times) {
			if (time.getId() == idTime) {
				jogadores.sort((jogador1, jogador2) -> jogador2.getSalario().compareTo(jogador1.getSalario()));
				jogadorId = jogadores.get(0).getId();
			}
		}

		return jogadorId;
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException
	{
		if (searchPlayer(idJogador) == null) throw new JogadorNaoEncontradoException();

		BigDecimal salario = new BigDecimal(0);

		for (Jogador jogador: jogadores) {
			if (jogador.getId() == idJogador) {
				salario = jogador.getSalario();
			}
		}

		return salario;
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> topJogadores = new ArrayList<>();
		int iterator = 0;

		jogadores.sort((jogador1, jogador2) -> jogador2.getNivelHabilidade().compareTo(jogador1.getNivelHabilidade()));

		while (iterator < top) {
			topJogadores.add(jogadores.get(iterator).getId());
			iterator += 1;
		}

		if (topJogadores.isEmpty()) {
			ArrayList vazia = new ArrayList();
			return vazia;
		} else {
			return topJogadores;
		}
	}
}
