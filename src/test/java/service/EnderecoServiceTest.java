package service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.repositories.EnderecoRepository;
import org.sinerji.services.EnderecoService;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;

    @Before
    public void setUp() {
        endereco = new Endereco(1, EnumEstado.SP, "São Paulo", "Rua A", 123, "01000-000");
    }

    @Test
    public void testSalvar_Endereco() {
        when(enderecoRepository.buscarEnderecoExistente(endereco)).thenReturn(endereco);

        Endereco result = enderecoService.salvar(endereco);

        assertNotNull(result);
        assertEquals("São Paulo", result.getCidade());
    }
}
