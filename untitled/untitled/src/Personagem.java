public class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int vida;
    private int ataque;

    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = 1;
        this.vida = 100;
        this.ataque = 10;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void mostrarStatus() {
        System.out.println("Personagem: " + nome + " | Classe: " + classe + " | Nível: " + nivel);
        System.out.println("Vida: " + vida + " | Ataque: " + ataque);
    }

    public void atacar(Inimigo inimigo) {
        System.out.println(nome + " ataca " + inimigo.getNome() + " causando " + ataque + " de dano!");
        inimigo.setVida(inimigo.getVida() - ataque);
    }

    public void evoluir() {
        nivel++;
        vida += 20;
        ataque += 5;
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }
}