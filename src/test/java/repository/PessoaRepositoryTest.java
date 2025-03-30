package repository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.entities.EnumGenero;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.PessoaRepository;

@RunWith(MockitoJUnitRunner.class)
public class PessoaRepositoryTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private PessoaRepository repository;

    private List<Pessoa> pessoasTeste;
    private Pessoa pessoa;

    @Before
    public void setUp() {
        pessoa = new Pessoa(1, "João", LocalDate.now(), EnumGenero.M,
                new Endereco(1, EnumEstado.AC, "Rio Branco", "Rua 2", 123, "123123"));

        pessoasTeste = Arrays.asList(
                new Pessoa(1, "João Silva", LocalDate.now(), EnumGenero.M,
                        new Endereco(1, EnumEstado.SP, "São Paulo", "Av. Paulista", 1000, "01310-000")),
                new Pessoa(2, "Maria Santos", LocalDate.now(), EnumGenero.F,
                        new Endereco(2, EnumEstado.RJ, "Rio de Janeiro", "Rua Copacabana", 200, "22020-001")),
                new Pessoa(3, "José Oliveira", LocalDate.now(), EnumGenero.M,
                        new Endereco(3, EnumEstado.MG, "Belo Horizonte", "Av. Afonso Pena", 300, "30130-000"))
        );
    }

    @Test
    public void buscaPorId_DeveRetornarPessoa() {
        when(em.find(Pessoa.class, 1)).thenReturn(pessoa);

        Pessoa result = repository.buscaPorId(1);

        assertEquals(pessoa, result);
    }

    @Test
    public void guardar_DeveRetornarPessoaSalva() {
        when(em.merge(pessoa)).thenReturn(pessoa);

        Pessoa result = repository.guardar(pessoa);

        assertEquals(pessoa, result);
    }

    @Test
    public void remover_DeveChamarDelete() {
        when(em.find(Pessoa.class, 1)).thenReturn(pessoa);

        repository.remover(pessoa);

        verify(em).remove(pessoa);
    }

    @Test
    public void todos_DeveRetornarTodasPessoas() {
        TypedQuery<Pessoa> query = mock(TypedQuery.class);
        when(em.createQuery("from Pessoa", Pessoa.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(pessoasTeste);

        List<Pessoa> resultado = repository.todos();

        assertEquals(3, resultado.size());
        assertTrue(resultado.stream().anyMatch(p -> p.getNome().equals("João Silva")));
        assertTrue(resultado.stream().anyMatch(p -> p.getNome().equals("Maria Santos")));
        assertTrue(resultado.stream().anyMatch(p -> p.getNome().equals("José Oliveira")));
    }

    @Test
    public void pesquisar_DeveRetornarPessoasComNomeQueComecaComJo() {
        TypedQuery<Pessoa> query = mock(TypedQuery.class);
        when(em.createQuery("from Pessoa where LOWER(nome) like LOWER(:nome)", Pessoa.class))
                .thenReturn(query);
        when(query.setParameter("nome", "Jo%")).thenReturn(query);
        when(query.getResultList()).thenReturn(
                pessoasTeste.stream()
                        .filter(p -> p.getNome().startsWith("Jo"))
                        .collect(Collectors.toList())
        );

        List<Pessoa> resultado = repository.pesquisar("Jo");

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(p -> p.getNome().startsWith("Jo")));
    }

    @Test
    public void pesquisar_DeveRetornarListaVaziaQuandoNaoEncontrar() {
        TypedQuery<Pessoa> query = mock(TypedQuery.class);
        when(em.createQuery("from Pessoa where LOWER(nome) like LOWER(:nome)", Pessoa.class))
                .thenReturn(query);
        when(query.setParameter("nome", "Xi%")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        List<Pessoa> resultado = repository.pesquisar("Xi");

        assertTrue(resultado.isEmpty());
    }
}
