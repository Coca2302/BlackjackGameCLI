package Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
public class Gambler implements Player {
        private final String name;
        private final ArrayList<Card> hand;

        Scanner sc=new Scanner(System.in);

        public Gambler(String name,ArrayList<Card> hand) {
            this.name=name;
            this.hand = hand;
        }
        public ArrayList<Card> getHand(){
            return hand;
        }
        public void showHand(){
            for (Card card : hand) {
                System.out.print(card + " ");
            }
        }
        @Override
        public ArrayList<Card> drawCard(Stack<Card> cards){
            int x;
            if((this.getHand().get(0).getRank().equals(1)&&this.getHand().get(1).getRank()>=10)||(this.getHand().get(1).getRank().equals(1)&&this.getHand().get(0).getRank()>=10))
                return hand;
            while (!bust()) {
                System.out.println("0 to draw/1 to stop");
                x= sc.nextInt();
                if(x==1){
                    showHand();
                    System.out.print("POINT["+point()+"]\n");
                    System.out.println("stop");
                    break;
                }
                hand.add(cards.pop());
                if(bust()){
                    showHand();
                    System.out.print("POINT["+point()+"]\n");
                    System.out.println("bust");
                    break;
                }
                showHand();
                System.out.print("POINT["+point()+"]\n");
                System.out.println(this.point());
            }
            return hand;
        }
        public boolean bust(){
            int sum=0;
            for (Card card : hand) {
                if (card.getRank() >= 10) {
                    sum += 10;
                } else {
                    sum += (card.getRank());
                }
            }
            return sum > 21;
        }
        public int point(){
            if ((this.getHand().get(0).getRank()>=10&&this.getHand().get(1).getRank()==1)||
                    (this.getHand().get(1).getRank()>=10&&this.getHand().get(0).getRank()==1)){
                return 21;
            }
            int sum=0;
            for (Card card : hand) {
                if (card.getRank() >= 10)
                    sum += 10;
                else
                    sum += (card.getRank());
            }
            return sum<10&&containA() ? sum+9 : sum;
        }
        public boolean containA() {
            for (Card card : hand){
                if (card.getRank() == 1)
                    return true;
            }
            return false;
        }
        @Override
        public String toString() {
            return this.name+" ["+point()+"]";
        }
    }
