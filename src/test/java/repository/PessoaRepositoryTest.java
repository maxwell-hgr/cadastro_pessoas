package repository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    private PessoaRepository repository;

    @Before
    public void setUp() {
        repository = new PessoaRepository();
        repository.setEntityManager(em);
    }

    @Test
    public void buscaPorId_DeveRetornarPessoa() {
        Pessoa pessoaMock = new Pessoa(1, "João", LocalDate.now(), EnumGenero.M, new ArrayList<>());
        when(em.find(Pessoa.class, 1)).thenReturn(pessoaMock);

        Pessoa resultado = repository.buscaPorId(1);

        assertEquals(pessoaMock, resultado);
    }

    @Test
    public void guardar_DeveRetornarPessoaSalva() {
        Pessoa pessoaMock = new Pessoa(1, "Maria", LocalDate.now(), EnumGenero.F, new ArrayList<>());
        when(em.merge(pessoaMock)).thenReturn(pessoaMock);

        Pessoa resultado = repository.guardar(pessoaMock);

        assertEquals(pessoaMock, resultado);
    }

    @Test
    public void remover_DeveChamarDelete() {
        Pessoa pessoaMock = new Pessoa(1, "José", LocalDate.now(), EnumGenero.M, new ArrayList<>());
        when(em.find(Pessoa.class, 1)).thenReturn(pessoaMock);

        repository.remover(pessoaMock);

        verify(em).remove(pessoaMock);
    }

    @Test
    public void todos_DeveRetornarListaDePessoas() {
        TypedQuery<Pessoa> queryMock = mock(TypedQuery.class);
        List<Pessoa> pessoasMock = Arrays.asList(
                new Pessoa(1, "João", LocalDate.now(), EnumGenero.M, new ArrayList<>()),
                new Pessoa(2, "Maria", LocalDate.now(), EnumGenero.F, new ArrayList<>())
        );

        when(em.createQuery("from Pessoa", Pessoa.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(pessoasMock);

        List<Pessoa> resultado = repository.todos();

        assertEquals(2, resultado.size());
    }

    @Test
    public void pesquisar_DeveFiltrarPorNome() {
        TypedQuery<Pessoa> queryMock = mock(TypedQuery.class);
        List<Pessoa> pessoasMock = Arrays.asList(
                new Pessoa(1, "João Silva", LocalDate.now(), EnumGenero.M, new ArrayList<>())
        );

        when(em.createQuery("FROM Pessoa where LOWER(nome) like LOWER(:nome)", Pessoa.class))
                .thenReturn(queryMock);
        when(queryMock.setParameter("nome", "João%")).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(pessoasMock);

        List<Pessoa> resultado = repository.pesquisar("João");

        assertEquals(1, resultado.size());
        assertEquals("João Silva", resultado.get(0).getNome());
    }
}
