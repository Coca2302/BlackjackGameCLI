package Card;

public class BlackJack {
    private BookMaker bookMaker;
    private Gambler[] gamblers;

    public BlackJack( BookMaker bookMaker,Gambler[] gamblers) {
        this.bookMaker = bookMaker;
        this.gamblers = gamblers;
    }
    public Player gameResult(BookMaker bookMaker, Gambler gambler){
        if (((bookMaker.getHand().get(0).getRank()>=10&&bookMaker.getHand().get(1).getRank()==1)||
           (bookMaker.getHand().get(1).getRank()>=10&&bookMaker.getHand().get(0).getRank()==1))&&
           ((gambler.getHand().get(0).getRank()>=10&&gambler.getHand().get(1).getRank()==1)||
           (gambler.getHand().get(1).getRank()>=10&&gambler.getHand().get(0).getRank()==1))){
            System.out.print("DRAW");
            return null;
        }
        if ((bookMaker.getHand().get(0).getRank()>=10&&bookMaker.getHand().get(1).getRank()==1)||
           (bookMaker.getHand().get(1).getRank()>=10&&bookMaker.getHand().get(0).getRank()==1)) {
            System.out.print("Blackjack ");
            return bookMaker;
        }
        if ((gambler.getHand().get(0).getRank()>=10&&gambler.getHand().get(1).getRank()==1)||
                (gambler.getHand().get(1).getRank()>=10&&gambler.getHand().get(0).getRank()==1)) {
            System.out.print("Blackjack ");
            return gambler;
        }
        if (gambler.bust()) {
            System.out.print("Bookmaker win: ");
            return bookMaker;
        }
        if(bookMaker.bust()) {
            System.out.print("Gambler win: ");
            return gambler;
        }
        if(gambler.point()>bookMaker.point()){
            System.out.print("Gambler win: ");
            return gambler;
        } else if (bookMaker.point()>gambler.point()) {
            System.out.print("Bookmaker win: ");
            return bookMaker;
        } else if (bookMaker.point()==gambler.point()) {
            System.out.print("DRAW");
            return null;
        }
        return null;
    }

}
