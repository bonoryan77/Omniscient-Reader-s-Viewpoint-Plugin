package org.mdgmi.enginev1.characters.sooyoung;

import net.citizensnpcs.api.ai.Navigator;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.mdgmi.enginev1.engine.manager.CombatManager;
import org.mdgmi.enginev1.engine.npc.AvatarData;

import java.util.Objects;


public class AvatarTask extends BukkitRunnable {

    private static final double FOLLOW_DISTANCE_SQUARED = 9.0;
    private static final double TELEPORT_DISTANCE_SQUARED = 400.0;
    private static final double ATTACK_DISTANCE_SQUARED = 7.84;
    private static final long ATTACK_DELAY_MILLIS = 700L;

    @Override
    public void run() {

        for (AvatarData data : AvatarManager.getAvatars()) {

            Player owner = data.getOwner();

            if (!owner.isOnline() || owner.isDead()) {

                AvatarManager.removeAvatar(owner);
                continue;
            }

            NPC npc = data.getNpc();

            if (npc == null || !npc.isSpawned())
                continue;

            if (!(npc.getEntity() instanceof LivingEntity avatar))
                continue;

            /*
             * 다른 월드에 있으면 주인에게 이동
             */
            if (!avatar.getWorld().equals(owner.getWorld())) {

                npc.teleport(
                        owner.getLocation(),
                        org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.PLUGIN
                );

                continue;
            }

            Navigator navigator = npc.getNavigator();

            LivingEntity target = data.getTarget();

            /*
             * 죽었거나 유효하지 않은 대상 제거
             */
            if (target != null
                    && (!target.isValid()
                    || target.isDead()
                    || target.equals(owner)
                    || target.equals(avatar))) {

                data.setTarget(null);
                target = null;
            }

            /*
             * 공격 대상이 있을 때
             */
            if (target != null) {

                double targetDistance =
                        avatar.getLocation()
                                .distanceSquared(
                                        target.getLocation()
                                );

                /*
                 * 대상을 추적
                 */
                if (targetDistance > ATTACK_DISTANCE_SQUARED) {

                    navigator.setTarget(
                            target,
                            true
                    );

                    continue;
                }

                /*
                 * 공격 거리 안에 도달
                 */
                navigator.cancelNavigation();

                long now =
                        System.currentTimeMillis();

                if (now - data.getLastAttack()
                        < ATTACK_DELAY_MILLIS) {

                    continue;
                }

                data.setLastAttack(now);

                AttributeInstance attackAttribute =
                        owner.getAttribute(
                                Attribute.GENERIC_ATTACK_DAMAGE
                        );

                double baseDamage =
                        attackAttribute == null
                                ? 1.0
                                : attackAttribute.getValue();

                /*
                 * 아바타 피해는 본체 공격력의 50%
                 */
                double avatarBaseDamage =
                        baseDamage * 0.5;

                double finalDamage =
                        CombatManager.attack(
                                owner,
                                target,
                                avatarBaseDamage
                        );

                CombatManager.damage(
                        owner,
                        target,
                        finalDamage
                );

                /*
                 * NPC 공격 모션
                 */
                if (npc.getEntity() instanceof Player avatarPlayer) {

                    avatarPlayer.swingMainHand();
                }

                continue;
            }

            /*
             * 타겟이 없으면 주인을 따라감
             */
            Location ownerLocation =
                    owner.getLocation();

            double ownerDistance =
                    avatar.getLocation()
                            .distanceSquared(
                                    ownerLocation
                            );

            /*
             * 너무 멀어지면 순간이동
             */
            if (ownerDistance
                    >= TELEPORT_DISTANCE_SQUARED) {

                navigator.cancelNavigation();

                npc.teleport(
                        ownerLocation,
                        org.bukkit.event.player.PlayerTeleportEvent.TeleportCause.PLUGIN
                );
                continue;
            }

            /*
             * 3블록 이상 떨어지면 추적
             */
            if (ownerDistance
                    > FOLLOW_DISTANCE_SQUARED) {

                navigator.setTarget(
                        owner,
                        false
                );

            } else {

                navigator.cancelNavigation();
            }
            EntityEquipment npcEq =
                    ((LivingEntity) npc.getEntity()).getEquipment();

            PlayerInventory inv =
                    owner.getInventory();

            Objects.requireNonNull(npcEq).setItemInMainHand(inv.getItemInMainHand());

            npcEq.setHelmet(inv.getHelmet());
            npcEq.setChestplate(inv.getChestplate());
            npcEq.setLeggings(inv.getLeggings());
            npcEq.setBoots(inv.getBoots());
        }

    }
}