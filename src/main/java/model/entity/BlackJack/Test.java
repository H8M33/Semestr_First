package model.entity.BlackJack;

import model.service.DeckMakerService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = DeckMakerService.getDeck();
        int value = 0;
        int ace_counter = 0;
        while (true) {
            sc.next();
            Card card = deck.getCard();
            if (card.value == 11) {
                if (ace_counter >= 0) {
                    ace_counter++;
                }
                else {
                    card.setValue(1);
                }
            }
            value += card.value;
            System.out.println(card.name);
            System.out.println(card.value);
            if (value == 22 && ace_counter == 2) {
                System.out.println("You win with two aces!");
                break;
            }
            if (value > 21) {
                if (ace_counter > 0) {
                    value -= 10 * ace_counter;
                    ace_counter = -1;
                } else {
                    System.out.println("You lose.");
                    System.out.println("Your score: "+value);
                    break;
                }
            }
            if (value==21){
                System.out.println("You win!");
            }
        }
    }
}
