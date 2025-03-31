package service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private List<Endereco> enderecos;

    @Before
    public void setUp() {
        enderecos = new ArrayList<>();
        enderecos.add(new Endereco(1, EnumEstado.AC, "Rio Branco", "Rua 2", 123, "123123", null));

        pessoa = new Pessoa(1, "João", LocalDate.now(), EnumGenero.M, enderecos);

        enderecos.forEach(e -> e.setPessoa(pessoa));
    }

    @Test
    public void testSalvar() {
        pessoaService.salvar(pessoa);

        verify(pessoaRepository, times(1)).guardar(pessoa);
    }

    @Test
    public void testRemover() {
        doNothing().when(pessoaRepository).remover(any(Pessoa.class));

        pessoaService.remover(pessoa);

        verify(pessoaRepository, times(1)).remover(pessoa);
    }
}