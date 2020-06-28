package cz.angelo.angelmenus.spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    private AngelMenusSpigot angelmenusspigot;

    public void guiCreate(){
        for(String s : angelmenusspigot.getServersDatabase().getServers().keySet()) {
            SpigotServer serv = angelmenusspigot.getServersDatabase().getServers().get(s);
            Inventory inv = Bukkit.createInventory(null, 18, angelmenusspigot.getServersDatabase().getServers().get(s).getName());
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

}
