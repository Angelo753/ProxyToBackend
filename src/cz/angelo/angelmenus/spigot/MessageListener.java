package cz.angelo.angelmenus.spigot;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cz.angelo.angelmenus.bungee.BungeeServerPatcher;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashMap;
import java.util.Map;

public class MessageListener implements PluginMessageListener {

    private final AngelMenusSpigot plugin;
    private Map<String, SpigotServer> servers;

    public MessageListener(AngelMenusSpigot plugin) {
        this.plugin = plugin;
        this.servers = new HashMap<String, SpigotServer>();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player hrac, byte[] bytes) {
        if(!channel.equalsIgnoreCase("angelmenus:channel")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();
        if(subChannel.equalsIgnoreCase("update")) {
            String data = in.readUTF();
            SpigotServer res = BungeeServerPatcher.unPatch(data);
            this.servers.put(res.getName(), res);
        }
    }

    public Map<String, SpigotServer> getServers() {
        return this.servers;
    }

    public AngelMenusSpigot getPlugin() {
        return plugin;
    }
}
