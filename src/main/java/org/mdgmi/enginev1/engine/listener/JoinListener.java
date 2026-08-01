package org.mdgmi.enginev1.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mdgmi.enginev1.engine.player.DataManager;

public class JoinListener
        implements Listener {

    @EventHandler
    public void onJoin(
            PlayerJoinEvent e
    ) {

        DataManager.get(
                e.getPlayer()
        );

    }

}