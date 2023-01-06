package model.entity.BlackJack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.repository.SqlGenerator.ColumnName;
import model.repository.SqlGenerator.TableName;

@AllArgsConstructor
@Data
@Builder
@TableName(nameOfTable = "black_jack_rounds")
public class BlackJackRound {
    @ColumnName(name = "game_id")
    Long gameID;
    @ColumnName(name = "username")
    String username;
    @ColumnName(name = "order_number")
    Long orderNumber;
    @ColumnName(name = "hand")
    String hand;
}
