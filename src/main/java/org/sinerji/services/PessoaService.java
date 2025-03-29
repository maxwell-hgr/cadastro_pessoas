package org.sinerji.services;

import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.PessoaRepository;
import org.sinerji.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class PessoaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaRepository pessoaRepository;

    @Transacional
    public void salvar(Pessoa pessoa) {
        pessoaRepository.guardar(pessoa);
    }

    @Transacional
    public void remover(Pessoa pessoa) {
        pessoaRepository.remover(pessoa);
    }
}
