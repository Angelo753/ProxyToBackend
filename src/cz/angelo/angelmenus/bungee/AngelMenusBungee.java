package cz.angelo.angelmenus.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class AngelMenusBungee extends Plugin {

    private ServerFetcher fetcher;

    @Override
    public void onEnable() {
        this.getProxy().registerChannel("angelmenus:channel");
        this.fetcher = new ServerFetcher(this, 1);
        this.fetcher.start();
    }

    public ServerFetcher getFetcher() {
        return fetcher;
    }

}
