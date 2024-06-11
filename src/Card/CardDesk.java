package Card;
import java.util.*;

public class CardDesk {
    private final Card[] cards;
    public CardDesk() {
        cards=new Card[52];
        int k=0;
        for(int i=1;i<=13;i++){
            for (int j=1;j<=4;j++) {
                cards[k]=new Card(i,j);
                k++;
            }
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public void shuffle(){
        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
            swap(cards,rand.nextInt(52), rand.nextInt(52));
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
    public void sort(Card[] cards, Comparator<Card> c,boolean ascending){
        //using selection sort
        for(int i=0;i< cards.length-1;i++){
            int current=i;
            for (int j = i+1; j <cards.length ; j++) {
                if(c.compare(cards[j], cards[current]) == ((ascending) ? 1 : 0)) {
                    current=j;
                }
            }
            swap(cards,current,i);
        }
    }
}
