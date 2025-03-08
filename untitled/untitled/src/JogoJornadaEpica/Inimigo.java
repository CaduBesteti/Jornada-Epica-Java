import java.util.HashMap;
import java.util.Map;

public class Inimigo {
    private static final Map<String, Integer> DIFICULDADE = new HashMap<>();
    static {
        DIFICULDADE.put("East Blue", 1);
        DIFICULDADE.put("Alabasta", 2);
        DIFICULDADE.put("Skypiea", 3);
        DIFICULDADE.put("Water 7", 4);
        DIFICULDADE.put("Thriller Bark", 5);
        DIFICULDADE.put("Marineford", 6);
        DIFICULDADE.put("Dressrosa", 7);
        DIFICULDADE.put("Wano", 8);
    }

    private String nome;
    private int vida;
    private int dano;
    private int experiencia;
    private String habilidade;
    private String regiao;

    public Inimigo(String regiao, String nome, int nivel) {
        this.regiao = regiao;
        this.nome = nome;
        int multiplicador = DIFICULDADE.get(regiao);
        
        this.vida = 50 * multiplicador + (nivel * 20);
        this.dano = 10 * multiplicador + (nivel * 5);
        this.experiencia = 20 * multiplicador + (nivel * 10);
        
        this.habilidade = definirHabilidade(nome);
    }

    private String definirHabilidade(String nome) {
        switch(nome) {
            case "Arlong": return "Dente de Tubarão";
            case "Crocodile": return "Areia Assassina";
            case "Enel": return "Raio de 200 Milhões de Volts";
            case "Lucci": return "Rokuougan";
            case "Moria": return "Cortador de Sombras";
            case "Doflamingo": return "Linha de Corte";
            case "Big Mom": return "Raio de Napoleon";
            case "Kaido": return "Bafo do Dragão";
            default: return "Ataque Básico";
        }
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
    public String getHabilidade() { return habilidade; }
    public void setHabilidade(String habilidade) { this.habilidade = habilidade; }
    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }
}