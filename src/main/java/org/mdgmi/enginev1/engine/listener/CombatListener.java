package org.mdgmi.enginev1.engine.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.mdgmi.enginev1.characters.sooyoung.AvatarManager;
import org.mdgmi.enginev1.engine.manager.CombatManager;

public class CombatListener implements Listener {

    @EventHandler
    public void onAttack(
            EntityDamageByEntityEvent event
    ) {

        if (event.isCancelled())
            return;

        if (CombatManager.isInternalDamage())
            return;

        if (!(event.getDamager() instanceof Player attacker))
            return;

        if (!(event.getEntity() instanceof LivingEntity victim))
            return;

        double damage =
                CombatManager.attack(
                        attacker,
                        victim,
                        event.getDamage()
                );

        event.setDamage(
                Math.max(0, damage)
        );

        // ★ 추가
        AvatarManager.setTarget(
                attacker,
                victim
        );

    }
}