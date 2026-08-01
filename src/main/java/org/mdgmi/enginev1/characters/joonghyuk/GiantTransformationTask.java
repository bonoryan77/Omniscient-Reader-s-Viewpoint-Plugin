package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.manager.BossBarManager;
import org.mdgmi.enginev1.engine.manager.StatusManager;
import org.mdgmi.enginev1.engine.status.StatusType;

public class GiantTransformationTask extends BukkitRunnable {

    private final Player player;

    private int tick = 20 * 15;

    private double originalMaxHealth;

    public GiantTransformationTask(
            Player player
    ) {

        this.player = player;

    }

    @Override
    public void run() {

        if (!player.isOnline() || player.isDead()) {

            finish();
            return;

        }

        if (tick == 20 * 15) {

            startTransformation();

        }

        player.getWorld().spawnParticle(
                Particle.SOUL_FIRE_FLAME,
                player.getLocation().add(0, 1, 0),
                8,
                0.4,
                0.8,
                0.4,
                0.02
        );

        player.getWorld().spawnParticle(
                Particle.FLAME,
                player.getLocation().add(0, 1, 0),
                5,
                0.3,
                0.5,
                0.3,
                0.01
        );

        BossBarManager.setProgress(
                player,
                tick / (20D * 15D)
        );

        tick--;

        if (tick <= 0) {

            finish();

        }

    }

    private void startTransformation() {

        StatusManager.addStatus(
                player,
                StatusType.GIANT_TRANSFORMATION,
                20 * 15,
                1
        );

        player.sendTitle(
                "§4『거신화』",
                "§c거인의 힘이 각성합니다.",
                10,
                40,
                10
        );

        player.getWorld().strikeLightningEffect(
                player.getLocation()
        );

        player.playSound(
                player.getLocation(),
                Sound.ENTITY_WARDEN_ROAR,
                1f,
                0.8f
        );

        BossBarManager.create(
                player,
                "§4『거신화』"
        );

        AttributeInstance health =
                player.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                );

        if (health != null) {

            originalMaxHealth =
                    health.getBaseValue();

            health.setBaseValue(
                    originalMaxHealth + 20
            );

            player.setHealth(
                    Math.min(
                            player.getHealth() + 20,
                            health.getBaseValue()
                    )
            );

        }

        AttributeInstance knockback =
                player.getAttribute(
                        Attribute.GENERIC_KNOCKBACK_RESISTANCE
                );

        if (knockback != null) {

            knockback.setBaseValue(1);

        }

        AttributeInstance speed =
                player.getAttribute(
                        Attribute.GENERIC_MOVEMENT_SPEED
                );

        if (speed != null) {

            speed.setBaseValue(
                    speed.getBaseValue() * 0.8
            );

        }

    }

    private void finish() {

        BossBarManager.remove(player);

        StatusManager.removeStatus(
                player,
                StatusType.GIANT_TRANSFORMATION
        );

        AttributeInstance health =
                player.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                );

        if (health != null) {

            health.setBaseValue(
                    originalMaxHealth
            );

            if (player.getHealth() >
                    originalMaxHealth) {

                player.setHealth(
                        originalMaxHealth
                );

            }

        }

        AttributeInstance knockback =
                player.getAttribute(
                        Attribute.GENERIC_KNOCKBACK_RESISTANCE
                );

        if (knockback != null) {

            knockback.setBaseValue(0);

        }

        AttributeInstance speed =
                player.getAttribute(
                        Attribute.GENERIC_MOVEMENT_SPEED
                );

        if (speed != null) {

            speed.setBaseValue(0.1);

        }

        player.sendTitle(
                "",
                "§8거신의 힘이 사라집니다...",
                0,
                30,
                10
        );

        player.getWorld().spawnParticle(
                Particle.SMOKE,
                player.getLocation(),
                50,
                1,
                1,
                1,
                0.02
        );

        cancel();

    }

    public void start(
            Enginev1 plugin
    ) {

        runTaskTimer(
                plugin,
                0,
                1
        );

    }

}