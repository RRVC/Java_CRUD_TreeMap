/*
package service;

import model.Pessoa;
import repository.RepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class PessoaService {
    private Scanner sc = new Scanner(System.in);
    private RepositoryImpl<Integer, Pessoa> repository = new RepositoryImpl<>();

    public PessoaService(Scanner sc) {
        this.sc = sc;
    }

    public void cadastrarPessoa() {
        sc.nextLine();
        System.out.println("Digite o nome do cadastro:");
        String nome = sc.nextLine();
        System.out.println("Digite a data de nascimento referente ao cadastro:");
        String nascimento = sc.nextLine();
        System.out.println("Digite o cpf referente ao cadastro");
        String cpf = sc.nextLine();

        List<Pessoa> pessoasList = this.repository.buscarTodos();

        for (Pessoa pessoaFor : pessoasList) {
            if (pessoaFor.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado!");
            }
        }
        Pessoa pessoa = new Pessoa(nome, nascimento, cpf);
        repository.salvar(pessoa.getId(), pessoa);
    }
}*/