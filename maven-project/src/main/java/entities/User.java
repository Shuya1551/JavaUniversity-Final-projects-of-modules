package entities;

import lombok.Getter;

public class User {
    private String name;
    @Getter
    private int lostGames;
    @Getter
    private int wonGames;

    public User(String name) {
        this.name = name;
        this.lostGames = 0;
        this.wonGames = 0;
    }

}
