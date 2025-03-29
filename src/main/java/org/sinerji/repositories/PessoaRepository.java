package org.sinerji.repositories;

import lombok.NoArgsConstructor;
import org.sinerji.entities.Pessoa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

@NoArgsConstructor
public class PessoaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public PessoaRepository(EntityManager em) {
        this.em = em;
    }

    public Pessoa buscaPorId(int id) {
        return em.find(Pessoa.class, id);
    }

    public Pessoa guardar(Pessoa pessoa) {
        return em.merge(pessoa);
    }

    public void remover(Pessoa pessoa) {
        pessoa = buscaPorId(pessoa.getId());
        em.remove(pessoa);
    }
}
