package org.sinerji.repositories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sinerji.entities.Pessoa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PessoaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

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

    public List<Pessoa> todos(){
        return em.createQuery("from Pessoa", Pessoa.class).getResultList();
    }

    public List<Pessoa> pesquisar(String query){
        String jpql = "from Pessoa where LOWER(nome) like LOWER(:nome)";
        TypedQuery<Pessoa> consulta = em.createQuery(jpql, Pessoa.class);
        consulta.setParameter("nome", query + "%");
        return consulta.getResultList();
    }
}
