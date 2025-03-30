package org.sinerji.repositories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sinerji.entities.Endereco;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public Endereco buscarEnderecoExistente(Endereco endereco) {
        String jpql = "SELECT e FROM Endereco e WHERE " +
                "e.estado = :estado AND " +
                "e.cidade = :cidade AND " +
                "e.logradouro = :logradouro AND " +
                "e.numero = :numero AND " +
                "e.cep = :cep";

        try {
            return em.createQuery(jpql, Endereco.class)
                    .setParameter("estado", endereco.getEstado())
                    .setParameter("cidade", endereco.getCidade())
                    .setParameter("logradouro", endereco.getLogradouro())
                    .setParameter("numero", endereco.getNumero())
                    .setParameter("cep", endereco.getCep())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
