package repository;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.EnderecoRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoRepositoryTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private EnderecoRepository enderecoRepository;

    @Before
    public void setUp() {
        enderecoRepository = new EnderecoRepository();
        enderecoRepository.setEntityManager(em);
    }

    @Test
    public void deveRemoverEnderecoComPessoa() {

        Endereco endereco = new Endereco();
        endereco.setId(1);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.getEnderecos().add(endereco);

        endereco.setPessoa(pessoa);

        Endereco enderecoGerenciado = new Endereco();
        enderecoGerenciado.setId(1);
        enderecoGerenciado.setPessoa(pessoa);

        when(em.find(Endereco.class, 1)).thenReturn(enderecoGerenciado);

        enderecoRepository.remover(endereco);

        verify(em).find(Endereco.class, 1);
        assertFalse(pessoa.getEnderecos().contains(enderecoGerenciado));
        verify(em).remove(enderecoGerenciado);
        verify(em).flush();
    }

    @Test(expected = NullPointerException.class)
    public void deveLancarExcecaoQuandoEnderecoNaoEncontrado() {
        Endereco endereco = new Endereco();
        endereco.setId(999);

        when(em.find(Endereco.class, 999)).thenReturn(null);
        enderecoRepository.remover(endereco);
    }

}
