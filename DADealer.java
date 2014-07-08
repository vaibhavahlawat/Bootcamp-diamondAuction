package codes;

public class DADealer extends Hand{
    
    
    public DADealer(Card[] cards){
        super(cards);
    }
    
    
    public Integer[] ChooseWinners(Card[] cards){
        
       // int points1 = 10, points2 = 9, points3 = 8;
         // Logic to compare 
        int max = -1;
        int sizeCtr = 0;
        for(int i = 0; i < cards.length; ++i){
            if(max < cards[i].getRank())
                max = cards[i].getRank();
        }
        
        for(int i = 0; i < cards.length; ++i){
            if(max == cards[i].getRank())
                sizeCtr++;
        }
        
        Integer[] p = new Integer[sizeCtr];
        
        for(int i = 0, j = 0; i < cards.length && j < sizeCtr; ++i){
            if(max == cards[i].getRank())
                p[j++] = i;
        }
        return p;
    }
    
    
}
