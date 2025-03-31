package org.sinerji.repositories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sinerji.entities.Endereco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Stateless
public class EnderecoRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    public void remover(Endereco endereco) {
        Endereco enderecoGerenciado = em.find(Endereco.class, endereco.getId());

        if (enderecoGerenciado.getPessoa() != null) {
            enderecoGerenciado.getPessoa().getEnderecos().remove(enderecoGerenciado);
        }

        em.remove(enderecoGerenciado);
        em.flush();
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
