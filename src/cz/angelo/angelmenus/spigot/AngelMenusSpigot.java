package cz.angelo.angelmenus.spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AngelMenusSpigot extends JavaPlugin {

    private MessageListener messageListener;

    @Override
    public void onEnable() {
        this.messageListener = new MessageListener(this);
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "angelmenus:channel", this.messageListener);

        for(String s : this.messageListener.getServers().keySet()) {
            SpigotServer serv = this.messageListener.getServers().get(s);
            Inventory inv = Bukkit.createInventory(null, 18, "cus");
            ItemStack server = new ItemStack(Material.WOOL, serv.getPlayers(), (short) 12);
            ItemMeta serverMeta = server.getItemMeta();
            List<String> lore = new ArrayList<String>();
            lore.add(" ");
            lore.add(" NazevServeru: " + serv.getName());
            lore.add(" Motd: " + serv.getMotd());
            lore.add(" Players: " + serv.getPlayers() + "/" + serv.getMaxPlayers());
            serverMeta.setLore(lore);
            serverMeta.setDisplayName(ChatColor.GREEN + serv.getName());
            server.setItemMeta(serverMeta);
            inv.setItem(0, server);
        }
    }

    public MessageListener getServersDatabase() {
        return this.messageListener;
    }

}
