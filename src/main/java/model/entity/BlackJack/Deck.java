package model.entity.BlackJack;

import model.service.DeckMakerService;

import java.util.List;

public record Deck(List<Card> list) {
    public Card getCard() {
        if (list.size()>0) {
            int t = rnd(0, list.size());
            Card card = list.get(t);
            list.remove(t);
            return card;
        }
        else {
            list.addAll(DeckMakerService.getDeck().list());
            return getCard();
        }
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * max) + min;
    }
}
