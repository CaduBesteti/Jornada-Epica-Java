public class Personagem {
    private String nome;
    private String classe;
    private String raca;
    private String habilidade;
    private int nivel;
    private int vida;
    private int ataque;
    private int sorte;
    private Caracteristicas caracteristicas;

    // Construtor
    public Personagem(String nome, String classe, String raca) {
        this.nome = nome;
        this.classe = classe;
        this.raca = raca;
        this.nivel = 1;
        this.vida = 100;
        this.ataque = 10;
        this.sorte = 0;
        this.caracteristicas = new Caracteristicas(raca);
        this.habilidade = definirHabilidade(classe);

        // Aplicar bônus da raça
        this.vida += caracteristicas.getBonusVida();
        this.ataque += caracteristicas.getBonusAtaque();
        this.sorte += caracteristicas.getBonusSorte();
    }

    // Getters e Setters
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
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

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    // Métodos
    public void mostrarStatus() {
        System.out.println("Personagem: " + nome + " | Classe: " + classe + " | Raça: " + raca + " | Nível: " + nivel);
        System.out.println("Vida: " + vida + " | Ataque: " + ataque + " | Sorte: " + sorte);
        System.out.println("Habilidade: " + habilidade);
    }

    public void atacar(Inimigo inimigo) {
        int dano = ataque;

        // Aplicar habilidades
        if (classe.equalsIgnoreCase("Mago")) {
            dano *= 2; // Magia Duplicada
        } else if (classe.equalsIgnoreCase("Arqueiro")) {
            dano += nivel; // Análise de Batalha (aumenta o dano com o nível)
        }

        System.out.println(nome + " ataca " + inimigo.getNome() + " causando " + dano + " de dano!");
        inimigo.setVida(inimigo.getVida() - dano);
    }

    public void receberDano(int dano) {
        if (classe.equalsIgnoreCase("Guerreiro")) {
            dano /= 2; // Fúria (reduz o dano pela metade)
        }
        vida -= dano;
        System.out.println(nome + " recebeu " + dano + " de dano! Vida restante: " + vida);
    }

    public void evoluir() {
        nivel++;
        vida += 20;
        ataque += 5;
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }

    // Definir habilidade com base na classe
    private String definirHabilidade(String classe) {
        switch (classe.toLowerCase()) {
            case "guerreiro":
                return "Fúria (reduz o dano tomado pela metade)";
            case "mago":
                return "Magia Duplicada (duplica o dano aplicado)";
            case "arqueiro":
                return "Análise de Batalha (aumenta o dano a cada uso)";
            default:
                return "Nenhuma habilidade especial";
        }
    }

    // Classe interna para características das raças
    private class Caracteristicas {
        private int bonusVida;
        private int bonusAtaque;
        private int bonusSorte;

        public Caracteristicas(String raca) {
            switch (raca.toLowerCase()) {
                case "humano":
                    bonusVida = 5;
                    bonusAtaque = 5;
                    bonusSorte = 5;
                    break;
                case "elfo":
                    bonusVida = 5;
                    bonusAtaque = 10;
                    bonusSorte = 0;
                    break;
                case "halfling":
                    bonusVida = 5;
                    bonusAtaque = 0;
                    bonusSorte = 10;
                    break;
                default:
                    bonusVida = 0;
                    bonusAtaque = 0;
                    bonusSorte = 0;
                    break;
            }
        }

        public int getBonusVida() {
            return bonusVida;
        }

        public int getBonusAtaque() {
            return bonusAtaque;
        }

        public int getBonusSorte() {
            return bonusSorte;
        }
    }
}