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
import org.sinerji.repositories.EnderecoRepository;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoRepositoryTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private EnderecoRepository enderecoRepository;

    private List<Endereco> enderecosTeste;

    @Before
    public void setUp() {
        enderecosTeste = Arrays.asList(
                new Endereco(1, EnumEstado.SP, "São Paulo", "Av. Paulista", 1000, "01310-000"),
                new Endereco(2, EnumEstado.RJ, "Rio de Janeiro", "Rua Copacabana", 200, "22020-001"),
                new Endereco(3, EnumEstado.MG, "Belo Horizonte", "Av. Afonso Pena", 300, "30130-000"),
                new Endereco(4, EnumEstado.SP, "Campinas", "Rua Barão de Jaguara", 400, "13015-001")
        );
    }

    @Test
    public void buscaPorId_DeveRetornarEnderecoCorreto() {
        when(em.find(Endereco.class, 2)).thenReturn(enderecosTeste.get(1));

        Endereco resultado = enderecoRepository.buscaPorId(2);

        assertNotNull(resultado);
        assertEquals("Rio de Janeiro", resultado.getCidade());
        assertEquals(EnumEstado.RJ, resultado.getEstado());
    }

    @Test
    public void buscaPorId_DeveRetornarNullParaIdInexistente() {
        when(em.find(Endereco.class, 99)).thenReturn(null);

        Endereco resultado = enderecoRepository.buscaPorId(99);

        assertNull(resultado);
    }

    @Test
    public void buscarEnderecoExistente_DeveRetornarNullQuandoNaoExistir() {
        Endereco enderecoNovo = new Endereco(0, EnumEstado.RS, "Porto Alegre", "Rua Nova", 600, "90000-000");
        TypedQuery<Endereco> query = mock(TypedQuery.class);

        when(em.createQuery(anyString(), eq(Endereco.class))).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenThrow(new NoResultException());

        Endereco resultado = enderecoRepository.buscarEnderecoExistente(enderecoNovo);

        assertNull(resultado);
    }

}