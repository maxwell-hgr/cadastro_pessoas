package org.sinerji;

import org.sinerji.entities.Endereco;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.EnderecoRepository;
import org.sinerji.repositories.PessoaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SinerjiPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        PessoaRepository pessoaRepository = new PessoaRepository(em);
        EnderecoRepository enderecoRepository = new EnderecoRepository(em);

        Endereco endereco = new Endereco();
        endereco.setCep("72510-418");
        endereco = enderecoRepository.guardar(endereco);

        Endereco endereco2 = new Endereco();
        endereco2.setCep("72510-419");
        endereco2 = enderecoRepository.guardar(endereco2);

        Pessoa pessoa = new Pessoa();
        pessoa.setDataNascimento(LocalDate.now());
        pessoa.setNome("Sinerji");
        pessoa.setEndereco(endereco);

        pessoa = pessoaRepository.guardar(pessoa);

        System.out.println("ID da pessoa: " + pessoa.getId());

        pessoa.setEndereco(endereco2);
        pessoaRepository.guardar(pessoa);

        pessoa.setEndereco(endereco);
        pessoaRepository.guardar(pessoa);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}