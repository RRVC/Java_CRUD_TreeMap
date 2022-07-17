package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Aluno extends Pessoa {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private Double notaFinal;

    public Aluno(String nome, String telefone, String nascimento, LocalDateTime regDate, Double notaFinal) {
        super(nome, telefone, nascimento, regDate);
        this.notaFinal = notaFinal;
    }
    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return "id - " + getId() +
                ", nome: " + getNome() +
                ", telefone: " + getTelefone() +
                ", nascimento: " + getNascimento() +
                ", data de registro: " + dtf.format(getRegDate()) +
                ", data de alteração: " + dtf.format(getEditDate()) +
                ", notaFinal: " + getNotaFinal() +
                '}';
    }
}