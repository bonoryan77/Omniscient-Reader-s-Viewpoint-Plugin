package org.mdgmi.enginev1.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mdgmi.enginev1.engine.player.DataManager;

public class QuitListener
        implements Listener {

    @EventHandler
    public void onQuit(
            PlayerQuitEvent e
    ) {

        DataManager.remove(
                e.getPlayer()
        );

    }

}