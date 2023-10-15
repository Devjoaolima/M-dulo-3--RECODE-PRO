public class Cliente {

    private int id;
    private String cpf, nome, email, telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void RealizarCadastro(int id, String cpf, String nome, String email, String telefone) {
        // Lógica para cadastrar o cliente com os dados fornecidos
        this.setId(id);
        this.setCpf(cpf);
        this.setNome(nome);
        this.setEmail(email);
        this.setTelefone(telefone);
    }

    public void AtualizarCadastro(String nome, String email, String telefone) {
        // Lógica para atualizar os dados do cliente, mantendo o ID e CPF
        this.setNome(nome);
        this.setEmail(email);
        this.setTelefone(telefone);
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "id=" + id +
               ", cpf='" + cpf + '\'' +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", telefone='" + telefone + '\'' +
               '}';
    }

    int getIdade() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
