package org.sinerji.repositories;

import org.sinerji.entities.Endereco;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class EnderecoRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    public EnderecoRepository(EntityManager em) {
        this.em = em;
    }

    public Endereco buscaPorId(int id) {
        return em.find(Endereco.class, id);
    }

    public Endereco guardar(Endereco endereco) {
        em.merge(endereco);
        return endereco;
    }

    public void remover(Endereco endereco) {
        endereco = buscaPorId(endereco.getId());
        em.remove(endereco);
    }
}
