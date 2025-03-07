public class Inimigo {
    private String nome;
    private int vida;
    private int ataque;

    public Inimigo(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void atacar(Personagem personagem) {
        System.out.println(nome + " ataca " + personagem.getNome() + " causando " + ataque + " de dano!");
        personagem.setVida(personagem.getVida() - ataque);
    }
}