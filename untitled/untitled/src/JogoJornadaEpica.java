import java.util.Scanner;
import java.util.Random;

class Personagem {
    String nome;
    String classe;
    int nivel;
    int vida;
    int ataque;

    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = 1;
        this.vida = 100;
        this.ataque = 10;
    }

    public void mostrarStatus() {
        System.out.println("Personagem: " + nome + " | Classe: " + classe + " | Nível: " + nivel);
        System.out.println("Vida: " + vida + " | Ataque: " + ataque);
    }

    public void atacar(Inimigo inimigo) {
        System.out.println(nome + " ataca " + inimigo.nome + " causando " + ataque + " de dano!");
        inimigo.vida -= ataque;
    }

    public void evoluir() {
        nivel++;
        vida += 20;
        ataque += 5;
        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }
}

class Inimigo {
    String nome;
    int vida;
    int ataque;

    public Inimigo(String nome, int vida, int ataque) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void atacar(Personagem personagem) {
        System.out.println(nome + " ataca " + personagem.nome + " causando " + ataque + " de dano!");
        personagem.vida -= ataque;
    }
}

public class JogoJornadaEpica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bem-vindo à Jornada Épica!");
        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();
        System.out.print("Escolha sua classe (Guerreiro / Mago / Arqueiro): ");
        String classe = scanner.nextLine();

        Personagem jogador = new Personagem(nome, classe);
        jogador.mostrarStatus();

        System.out.println("Você encontrou um inimigo!");
        Inimigo inimigo = new Inimigo("Goblin", 50, 8);

        while (jogador.vida > 0 && inimigo.vida > 0) {
            System.out.println("O que deseja fazer? (1) Atacar (2) Fugir");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                jogador.atacar(inimigo);
                if (inimigo.vida > 0) {
                    inimigo.atacar(jogador);
                }
            } else {
                System.out.println("Você fugiu da batalha!");
                break;
            }
        }

        if (jogador.vida > 0 && inimigo.vida <= 0) {
            System.out.println("Você derrotou o inimigo!");
            jogador.evoluir();
        } else if (jogador.vida <= 0) {
            System.out.println("Game Over!");
        }

        // quero que faca mais etapas desse jogo e que use getset para deixar o codigo mais limpo
        // e que use o conceito de heranca para criar mais personagens e inimigos
        class Personagem {
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





        scanner.close();
    }


}
