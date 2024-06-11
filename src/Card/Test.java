package Card;
import java.util.*;


public class Test {
    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void test() throws InterruptedException {
        CardDesks cards=new CardDesks(2);
        Comparator<Card> c=new compareCard();
        cards.shuffle();
        System.out.print("\nShuffled card desk");
        printCard(cards);
        Arrays.sort(cards.getCards(),c);
        System.out.print("\nSorted card desk");
        printCard(cards);
        Gambler[] gamblers={new Gambler("Player1",new ArrayList<>()),new Gambler("Player2",new ArrayList<>()),new Gambler("Player3",new ArrayList<>())};
        ArrayList<Card> bookMakerHand=new ArrayList<>();
        BookMaker bm=new BookMaker("Jenda",cards,bookMakerHand);
        BlackJack bj=new BlackJack(bm,gamblers);
        Stack<Card> cardStack=bm.getCards().toStack();
        int k=1;
        while (k<=5){
            System.out.println("\nGAME "+ k++);
            bm.distribute(gamblers,cardStack);
            System.out.println(bm.getHand().get(0));
            System.out.println();
            for (int i=0;i< gamblers.length;i++) {
                System.out.println("Player "+(i+1)+" turn ");
                gamblers[i].showHand();
                System.out.print("POINT["+gamblers[i].point()+"]\n");
                gamblers[i].drawCard(cardStack);
                System.out.println();
            }
            System.out.println("Jenda turn ");
            bm.showHand();
            System.out.print("POINT["+bm.point()+"]\n");
            //bm.drawCard(cardStack);
            Random rand=new Random();
            int drawVer= rand.nextInt(1,7);
            switch (drawVer) {
                case 1,6 -> bm.drawCardVer2(cardStack,gamblers, 17);
                case 2 -> bm.drawCardVer3(cardStack, gamblers);
                case 3,4,5 -> bm.drawCardVer4(cardStack, gamblers);
            }

            for (Gambler gambler : gamblers) {
                System.out.println(bj.gameResult(bm,gambler)+"\n");
            }
            bm.getHand().clear();
            for (Gambler gambler:gamblers){
                gambler.getHand().clear();
            }
            System.out.println("------------------------------------------");
        }
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
