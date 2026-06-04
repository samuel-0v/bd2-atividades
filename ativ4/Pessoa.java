/*
create table pessoa (
 cpf char(11) primary key,
 nome varchar(50) not null,
 estadocivil char(10) check (estadocivil in
 ('solteiro','casado','viuvo','divorciado')),
 nascimento date,
 altura_cm int,
 peso_kg decimal(5,2)
); 
*/

public class Pessoa {
    private String cpf;
    private String nome;
    private String estadocivil;
    private java.sql.Date nascimento;
    private int altura_cm;
    private double peso_kg;

    public Pessoa( String cpf, String nome, String estadocivil, java.sql.Date nascimento, int altura_cm, double peso_kg) {
        this.cpf = cpf;
        this.nome = nome;
        this.estadocivil = estadocivil;
        this.nascimento = nascimento;
        this.altura_cm = altura_cm;
        this.peso_kg = peso_kg;
    }

    // Getters e Setters (opcional)
    public String getCpf() {
        return this.cpf;     
    }

    public void setCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 caracteres.");
        }
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getEstadocivil() {
        return this.estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        if (!estadocivil.equals("solteiro") && !estadocivil.equals("casado") &&
            !estadocivil.equals("viuvo") && !estadocivil.equals("divorciado")) {
            throw new IllegalArgumentException("Estado civil inválido.");
        }
        this.estadocivil = estadocivil;
    }

    public java.sql.Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(java.sql.Date nascimento) {
        if (nascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula.");
        }else if (nascimento.after(new java.sql.Date(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro.");
        }
        this.nascimento = nascimento;
    }

    public int getAltura_cm() {
        return this.altura_cm;
    }

    public void setAltura_cm(int altura_cm) {
        if (altura_cm <= 0) {
            throw new IllegalArgumentException("Altura deve ser um valor positivo.");
        }
        this.altura_cm = altura_cm;
    }

    public double getPeso_kg() {
        return this.peso_kg;
    }

    public void setPeso_kg(double peso_kg) {
        if (peso_kg <= 0) {
            throw new IllegalArgumentException("Peso deve ser um valor positivo.");
        }
        this.peso_kg = peso_kg;
    }

    // Imprimir os dados da pessoa
    public void imprimirDados() {
        System.out.printf(
            "CPF: %s | Nome: %s | Estado Civil: %s | Nascimento: %s | Altura: %d cm | Peso: %.2f kg%n",
            this.cpf,
            this.nome,
            this.estadocivil,
            this.nascimento,
            this.altura_cm,
            this.peso_kg
        );
    }

    public String toString() {
        return String.format(
            this.cpf + " | " +
            this.nome + " | " +
            this.estadocivil + " | " +
            this.nascimento + " | " +
            this.altura_cm + " cm | " +
            this.peso_kg + " kg" 
        );
    }
}
