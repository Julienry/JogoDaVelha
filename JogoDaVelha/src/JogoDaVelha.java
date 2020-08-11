
import java.util.Scanner;


public class JogoDaVelha {
    public static void main(String[] args) {
        
        game();
    }

    private static void game() {
        char tabuleiro[][] = initialize();
        int jogoAtivo = -1, lin, col;
        char jogador[] = {'O','X'};
        int i=0;

        Scanner ler = new Scanner(System.in);
        while( jogoAtivo == -1){
            //System.out.println("tabuleiro i="+i);
            printGame(tabuleiro);
            // entrada de dados
            System.out.println("Vez do Jogador ["+jogador[i]+"]");
            System.out.print("digite a linha :");
            lin = ler.nextInt();
            System.out.print("digite a coluna:");
            col = ler.nextInt();
            
            // jogada valida
            if( step( tabuleiro, lin, col, jogador[i]) ){
                // int status (char M[ ][ ]){...}
                jogoAtivo = status(tabuleiro, jogador[i]);
                // troca o turno do jagador
                //i = (i + 1)%2;
                i = 1 - i;
            }else
                System.out.println("Jogada invalida do ["+jogador[i]+"]");
            
        }
        if( jogoAtivo == 0)
            System.out.println("Empatou");
        else if( jogoAtivo == 1)
            System.out.println("O ganhou");
        else
            System.out.println("X ganhou");
            
        
        
    }
    private static char[][] initialize() {
        // instancia a matriz        
      char M[][]= new char[3][3];
        
        // inicializa a matriz com _ = vazio
        for( int i=0; i < M.length; i++) // linha
            for( int j=0; j < M[0].length; j++) // coluna
                M[i][j] = '_';
        return M;
    }

    private static boolean step(char[][] tabuleiro, int lin, int col, char jogador) {
        // se a posicao tiver vazia
        if( tabuleiro[lin][col] == '_'){
            tabuleiro[lin][col] = jogador;
            return true;
        }
        return false; 
    }

    private static int status(char[][] tabuleiro, char jogador) {
        int ret=1; // testando o jogador 'O'
        
        if( jogador== 'X')
            ret = 2;
        
        // testa para linha e coluna os dois jogadores
        for( int i=0;i<tabuleiro.length;i++){
            // verifica as linhas
            if( (tabuleiro[i][0]==jogador && tabuleiro[i][1]==jogador && tabuleiro[i][2]==jogador) ||
                // verifica a coluna
                (tabuleiro[0][i]==jogador && tabuleiro[1][i]==jogador && tabuleiro[2][i]==jogador))
              return ret ;
        }
        // testar as diagonais
        // diagonal principal
        if( (tabuleiro[0][0]==jogador && tabuleiro[1][1]==jogador && tabuleiro[2][2]==jogador) ||
            // diagonal secundaria
            (tabuleiro[0][2]==jogador && tabuleiro[1][1]==jogador && tabuleiro[2][0]==jogador))
            return ret;
        
        ret = 0; // a principio empatou 
        for( int i=0; i < tabuleiro.length;i++)
            for( int j=0; j < tabuleiro[0].length;j++)
                if( tabuleiro[i][j]=='_')
                    ret = -1; // pode continuar
        
        return ret;
    }

    private static void printGame(char[][] tabuleiro) {
        for( int i=0; i < tabuleiro.length; i++){ // linha
            for( int j=0; j < tabuleiro[0].length; j++) // coluna
                System.out.print( tabuleiro[i][j]+"|");    
            
            System.out.println(); // nova linha
        }
    }
    
}
