package service;

import static org.mockito.Mockito.*;

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
import org.sinerji.services.PessoaService;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @Before
    public void setUp() {
        pessoa = new Pessoa(1, "João",  LocalDate.now(), EnumGenero.M ,
                    new Endereco(1, EnumEstado.AC, "Rio Branco", "Rua 2", 123, "123123"));
    }

    @Test
    public void testSalvar() {
        doNothing().when(pessoaRepository).guardar(pessoa);

        pessoaService.salvar(pessoa);

        verify(pessoaRepository, times(1)).guardar(pessoa);
    }

    @Test
    public void testRemover() {
        doNothing().when(pessoaRepository).remover(pessoa);

        pessoaService.remover(pessoa);

        verify(pessoaRepository, times(1)).remover(pessoa);
    }
}
