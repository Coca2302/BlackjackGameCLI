package Card;

import hw2_22001658_NguyenHoangViet.Card.Card;

import java.util.ArrayList;
import java.util.Stack;

public interface Player {
    ArrayList<Card> drawCard(Stack<Card> cards);
}
