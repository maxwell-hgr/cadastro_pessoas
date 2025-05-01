package org.sinerji.controller;

import lombok.Getter;
import lombok.Setter;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.entities.EnumGenero;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.EnderecoRepository;
import org.sinerji.repositories.PessoaRepository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaRepository pessoaRepository;

    @Inject
    private EnderecoRepository enderecoRepository;

    private Pessoa pessoa;
    private Endereco endereco;

    private List<Endereco> enderecos;

    private List<Pessoa> todos;
    private String query;

    public void salvarPessoa() {
        pessoaRepository.guardar(pessoa);

        buscarTodos();
    }

    public void salvarEndereco() {
        pessoa = pessoaRepository.buscaPorId(pessoa.getId());
        pessoa.addEndereco(endereco);

        pessoaRepository.guardar(pessoa);

        buscarTodos();
    }

    public void excluirPessoa(Pessoa pessoa) {
        pessoaRepository.remover(pessoa);

        buscarTodos();

        this.pessoa = null;
        this.endereco = null;

        FacesMessage facesMessage = new FacesMessage("Registro excluido com sucesso!");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void excluirEndereco(Endereco endereco) {

        enderecoRepository.remover(endereco);

        carregarEnderecos(pessoa);
    }

    public void selecionarPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void carregarEnderecos(Pessoa pessoa) {
        this.pessoa = pessoaRepository.buscaPorId(pessoa.getId());
        enderecos = this.pessoa.getEnderecos();
    }

    public void inicializarPessoa() {
        pessoa = new Pessoa();
    }

    public void inicializarEndereco() {
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
