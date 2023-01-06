package model.entity.BlackJack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class Card {
    String name;
    int value;
}
