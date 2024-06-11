package Card;
import java.util.Arrays;
import java.util.Comparator;

public class Test1 {
    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void test() throws InterruptedException {
        CardDesks cards = new CardDesks(2);
        Comparator<Card> c = new compareCard();
        cards.shuffle();
        System.out.print("\nShuffled card desk");
        printCard(cards);
        Arrays.sort(cards.getCards(), c);
        //ascending
        //cards.sort(cards.getCards(),c,true);
        //decreasing
        //cards.sort(cards.getCards(),c,false);
        System.out.print("\nSorted card desk");
        printCard(cards);
    }
    public static void printCard(CardDesks cards){
        for (int i = 0; i < cards.getCards().length; i++) {
            if(i%4==0) {
                System.out.print("\n"+cards.getCards()[i] + " ");
            }else{
                System.out.print(cards.getCards()[i] + " ");
            }
        }
        System.out.println();
    }
}
