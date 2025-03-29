package org.sinerji.repositories;

import org.sinerji.entities.Pessoa;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class PessoaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public PessoaRepository(EntityManager em) {
        this.em = em;
    }

    public Pessoa buscaPorId(int id) {
        return em.find(Pessoa.class, id);
    }

    public Pessoa guardar(Pessoa pessoa) {
        em.merge(pessoa);
        return pessoa;
    }

    public void remover(Pessoa pessoa) {
        pessoa = buscaPorId(pessoa.getId());
        em.remove(pessoa);
    }
}
