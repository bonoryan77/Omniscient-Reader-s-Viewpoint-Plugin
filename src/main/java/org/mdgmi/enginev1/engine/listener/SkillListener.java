package org.mdgmi.enginev1.engine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.mdgmi.enginev1.engine.manager.SkillManager;

public class SkillListener
        implements Listener {

    @EventHandler
    public void onInteract(
            PlayerInteractEvent e
    ) {

        Action action =
                e.getAction();

        if (action != Action.RIGHT_CLICK_AIR
                && action != Action.RIGHT_CLICK_BLOCK)
            return;

        SkillManager.useSkills(
                e.getPlayer()
        );

    }

}