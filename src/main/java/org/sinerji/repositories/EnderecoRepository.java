package org.sinerji.repositories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sinerji.entities.Endereco;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public Endereco buscaPorId(int id) {
        return em.find(Endereco.class, id);
    }

    public Endereco guardar(Endereco endereco) {
        return em.merge(endereco);
    }

    public void remover(Endereco endereco) {
        endereco = buscaPorId(endereco.getId());
        em.remove(endereco);
    }
}
