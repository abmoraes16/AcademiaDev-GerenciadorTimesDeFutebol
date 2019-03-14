package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
	final Map<Long,Time> time = new HashMap<Long,Time>();
	final Map<Long,Jogador> jogador = new HashMap<Long,Jogador>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		try {
			if(time.get(id) == null)
				time.put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
			else
				throw new IdentificadorUtilizadoException();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		try {
			if(time.get(idTime) == null)
				throw new TimeNaoEncontradoException();

			if (jogador.get(id) != null)
				throw new IdentificadorUtilizadoException();

			jogador.put(id, new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario));
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		try{
			if(jogador.get(idJogador)== null)
				throw new JogadorNaoEncontradoException();

			time.entrySet()
					.stream()
					.map(v -> v.getValue())
					.filter(v -> v.getId().equals(jogador.get(idJogador).getIdTime()))
					.findAny()
					.get()
					.setCapitao(jogador.get(idJogador));
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		try{
			if(time.get(idTime)==null)
				throw new TimeNaoEncontradoException();

			if(time.get(idTime).getCapitao()==null)
				throw new CapitaoNaoInformadoException();

			return time.get(idTime).getCapitao().getId();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		try{
			if (jogador.get(idJogador) == null)
				throw new JogadorNaoEncontradoException();

			return jogador.get(idJogador).getNome();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		try{
			if (time.get(idTime) == null)
				throw new TimeNaoEncontradoException();

			return time.get(idTime).getNome();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		try{
			if (time.get(idTime) == null)
				throw new TimeNaoEncontradoException();

			return jogador.entrySet()
					.stream()
					.filter(v -> v.getValue().getIdTime().equals(idTime))
					.map(v -> v.getKey())
					.sorted()
					.collect(Collectors.toList());
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		try {
			if(time.get(idTime)==null)
				throw new TimeNaoEncontradoException();

			return jogador.entrySet()
					.stream()
					.filter(v -> v.getValue().getIdTime().equals(idTime))
					.max(Comparator.comparingInt(v -> v.getValue().getNivelHabilidade()))
					.get()
					.getKey();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		try {
			return jogador.entrySet()
					.stream()
					.filter(v -> v.getValue().getIdTime().equals(idTime))
					.sorted(Comparator.comparingLong(v->v.getKey()))
					.min(Comparator.comparing(v->v.getValue().getDataNascimento()))
					.orElseThrow(()-> new TimeNaoEncontradoException())
					.getKey();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		try {
			return time.entrySet()
					.stream()
					.map(v->v.getKey())
					.sorted()
					.collect(Collectors.toList());
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		try {
			return jogador.entrySet()
					.stream()
					.filter(v -> v.getValue().getIdTime().equals(idTime))
					.sorted(Comparator.comparingLong(v->v.getKey()))
					.max(Comparator.comparing(v->v.getValue().getSalario()))
					.orElseThrow(()->new TimeNaoEncontradoException())
					.getKey();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		try {
			return jogador.entrySet()
					.stream()
					.map(v->v.getValue())
					.filter(v -> v.getId().equals(idJogador))
					.findAny()
					.orElseThrow(()->new JogadorNaoEncontradoException())
					.getSalario();
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		try{
			return jogador.entrySet()
					.stream()
					.map(v->v.getValue())
					.sorted(Comparator.comparingLong(Jogador::getId))
					.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
					.limit(top)
					.map(v->v.getId())
					.collect(Collectors.toList());
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		try{
			if((time.get(timeDaCasa)==null) || (time.get(timeDeFora)==null))
				throw new TimeNaoEncontradoException();

			Optional<Time> timeDaCasaDados = time.entrySet()
					.stream()
					.map(v->v.getValue())
					.filter(v -> v.getId().equals(timeDaCasa))
					.findAny();

			Optional<Time> timeDeForaDados = time.entrySet()
					.stream()
					.map(v->v.getValue())
					.filter(v -> v.getId().equals(timeDeFora))
					.findAny();

			String corPrincipalTimeDeFora = timeDeForaDados.get().getCorUniformePrincipal();
			String corSecundariaTimeDeFora = timeDeForaDados.get().getCorUniformeSecundario();
			String corPrincipalTimeDaCasa = timeDaCasaDados.get().getCorUniformePrincipal();

			return corPrincipalTimeDaCasa.equals(corPrincipalTimeDeFora) ? corSecundariaTimeDeFora : corPrincipalTimeDeFora;
		}
		catch(UnsupportedOperationException ex){
			throw ex;
		}
	}

}
