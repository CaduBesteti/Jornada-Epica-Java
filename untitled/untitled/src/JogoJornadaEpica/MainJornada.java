import java.util.Scanner;

public class JogoJornadaEpica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Jornada Épica!");
        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        System.out.print("\nEscolha sua classe (Guerreiro / Mago / Arqueiro): ");
        String classe = scanner.nextLine();

        System.out.print("\nEscolha sua raça (Humano / Elfo / Halfling): ");
        String raca = scanner.nextLine();

        // Criação do personagem
        Personagem jogador = new Personagem(nome, classe, raca);
        System.out.println("\nStatus inicial do personagem:");
        jogador.mostrarStatus();

        // Primeira batalha
        System.out.println("\nVocê encontrou um inimigo!");
        Inimigo inimigo = new Inimigo("Goblin", 50, 8);

        while (jogador.getVida() > 0 && inimigo.getVida() > 0) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("(1) Atacar");
            System.out.println("(2) Fugir\n");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                jogador.atacar(inimigo); // Jogador ataca o inimigo
                if (inimigo.getVida() > 0) {
                    inimigo.atacar(jogador); // Inimigo ataca o jogador
                }
            } else {
                System.out.println("\nVocê fugiu da batalha!");
                break;
            }

            // Mostrar status após cada rodada
            System.out.println("\nStatus após a rodada:");
            jogador.mostrarStatus();
            System.out.println("Vida do inimigo: " + inimigo.getVida());
        }

        // Resultado da primeira batalha
        if (jogador.getVida() > 0 && inimigo.getVida() <= 0) {
            System.out.println("\nVocê derrotou o inimigo!");
            jogador.evoluir();
        } else if (jogador.getVida() <= 0) {
            System.out.println("\nGame Over!");
        }

        // Segunda batalha (inimigo mais forte)
        System.out.println("\nVocê encontrou um novo inimigo!");
        Inimigo novoInimigo = new Inimigo("Orc", 70, 12);

        while (jogador.getVida() > 0 && novoInimigo.getVida() > 0) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("(1) Atacar");
            System.out.println("(2) Fugir\n");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                jogador.atacar(novoInimigo); // Jogador ataca o novo inimigo
                if (novoInimigo.getVida() > 0) {
                    novoInimigo.atacar(jogador); // Novo inimigo ataca o jogador
                }
            } else {
                System.out.println("\nVocê fugiu da batalha!");
                break;
            }

            // Mostrar status após cada rodada
            System.out.println("\nStatus após a rodada:");
            jogador.mostrarStatus();
            System.out.println("Vida do novo inimigo: " + novoInimigo.getVida());
        }

        // Resultado da segunda batalha
        if (jogador.getVida() > 0 && novoInimigo.getVida() <= 0) {
            System.out.println("\nVocê derrotou o novo inimigo!");
            jogador.evoluir();
        } else if (jogador.getVida() <= 0) {
            System.out.println("\nGame Over!");
        }

        scanner.close();
    }
}