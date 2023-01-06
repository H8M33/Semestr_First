package model.entity.BlackJack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.repository.SqlGenerator.ColumnName;
import model.repository.SqlGenerator.TableName;

import java.util.Map;

@AllArgsConstructor
@Data
@Builder
@TableName(nameOfTable = "black_jack_game")
public class BlackJackGame {
    @ColumnName(name = "id",identity = true,primary = true)
    Long id;
    @ColumnName(name = "is_ended", defaultBooleanFalse = true)
    boolean is_ended;
    @ColumnName(name = "bank")
    Integer bank;
    @ColumnName(name = "", toIgnore = true)
    Map<Long,BlackJackPlayer> players;
    @ColumnName(name = "rounds")
    Integer rounds;
}
