package cz.angelo.angelmenus.bungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.HashMap;
import java.util.Map;

public class ServerFetcher extends Thread {

    private final AngelMenusBungee plugin;
    private final int interval;

    private Map<String, BungeeServer> servs;

    public ServerFetcher(AngelMenusBungee plugin, int interval) {
        this.plugin = plugin;
        this.interval = interval;
        this.servs = new HashMap<String, BungeeServer>();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(this.interval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String updated = null;
            for(String s : this.plugin.getProxy().getServers().keySet()) {
                ServerInfo info = this.plugin.getProxy().getServers().get(s);
                try {
                    info.ping((serverPing, throwable) -> servs.put(s, new BungeeServer(s, serverPing.getDescription(), serverPing.getPlayers().getOnline(), serverPing.getPlayers().getMax())));
                } catch(Exception e) {
                    servs.put(s, new BungeeServer(s, "none", 0, 0));
                }
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("update");
                out.writeUTF(BungeeServerPatcher.patch(this.servs.get(s)));
                for(String s2 : this.plugin.getProxy().getServers().keySet()) {
                    if(s2.toLowerCase().contains("lobby")) {
                        this.plugin.getProxy().getServers().get(s2).sendData("angelmenus:channel", out.toByteArray());
                    }
                }
            }
        }
    }

    public AngelMenusBungee getPlugin() {
        return plugin;
    }
}
