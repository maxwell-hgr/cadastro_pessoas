package org.sinerji.controller;

import lombok.Getter;
import lombok.Setter;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.entities.EnumGenero;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.EnderecoRepository;
import org.sinerji.repositories.PessoaRepository;
import org.sinerji.services.PessoaService;
import org.sinerji.util.FacesMessages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/*
* ao anotar com named, fazemos com que a instancia do bean fique disponível no expression language do jsf (#{})
* a anotação de escopo define o tempo de vida dessa instância
* */


@Setter
@Getter
@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaRepository pessoaRepository;

    @Inject
    private PessoaService pessoaService;

    @Inject
    private EnderecoRepository enderecoRepository;

    @Inject
    private FacesMessages facesMessages;

    private Pessoa pessoa;
    private Endereco endereco;

    private List<Pessoa> todos;
    private String query;

    public void salvar() {
            // evitando que um endereço existente seja salvo no banco de dados como outro registro
            Endereco existente = enderecoRepository.buscarEnderecoExistente(endereco);
            pessoa.setEndereco(existente != null ? existente : endereco);

            pessoaService.salvar(pessoa);

            buscarTodos();
    }

    public void excluir() {
        pessoaService.remover(pessoa);
        pessoa = null;

        buscarTodos();

        facesMessages.info("Registro excluido com sucesso!");
    }


    public void inicializarInstancias() {
        pessoa = new Pessoa();
        endereco = new Endereco();
    }

    public void buscarTodos() {
        todos = pessoaRepository.todos();
    }

    public void pesquisar() {
        todos = pessoaRepository.pesquisar(query);
    }

    public EnumGenero[] getGeneros() {
        return EnumGenero.values();
    }

    public EnumEstado[] getEstados() {
        return EnumEstado.values();
    }
}
