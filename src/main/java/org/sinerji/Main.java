package org.sinerji;

import org.sinerji.entities.Endereco;
import org.sinerji.entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SinerjiPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Sinerji");
        pessoa.setDataNascimento(LocalDate.now());
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Logradouro");
        endereco.setCep("72510-418");

        em.persist(pessoa);
        em.persist(endereco);
        em.getTransaction().commit();

        System.out.println(endereco);

        em.close();
        emf.close();
    }
}