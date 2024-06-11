package Card;
import java.util.Comparator;
public class compareCard implements Comparator<Card> {
    @Override
    public int compare(Card card1, Card card2) {
        if(card2.getRank()>card1.getRank())
            return 1;
        else if (card2.getRank().equals(card1.getRank()))
            return (card1.getSuit() < card2.getSuit()) ? -1 : 1;
        else if(card2.getRank().equals(card1.getRank())&&card1.getSuit().equals(card2.getSuit()))
            return 0;
        return -1;
    }
}
