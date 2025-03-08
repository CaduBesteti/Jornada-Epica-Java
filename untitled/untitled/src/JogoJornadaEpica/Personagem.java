import java.util.HashMap;
import java.util.Map;

public class Personagem {
    private String nome;
    private String raca;
    private String classe;
    private int vida;
    private int vidaMaxima;
    private int dano;
    private int experiencia;
    private int level;
    private int sorte;
    private Map<String, Integer> equipamentos = new HashMap<>();

    public Personagem(String nome, String raca, String classe, int vidaBase, int danoBase) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;

        Caracteristicas caracteristicas = new Caracteristicas(raca);
        this.vida = vidaBase + caracteristicas.getBonusVida();
        this.vidaMaxima = this.vida;
        this.dano = danoBase + caracteristicas.getBonusAtaque();
        this.sorte = caracteristicas.getBonusSorte();

        this.experiencia = 0;
        this.level = 1;
    }    

    public void equiparItem(String item, int bonus) {
        equipamentos.put(item, bonus);
        System.out.println(item + " equipado! Bônus: +" + bonus);
    }

    public int calcularBerriesGanhos(int baseBerries) {
        return (int)(baseBerries * (1.0 + (sorte + getBonusEquipamentos())/100.0));
    }

    private int getBonusEquipamentos() {
        return equipamentos.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public void restaurarVida() {
        this.vida = vidaMaxima;
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        while (experiencia >= 100 && level < 10) {
            experiencia -= 100;
            level++;
            int aumentoVida = 20 + (sorte / 2);
            vidaMaxima += aumentoVida;
            vida = vidaMaxima;
            dano += 10 + (sorte / 2);
            System.out.println(nome + " subiu para o nível " + level + "! Vida +" + aumentoVida + ", Dano +" + (10 + (sorte / 2)) + "!");
        }
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = vidaMaxima; }
    public int getDano() {
        int danoBase = dano 
            + equipamentos.getOrDefault("Wado Ichimonji", 0) 
            + equipamentos.getOrDefault("Tonfas de Sanji", 0)
            + equipamentos.getOrDefault("Espada Shusui", 0)
            + equipamentos.getOrDefault("Punhos de Marimheiro", 0)
            + equipamentos.getOrDefault("Titanic Captain's Cannon", 0);

        switch (classe) {
            case "Filho do Mar": 
                danoBase += 20; 
                break;
            case "Caçador de Piratas": 
                danoBase += 2 * level; 
                break;
        }
        return danoBase;
    }
    public void setDano(int dano) { this.dano = dano; }
    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getSorte() { 
        int sorteTotal = sorte 
            + equipamentos.getOrDefault("Chapéu de Palha", 0)
            + equipamentos.getOrDefault("Violino de Brook", 0)
            + equipamentos.getOrDefault("Mapa do Novo Mundo", 0);

        if ("Sortudo".equals(classe)) {
            sorteTotal += 20;
        }
        return sorteTotal;
    }
    public void setSorte(int sorte) { this.sorte = sorte; }
    public int getDefesa() {
        int defesa = equipamentos.getOrDefault("Clima-Tact", 0)
            + equipamentos.getOrDefault("Roupa de Batalha de Franky", 0)
            + equipamentos.getOrDefault("Armadura de Germa 66", 0);

        if ("Fã do Luffy".equals(classe)) {
            defesa += 20;
        }
        return defesa;
    }
    public Map<String, Integer> getEquipamentos() { return equipamentos; }
    public void setEquipamentos(Map<String, Integer> equipamentos) { this.equipamentos = equipamentos; }
}