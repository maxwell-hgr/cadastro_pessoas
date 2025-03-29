package org.sinerji.controller;

import lombok.Getter;
import lombok.Setter;
import org.sinerji.entities.Endereco;
import org.sinerji.entities.EnumEstado;
import org.sinerji.entities.EnumGenero;
import org.sinerji.entities.Pessoa;
import org.sinerji.repositories.PessoaRepository;
import org.sinerji.util.FacesMessages;

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
    private FacesMessages facesMessages;

    private Pessoa pessoa = new Pessoa();

    private EnumGenero[] generos;
    private EnumEstado[] estados;
    private List<Pessoa> todos;
    private String query;

    public void buscarTodos(){
        todos = pessoaRepository.todos();
    }

    public void pesquisar(){
        todos = pessoaRepository.pesquisar(query);

        if(todos.isEmpty()){
            facesMessages.info("Sua consulta não retornou registros");
        }
    }
}
