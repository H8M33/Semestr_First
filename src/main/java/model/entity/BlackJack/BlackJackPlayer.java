package model.entity.BlackJack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.repository.SqlGenerator.ColumnName;
import model.repository.SqlGenerator.TableName;

@AllArgsConstructor
@Data
@Builder
@TableName(nameOfTable = "black_jack_player")
public class BlackJackPlayer {
    @ColumnName(name = "game_id")
    Long game_id;
    @ColumnName(name = "player_id")
    Long player_id;
    @ColumnName(name = "", toIgnore = true)
    String name;
    @ColumnName(name = "value")
    Integer value;
    @ColumnName(name = "cards")
    String cards;
    @ColumnName(name = "ace_counts")
    Integer ace_counts;
    @ColumnName(name = "is_stopped", defaultBooleanTrue = true)
    boolean is_stopped;


    public void takeCard(){

    }
}
