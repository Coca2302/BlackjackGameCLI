package Card;

public class Card implements Comparable<Card>{
    private Integer rank;
    private Integer suit;

    public Card(Integer rank, Integer suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }
    @Override
    public int compareTo(Card another) {
        if(another.getRank()>this.getRank())
            return 1;
        else if (another.getRank().equals(this.getRank()))
            return (this.getSuit() < another.getSuit()) ? -1 : 1;
        else if(another.getRank().equals(this.getRank())&&this.getSuit().equals(another.getSuit()))
            return 0;
        return -1;
    }
    public String suit(){
        if(this.suit==1)
            return "♥️";
        else if (this.suit==2)
            return "♦️";
        else if(this.suit==3)
            return "♣️";
        else if(this.suit==4)
            return "♠️";
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        if(this.rank<=10&&this.rank!=1)
            return rank+suit();
        else if (this.rank==11) {
            return "J"+suit();
        }else if (this.rank==12) {
            return "Q"+suit();
        }else if (this.rank==13) {
            return "K"+suit();
        }else if (this.rank==1) {
            return "A"+suit();
        }
        throw new IllegalArgumentException();
    }
}
