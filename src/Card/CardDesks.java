package Card;

import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

public class CardDesks {
    private Card[] cards;

    public CardDesks(int NCardDesk) {
        cards=new Card[NCardDesk*52];
        int count=0;
        for(int i=0;i<NCardDesk;i++){
            for (int j = 1; j < 14; j++) {
                for (int k = 1; k <5 ; k++) {
                    cards[count]=new Card(j,k);
                    count++;
                }
            }
        }
    }
    public Card[] getCards() {
        return cards;
    }


    public void shuffle(){
        Random rand = new Random();
        for (int i = 0; i < cards.length*2; i++) {
            swap(cards,rand.nextInt(getCards().length), rand.nextInt(getCards().length));
        }
    }
    public void swap(Card[] cards,int i,int j){
        Card temp=cards[i];
        cards[i]=cards[j];
        cards[j]=temp;
    }
    public Stack<Card> toStack(){
        shuffle();
        Stack<Card> cardDesk=new Stack<>();
        for (Card card : cards) {
            cardDesk.push(card);
        }
        return cardDesk;
    }
    public void sort(Card[] cards, Comparator<Card> c, boolean ascending){
        //using selection sort
        for(int i=0;i< cards.length-1;i++){
            int maxIndex=i;
            for (int j = i+1; j <cards.length ; j++) {
                if(c.compare(cards[j], cards[maxIndex]) == ((ascending) ? 1 : 0)) {
                    maxIndex=j;
                }
            }
            swap(cards,maxIndex,i);
        }
    }
}
