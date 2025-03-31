package org.sinerji.services;

import lombok.Setter;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.PessoaRepository;

import javax.inject.Inject;
import java.io.Serializable;

@Setter
public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaRepository pessoaRepository;

    public void salvar(Pessoa pessoa) {
        pessoaRepository.guardar(pessoa);
    }

    public void remover(Pessoa pessoa) {
        pessoaRepository.remover(pessoa);
    }
}
