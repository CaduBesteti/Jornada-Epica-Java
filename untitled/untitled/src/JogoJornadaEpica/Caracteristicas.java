public class Caracteristicas {
    private int bonusVida;
    private int bonusAtaque;
    private int bonusSorte;

    public Caracteristicas(String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                bonusVida = 10;
                bonusAtaque = 10;
                bonusSorte = 10;
                break;
            case "homem peixe":
                bonusVida = 10;
                bonusAtaque = 15;
                bonusSorte = 0;
                break;
            case "mink":
                bonusVida = 10;
                bonusAtaque = 0;
                bonusSorte = 15;
                break;
            case "gigante":
                bonusVida = 15;
                bonusAtaque = 20;
                bonusSorte = -5;
                break;
            default:
                bonusVida = 0;
                bonusAtaque = 0;
                bonusSorte = 0;
        }
    }

    // Getters e Setters
    public int getBonusVida() { return bonusVida; }
    public void setBonusVida(int bonusVida) { this.bonusVida = bonusVida; }
    public int getBonusAtaque() { return bonusAtaque; }
    public void setBonusAtaque(int bonusAtaque) { this.bonusAtaque = bonusAtaque; }
    public int getBonusSorte() { return bonusSorte; }
    public void setBonusSorte(int bonusSorte) { this.bonusSorte = bonusSorte; }
}