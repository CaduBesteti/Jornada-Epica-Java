import java.util.Scanner;
import java.util.Random;

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

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            System.out.println("O que deseja fazer? (1) Atacar (2) Fugir");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                jogador.atacar(inimigo);
                if (inimigo.getVida() > 0) {
                    inimigo.atacar(jogador);
                }
            } else {
                System.out.println("Você fugiu da batalha!");
                break;
            }
        }

        if (jogador.getVida() > 0 && inimigo.getVida() <= 0) {
            System.out.println("Você derrotou o inimigo!");
            jogador.evoluir();
        } else if (jogador.getVida() <= 0) {
            System.out.println("Game Over!");
        }

        // Segunda etapa do jogo
        System.out.println("Você encontrou um novo inimigo!");
        Inimigo novoInimigo = new Inimigo("Orc", 70, 12);

        while (jogador.getVida() > 0 && novoInimigo.getVida() > 0) {
            System.out.println("O que deseja fazer? (1) Atacar (2) Fugir");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                jogador.atacar(novoInimigo);
                if (novoInimigo.getVida() > 0) {
                    novoInimigo.atacar(jogador);
                }
            } else {
                System.out.println("Você fugiu da batalha!");
                break;
            }
        }

        if (jogador.getVida() > 0 && novoInimigo.getVida() <= 0) {
            System.out.println("Você derrotou o novo inimigo!");
            jogador.evoluir();
        } else if (jogador.getVida() <= 0) {
            System.out.println("Game Over!");
        }

        scanner.close();
    }
}