

import exceptions.CrudException;
import model.Aluno;
import model.Pessoa;
import service.CrudService;
import util.Menu;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        CrudService crudService = new CrudService(sc);
        boolean continua = true;
        do {
            try {
                Menu.menuPrincipal();
                int opcaoPrincipal = sc.nextInt();
                switch (opcaoPrincipal) {
                    case 1:
                        crudService.cadastrar();
                        break;
                    case 2:
                        Menu.menuExibicao();
                        int opcaoExibicao = sc.nextInt();
                        crudService.mostrarCadastros(opcaoExibicao);
                        break;
                    case 3:
                        System.out.println("Escolha o grupo referente ao cadastro a ser alterado:\n1 - Pessoas.\n2 - Alunos.");
                        int grupo = sc.nextInt();
                        if (grupo != 1 && grupo != 2) {
                            System.out.println("Opção de grupo inválida!");
                        }
                        crudService.mostrarCadastros(grupo);
                        switch (grupo) {
                            case 1:
                                Pessoa pessoa = crudService.buscarPessoaPorId();
                                crudService.alterarPessoa(pessoa);
                                break;
                            case 2:
                                Aluno aluno = crudService.buscarAlunoPorId();
                                crudService.alterarAluno(aluno);
                                break;
                        }
                        break;
                    case 4:
                        crudService.mostrarCadastros(3);
                        crudService.excluirCadastroPorId();
                        break;
                    case 0:
                        continua = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (CrudException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Tente novamente");
                sc.nextLine();
            } finally {
                Thread.sleep(500);
            }
        } while (continua);
    }
}