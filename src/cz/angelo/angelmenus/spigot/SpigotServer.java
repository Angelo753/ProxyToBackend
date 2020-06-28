package cz.angelo.angelmenus.spigot;

public class SpigotServer {

    private final String name;
    private final String motd;
    private final int players;
    private final int maxPlayers;

    public SpigotServer(String name, String motd, int players, int maxPlayers) {
        this.name = name;
        this.motd = motd;
        this.players = players;
        this.maxPlayers = maxPlayers;
    }


    public String getName() {
        return name;
    }

    public String getMotd() {
        return motd;
    }

    public int getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

}
