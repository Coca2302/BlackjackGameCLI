package Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BookMaker implements Player {
    private final String name;
    private final CardDesks cards;
    private final ArrayList<Card> hand;
    Scanner sc=new Scanner(System.in);


    public BookMaker(String name,CardDesks cards,ArrayList<Card> hand) {
        this.name = name;
        this.cards=cards;
        this.hand=hand;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    //jenda's thinking
    @Override
    public ArrayList<Card> drawCard(Stack<Card> cards){
        int x;
        if((this.getHand().get(0).getRank().equals(1)&&this.getHand().get(1).getRank()>=10)||
                (this.getHand().get(1).getRank().equals(1)&&this.getHand().get(0).getRank()>=10))
            return hand;
        while (!bust()) {
            System.out.println("0 to draw/1 to stop");
            x= sc.nextInt();
            if(x==1){
                showHand();
                System.out.println("stop");
                break;
            }
            if(this.point()+cards.peek().getRank()>21){
                Card card=cards.pop();
                hand.add(cards.pop());
                cards.push(card);
            }else {
                hand.add(cards.pop());
            }
            if(bust()){
                showHand();
                System.out.println("bust");
                break;
            }
            showHand();
            System.out.println(this.point());
        }
        return hand;
    }
    public ArrayList<Card> drawCardVer2(Stack<Card> cards,Gambler[] gamblers, int minPoint) throws InterruptedException {
        int maxPoint=0;
        for(Gambler gambler:gamblers){
            if(gambler.point()>maxPoint) {
                maxPoint = gambler.point();
            }
        }
        if(this.point()>=maxPoint)
            return hand;
        if((this.getHand().get(0).getRank().equals(1)&&this.getHand().get(1).getRank()>=10)||
                (this.getHand().get(1).getRank().equals(1)&&this.getHand().get(0).getRank()>=10))
            return hand;
        while (!bust()){
            int a=21-this.point();
            if(cards.peek().getRank()>a){
                cards.pop();
            }else {
                hand.add(cards.pop());
                Thread.sleep(3000);
                showHand();
                System.out.print("POINT["+this.point()+"]");
                Thread.sleep(3000);
            }
            if (this.point()>=minPoint){
                break;
            }
        }
        return hand;
    }
    public ArrayList<Card> drawCardVer3(Stack<Card> cards,Gambler[] gamblers) throws InterruptedException {
        int maxPoint=0;
        int sum=0;
        for(Gambler gambler:gamblers){
            if(gambler.point()>maxPoint) {
                maxPoint = gambler.point();
                sum +=gambler.point();
            }
        }
        int avg=sum/ gamblers.length;
        while (true){
            if(point()>=maxPoint||bust())
                break;
            else if (point()<=14) {
                Thread.sleep(3000);
                hand.add(cards.pop());
                showHand();
                System.out.println("POINT["+this.point()+"]");
                Thread.sleep(3000);
            }
            else{
                if(point()<avg)
                    drawCardVer2(cards,gamblers,avg);
                else
                    return hand;
            }
        }
        return hand;
    }
    public ArrayList<Card> drawCardVer4(Stack<Card> cards, Gambler[] gamblers) throws InterruptedException {
        int sum=0;
        for(Gambler gambler:gamblers){
            sum +=gambler.point();
        }
        int avg=sum/gamblers.length;
        while (true){
            if(this.point()>avg-1){
                break;
            }else {
                Thread.sleep(3000);
                hand.add(cards.pop());
                showHand();
                System.out.println("POINT["+this.point()+"]");
                Thread.sleep(3000);
            }
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
    public void showHand(){
        for (Card card : hand) {
            System.out.print(card + " ");
        }
    }
    public int point(){
        if ((this.getHand().get(0).getRank()>=10&&this.getHand().get(1).getRank()==1)||
                (this.getHand().get(1).getRank()>=10&&this.getHand().get(0).getRank()==1)){
            return 21;
        }
        int sum=0;
        for (Card card : hand) {
            if (card.getRank() >= 10) {
                sum += 10;
            } else {
                sum += (card.getRank());
            }
        }
        return sum;
    }
    public CardDesks getCards() {
        return cards;
    }
    public void distribute(Gambler[] gamblers, Stack<Card> cards){
        this.getHand().add(cards.pop());
        for (Gambler gambler : gamblers) {
            for (int j = 0; j < 2; j++) {
                gambler.getHand().add(cards.pop());
            }
        }
        this.getHand().add(cards.pop());
    }

    @Override
    public String toString() {
        return this.name+" ["+point()+"]";
    }
}
