/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculacaminho;

import java.util.concurrent.RecursiveTask;

/**
 *
 * @author CC49755682848
 */
public class BuscaCaminhos extends RecursiveTask<Integer>{
    int x, y, qtdCaminhos = 0;
    
    public BuscaCaminhos(int pontoX, int pontoY){
        this.x = pontoX;
        this.y = pontoY; 
}
    
    public int ContadorDeCaminhos(int x, int y){
        //se chegar na origem
        if ( x == 0 && y == 0) return 0;
        
        //se algum dos lados chegar a 0
        if (x == 0 || y == 0) return 1;
        
        //função recursiva
        return (ContadorDeCaminhos(x-1,y) + ContadorDeCaminhos(x,y-1));
    }
    
    @Override
    protected Integer compute(){
        if(x == 0 && y == 0){
            ContadorDeCaminhos(x,y);
        } else if (this.x > 0 || this.y > 0){
            qtdCaminhos = qtdCaminhos + ContadorDeCaminhos(x,y);
        } else {
            BuscaCaminhos eixoX = new BuscaCaminhos(1,2);
            BuscaCaminhos eixoY = new BuscaCaminhos(2,1);
            eixoX.fork();
            eixoY.fork();
            eixoX.join();
            eixoY.join();
        }
        return qtdCaminhos;
    }
}
