package org.sinerji.services;

import org.sinerji.entities.Endereco;
import org.sinerji.repositories.EnderecoRepository;
import org.sinerji.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class EnderecoService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EnderecoRepository enderecoRepository;

    @Transacional
    public void salvar(Endereco endereco) {
        enderecoRepository.guardar(endereco);
    }

    @Transacional
    public void remover(Endereco endereco) {
        enderecoRepository.remover(endereco);
    }
}
