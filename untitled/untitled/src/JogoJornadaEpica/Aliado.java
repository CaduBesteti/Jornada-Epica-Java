public class Aliado {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private int dano;

    public Aliado(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.dano = dano;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public void restaurarVida() {
        this.vida = this.vidaMaxima;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }
    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
}