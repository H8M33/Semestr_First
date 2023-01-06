package model.service;

import model.entity.BlackJack.BlackJackGame;
import model.repository.blackjack.BlackJackRepository;

public class BlackJackService {
    public static boolean isGameStarted(String id){
        return BlackJackRepository.isGameStarted(Long.parseLong(id));
    }

    public static boolean isPlayer(long user_id, String game_id){
        return BlackJackRepository.isPlayer(user_id, Long.parseLong(game_id));
    }
    public static boolean isPlayer(long user_id, long game_id){
        return BlackJackRepository.isPlayer(user_id, game_id);
    }

    public static boolean isEnded(String id){
        return BlackJackRepository.isEnded(Long.parseLong(id));
    }

    public static long getIdToCreate(){
        return BlackJackRepository.getLastID()+1;
    }

    public static void createGame(long id){BlackJackRepository.createGame(id);}
    public static BlackJackGame getGame(String id){return BlackJackRepository.getGame(Long.parseLong(id));}

    public static void addPlayer(String game_id, String user_id) {BlackJackRepository.addPlayer(Long.parseLong(game_id), Long.parseLong(user_id));
    }
    public static void removePlayer(String game_id, String user_id) {BlackJackRepository.removePlayer(Long.parseLong(game_id), Long.parseLong(user_id));
    }
}
