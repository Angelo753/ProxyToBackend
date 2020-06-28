package cz.angelo.angelmenus.bungee;

import cz.angelo.angelmenus.spigot.SpigotServer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BungeeServerPatcher {

    public static String patch(BungeeServer server) {
        String res = null;
        for(Method m : server.getClass().getMethods()) {
            if(m.getName().toLowerCase().contains("get")) {
                try {
                    if(m.getName().toLowerCase().contains("get")) {
                        String name = m.getName().toLowerCase().replaceAll("get", "");
                        Object o = m.invoke(server);
                        String oS = o.toString();
                        if(res == null) {
                            res = name + ":" + oS;
                            continue;
                        }
                        res += "-" + name + ":" + oS;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static SpigotServer unPatch(String input) {
        Map<String, String> temp = new HashMap<String, String>();
        String[] obj = input.split("-");
        for(String s : obj) {
            temp.put(s.split(":")[0], s.split(":")[1]);
        }
        return new SpigotServer(temp.get("name"), temp.get("motd"), Integer.parseInt(temp.get("players")), Integer.parseInt(temp.get("maxplayers")));
    }

}
