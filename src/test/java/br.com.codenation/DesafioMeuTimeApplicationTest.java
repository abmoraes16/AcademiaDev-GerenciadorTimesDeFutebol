package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DesafioMeuTimeApplicationTest {
    DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

    public DesafioMeuTimeApplicationTest(){
        this.incluirTime();
        this.incluirJogador();
        this.definirCapitao();
    }

    void incluirTime() {
        desafio.incluirTime((long)1, "Corinthians", LocalDate.now(), "branco", "preto");
        desafio.incluirTime((long)2, "São Paulo", LocalDate.now(), "vermelho", "branco");
        desafio.incluirTime((long)3, "Santos", LocalDate.now(), "branco", "preto");
        desafio.incluirTime((long)4, "Palmeiras", LocalDate.now(), "verde", "branco");
    }

    void incluirJogador() {
        desafio.incluirJogador((long)1,(long) 1, "Bruno", LocalDate.parse("1981-01-01"), 10, BigDecimal.valueOf(25.000));
        desafio.incluirJogador((long)2, (long)1, "Julio", LocalDate.parse("1980-10-16"), 8, BigDecimal.valueOf(20.000));
        desafio.incluirJogador((long)3, (long)1, "Thiago",LocalDate.parse("1980-02-10"), 7, BigDecimal.valueOf(18.000));
        desafio.incluirJogador((long)4, (long)1, "Pedro", LocalDate.parse("1988-04-11"), 5, BigDecimal.valueOf(10.000));
        desafio.incluirJogador((long)5, (long)1, "Richard", LocalDate.parse("1988-11-01"), 8, BigDecimal.valueOf(19.000));
        desafio.incluirJogador((long)6, (long)2, "Mario", LocalDate.parse("1980-10-15"), 7, BigDecimal.valueOf(20.000));
        desafio.incluirJogador((long)7, (long)2, "Edson", LocalDate.parse("1987-10-16"), 5, BigDecimal.valueOf(15.000));
        desafio.incluirJogador((long)8, (long)2, "Junior", LocalDate.parse("1982-08-08"), 10, BigDecimal.valueOf(20.000));
        desafio.incluirJogador((long)9, (long)3, "Denis", LocalDate.parse("1980-12-12"), 5, BigDecimal.valueOf(14.000));
        desafio.incluirJogador((long)10, (long)3, "Maicon", LocalDate.parse("1984-04-17"), 9, BigDecimal.valueOf(15.000));
        desafio.incluirJogador((long)11, (long)3, "Luis", LocalDate.parse("1985-10-08"), 9, BigDecimal.valueOf(20.000));
        desafio.incluirJogador((long)12, (long)3, "Alan", LocalDate.parse("1984-09-01"), 8, BigDecimal.valueOf(20.000));
        desafio.incluirJogador((long)13, (long)4, "Fabio", LocalDate.parse("1980-03-02"), 10, BigDecimal.valueOf(24.000));
        desafio.incluirJogador((long)14, (long)4, "Edmundo", LocalDate.parse("1984-03-16"), 10, BigDecimal.valueOf(23.000));
        desafio.incluirJogador((long)15, (long)4, "Diego", LocalDate.parse("1985-12-08"), 9, BigDecimal.valueOf(22.000));
    }

    void definirCapitao() {
        desafio.definirCapitao((long)1);
        desafio.definirCapitao((long) 7);
        desafio.definirCapitao((long)11);
        desafio.definirCapitao((long)14);
        desafio.definirCapitao((long)2);
    }

    @org.junit.jupiter.api.Test
    void buscarCapitaoDoTime() {
        assertEquals(2, desafio.buscarCapitaoDoTime((long)1));
        assertEquals(7, desafio.buscarCapitaoDoTime((long)2));
        assertEquals(11, desafio.buscarCapitaoDoTime((long)3));
        assertEquals(14, desafio.buscarCapitaoDoTime((long)4));
    }

    @org.junit.jupiter.api.Test
    void buscarNomeJogador() {
        assertEquals("Bruno", desafio.buscarNomeJogador((long)1));
        assertEquals("Mario", desafio.buscarNomeJogador((long)6));
        assertEquals("Denis", desafio.buscarNomeJogador((long)9));
        assertEquals("Diego", desafio.buscarNomeJogador((long)15));
    }

    @org.junit.jupiter.api.Test
    void buscarNomeTime() {
        assertEquals("Corinthians", desafio.buscarNomeTime((long)1));
        assertEquals("São Paulo", desafio.buscarNomeTime((long)2));
        assertEquals("Santos", desafio.buscarNomeTime((long)3));
        assertEquals("Palmeiras", desafio.buscarNomeTime((long)4));
    }

    @org.junit.jupiter.api.Test
    void buscarJogadoresDoTime() {
        assertEquals(5, desafio.buscarJogadoresDoTime((long)1).size());
        assertEquals(3, desafio.buscarJogadoresDoTime((long)2).size());
        assertEquals(4, desafio.buscarJogadoresDoTime((long)3).size());
        assertEquals(3, desafio.buscarJogadoresDoTime((long)4).size());
    }

    @org.junit.jupiter.api.Test
    void buscarMelhorJogadorDoTime() {
        assertEquals(1, desafio.buscarMelhorJogadorDoTime((long)1));
        assertEquals(8, desafio.buscarMelhorJogadorDoTime((long)2));
        assertEquals(10, desafio.buscarMelhorJogadorDoTime((long)3));
        assertEquals(13, desafio.buscarMelhorJogadorDoTime((long)4));
    }

    @org.junit.jupiter.api.Test
    void buscarJogadorMaisVelho() {
        assertEquals(3, desafio.buscarJogadorMaisVelho((long)1));
    }

    @org.junit.jupiter.api.Test
    void buscarTimes() {
        assertEquals(4, desafio.buscarTimes().size());
    }

    @org.junit.jupiter.api.Test
    void buscarJogadorMaiorSalario() {
        assertEquals(1, desafio.buscarJogadorMaiorSalario((long)1));
    }

    @org.junit.jupiter.api.Test
    void buscarSalarioDoJogador() {
        assertEquals(BigDecimal.valueOf(25.000), desafio.buscarSalarioDoJogador((long)1));
    }

    @org.junit.jupiter.api.Test
    void buscarTopJogadores() {
        List<Long> id = new ArrayList<Long>();
        id = desafio.buscarTopJogadores(3);
        assertEquals(1,id.get(0));
        assertEquals(8,id.get(1));
        assertEquals(13,id.get(2));
    }

    @org.junit.jupiter.api.Test
    void buscarCorCamisaTimeDeFora() {
        assertEquals("vermelho", desafio.buscarCorCamisaTimeDeFora((long)1, (long)2));
        assertEquals("preto", desafio.buscarCorCamisaTimeDeFora((long)1, (long)3));
    }
}