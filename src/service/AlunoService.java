/*
package service;

import model.Aluno;
import repository.RepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class AlunoService {
    private Scanner sc = new Scanner(System.in);
    private RepositoryImpl<Integer, Aluno> repository = new RepositoryImpl<>();

    public AlunoService(Scanner sc) {
        this.sc = sc;
        Aluno aluno = new Aluno("Vinicius", "01-05-1990", "111", 7.0);
        repository.salvar(aluno.getId(), aluno);

    }

    public void cadastrarAluno() {
        sc.nextLine();
        System.out.println("Digite o nome do aluno:");
        String nome = sc.nextLine();
        System.out.println("Digite a data de nascimento do aluno:");
        String nascimento = sc.nextLine();
        System.out.println("Digite o cpf do aluno");
        String cpf = sc.nextLine();
        System.out.println("Digite a nota do aluno:");
        Double nota = sc.nextDouble();

        List<Aluno> alunoList = this.repository.buscarTodos();

        for (Aluno alunoFor : alunoList) {
            if (alunoFor.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado!");
            }
        }
        Aluno aluno = new Aluno(nome, nascimento, cpf, nota);
        repository.salvar(aluno.getId(), aluno);
    }

    public void mostrarTodosAlunos() {
        repository.buscarTodos().forEach(aluno -> System.out.println(
                "ID: " + aluno.getId()
                + " Nome: " + aluno.getNome()
                + " CPF: " + aluno.getCpf()
                + " Nascimento: " + aluno.getNascimento()
                + " Nota: " + aluno.getNota()
        ));
    }

}*/