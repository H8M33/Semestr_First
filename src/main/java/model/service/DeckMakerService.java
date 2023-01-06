package model.service;

import model.entity.BlackJack.Card;
import model.entity.BlackJack.Deck;

import java.util.LinkedList;

public class DeckMakerService {
    static char[] chars = {'♠','♥','♣','♦'};

    public static Deck getDeck(){
        LinkedList<Card> list = new LinkedList<>();
        for (int i =0;i<4;i++){
            for (int j=1;j<=13;j++){
                switch (j) {
                    case (1) -> list.add(new Card(chars[i] + "A", 11));
                    case (11) -> list.add(new Card(chars[i] + "J", 10));
                    case (12) -> list.add(new Card(chars[i] + "Q", 10));
                    case (13) -> list.add(new Card(chars[i] + "K", 10));
                    default -> list.add(new Card(chars[i] + "" + j, j));
                }
            }
        }

        return new Deck(list);
    }
}
