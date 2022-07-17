package service;

import exceptions.CrudException;
import model.Aluno;
import model.Pessoa;
import repository.RepositoryImpl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class CrudService {
    private Scanner sc;
    private RepositoryImpl<Integer, Pessoa> repositoryPessoas = new RepositoryImpl<>();
    private RepositoryImpl<Integer, Aluno> repositoryAlunos = new RepositoryImpl<>();

    public CrudService(Scanner sc) {
        this.sc = sc;
        Pessoa pessoa = new Pessoa("João", "988776655", "01-01-1990", LocalDateTime.now());
        Pessoa pessoa1 = new Pessoa("Douglas", "977665533", "15-08-1970", LocalDateTime.now());
        Aluno aluno = new Aluno("Pedro", "988776655", "01-01-1800", LocalDateTime.now(), 10.0);
        Aluno aluno1 = new Aluno("Felipe", "987654321", "21-08-1999", LocalDateTime.now(), 8.0);
        pessoa.setEditDate(LocalDateTime.now());
        pessoa1.setEditDate(LocalDateTime.now());
        aluno.setEditDate(LocalDateTime.now());
        aluno1.setEditDate(LocalDateTime.now());
        repositoryPessoas.salvar(pessoa.getId(), pessoa);
        repositoryPessoas.salvar(pessoa1.getId(), pessoa1);
        repositoryAlunos.salvar(aluno.getId(), aluno);
        repositoryAlunos.salvar(aluno1.getId(), aluno1);
    }

    public void cadastrar() {
        Double notaFinal = null;
        sc.nextLine();
        System.out.println("Digite o nome do cadastro:");
        String nome = sc.nextLine();
        System.out.println("Digite um telefone de cadastro:");
        String telefone = sc.nextLine();
        System.out.println("Digite a data de nascimento do cadastro:");
        String nascimento = sc.nextLine();
        System.out.println("Entre com a nota final do aluno ou deixe em branco para cadastrar como pessoa.");
        String notaFinalStr = sc.nextLine();
        if (!notaFinalStr.isEmpty()) {
            while (true) {
                try {
                    notaFinal = Double.valueOf(notaFinalStr.replace(",", "."));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Valor inserido inválido!");
                }
                System.out.println("Entre com a nota final do aluno ou deixe em branco para cadastrar como pessoa.");
                notaFinalStr = sc.nextLine();
                if (notaFinalStr.isEmpty()) {
                    break;
                }
            }
        }

        if (notaFinal != null) {
            Aluno aluno = new Aluno(nome, telefone, nascimento, LocalDateTime.now(), notaFinal);
            aluno.setEditDate(LocalDateTime.now());
            repositoryAlunos.salvar(aluno.getId(), aluno);
        } else {
            Pessoa pessoa = new Pessoa(nome, telefone, nascimento, LocalDateTime.now());
            pessoa.setEditDate(LocalDateTime.now());
            repositoryPessoas.salvar(pessoa.getId(), pessoa);
        }
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void mostrarCadastros(int opcao) {
        List<Pessoa> listarPessoas = this.repositoryPessoas.buscarTodos();
        List<Aluno> listarAlunos = this.repositoryAlunos.buscarTodos();
        switch (opcao) {
            case 1:
                System.out.println("Pessoas:");
                listarPessoas.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Alunos:");
                listarAlunos.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Pessoas:");
                listarPessoas.forEach(System.out::println);
                System.out.println("Alunos:");
                listarAlunos.forEach(System.out::println);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválido!");
        }
    }

    public void excluirCadastroPorId() {
        System.out.println("\nDe qual grupo deseja excluir?\n1 - Pessoas.\n2 - Alunos");
        int grupo = sc.nextInt();
        if (grupo != 1 && grupo != 2) {
            System.out.println("Opção de grupo inválida!");
        } else {
            System.out.println("\nEntre com o ID referente ao cadastro a ser excluido");
            int id = sc.nextInt();
            if (grupo == 1) {
                while (true) {
                    if (!repositoryPessoas.exists(id)) {
                        System.out.println("ID inválido, tente novamente ou digite 0 para sair:");
                        id = sc.nextInt();
                        if (id == 0) {
                            break;
                        }
                    } else {
                        repositoryPessoas.excluir(id);
                        System.out.println("Cadastro excluido com sucesso!");
                        break;
                    }
                }
            } else {
                repositoryAlunos.excluir(id);
            }
        }
    }

    public Pessoa buscarPessoaPorId() {
        System.out.println("Entre com o ID referente ao cadastro a ser alterado:");
        int id = sc.nextInt();
        if (!repositoryPessoas.exists(id)) {
            throw new CrudException("ID inválido");
        }
        ;
        return repositoryPessoas.buscarPorId(id);
    }

    public Aluno buscarAlunoPorId() {
        System.out.println("Entre com o ID referente ao cadastro a ser alterado:");
        int id = sc.nextInt();
        if (!repositoryAlunos.exists(id)) {
            throw new CrudException("ID inválido");
        }
        ;
        return repositoryAlunos.buscarPorId(id);
    }

    public void alterarPessoa(Pessoa pessoa) {
        System.out.println(
                "Entre com a opção que deseja alterar:\n" +
                        "1. Nome (Atual:" + pessoa.getNome() + ").\n" +
                        "2. Telefone (Atual: " + pessoa.getTelefone() + ").\n" +
                        "3. Nascimento (Atual: " + pessoa.getNascimento() + ")."
        );
        int opcaoAlteracao = sc.nextInt();
        String nome;
        String telefone;
        String nascimento;
        sc.nextLine();
        switch (opcaoAlteracao) {
            case 1:
                System.out.println("Entre com o novo nome:");
                nome = sc.nextLine();
                pessoa.setNome(nome);
                break;
            case 2:
                System.out.println("Entre com o novo numero de telefone:");
                telefone = sc.nextLine();
                pessoa.setTelefone(telefone);
                break;
            case 3:
                System.out.println("Entre com a nova data de nascimento:");
                nascimento = sc.nextLine();
                pessoa.setNascimento(nascimento);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        System.out.println("Cadastro atualizado com sucesso!");
        pessoa.setEditDate(LocalDateTime.now());
    }

    public void alterarAluno(Aluno aluno) {
        System.out.println(
                "Entre com a opção que deseja alterar:\n" +
                        "1. Nome (Atual: " + aluno.getNome() + ").\n" +
                        "2. Telefone (Atual: " + aluno.getTelefone() + ").\n" +
                        "3. Nascimento (Atual: " + aluno.getNascimento() + ").\n" +
                        "4. Nota (Atual: " + aluno.getNotaFinal() + ")."
        );
        int opcaoAlteracao = sc.nextInt();
        String nome;
        String telefone;
        String nascimento;
        Double notaFinal;
        String notaFinalStr;
        sc.nextLine();
        switch (opcaoAlteracao) {
            case 1:
                System.out.println("Entre com o novo nome:");
                nome = sc.nextLine();
                aluno.setNome(nome);
                break;
            case 2:
                System.out.println("Entre com o novo numero de telefone:");
                telefone = sc.nextLine();
                aluno.setTelefone(telefone);
                break;
            case 3:
                System.out.println("Entre com a nova data de nascimento:");
                nascimento = sc.nextLine();
                aluno.setNascimento(nascimento);
                break;
            case 4:
                notaFinal = null;
                System.out.println("Entre com a nova nota");
                notaFinalStr = sc.nextLine();
                if (!notaFinalStr.isEmpty()) {
                    while (true) {
                        try {
                            notaFinal = Double.valueOf(notaFinalStr.replace(",", "."));
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inserido inválido!");
                        }
                        System.out.println("Entre com a nota");
                        notaFinalStr = sc.nextLine();
                    }
                }
                aluno.setNotaFinal(notaFinal);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        System.out.println("Cadastro atualizado com sucesso!");
        aluno.setEditDate(LocalDateTime.now());
    }

}