package org.mdgmi.enginev1.characters.sooyoung;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.scheduler.BukkitTask;
import org.mdgmi.enginev1.Enginev1;
import org.mdgmi.enginev1.engine.npc.AvatarData;
import org.mdgmi.enginev1.engine.npc.NPCManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AvatarManager {

    private static final Map<UUID, AvatarData> AVATARS =
            new HashMap<>();

    private static final Map<UUID, BukkitTask> REMOVE_TASKS =
            new HashMap<>();

    private AvatarManager() {
    }

    public static void spawnAvatar(
            Player owner,
            int level
    ) {

        // 기존 아바타와 기존 삭제 예약 정리
        removeAvatar(owner);

        NPC npc =
                NPCManager.createPlayerNPC(
                        owner.getName() + "의 아바타",
                        owner.getLocation()
                );

        SkinTrait skinTrait =
                npc.getOrAddTrait(
                        SkinTrait.class
                );

        skinTrait.setSkinName(
                owner.getName()
        );

        if (!(npc.getEntity() instanceof LivingEntity avatarEntity)) {

            npc.destroy();
            owner.sendMessage("§c아바타 생성에 실패했습니다.");
            return;
        }

        copyEquipment(
                owner,
                avatarEntity
        );

        AttributeInstance maxHealth =
                avatarEntity.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                );

        if (maxHealth != null) {

            maxHealth.setBaseValue(20.0);

            avatarEntity.setHealth(
                    maxHealth.getValue()
            );
        }

        AvatarData avatarData =
                new AvatarData(
                        owner,
                        npc,
                        level
                );

        AVATARS.put(
                owner.getUniqueId(),
                avatarData
        );

        BukkitTask removalTask =
                Bukkit.getScheduler().runTaskLater(
                        Enginev1.getInstance(),
                        () -> {

                            /*
                             * 예약 시점과 현재 아바타가 같은지 확인.
                             * 중간에 새 아바타를 소환했으면
                             * 이전 예약이 새 아바타를 삭제하지 않는다.
                             */
                            AvatarData current =
                                    AVATARS.get(
                                            owner.getUniqueId()
                                    );

                            if (current != avatarData)
                                return;

                            removeAvatar(owner);

                            if (owner.isOnline()) {

                                owner.sendMessage(
                                        "§7아바타가 소멸했습니다."
                                );
                            }
                        },
                        20L * 180L
                );

        REMOVE_TASKS.put(
                owner.getUniqueId(),
                removalTask
        );

        owner.sendMessage(
                "§5『아바타』가 생성되었습니다."
        );
    }

    private static void copyEquipment(
            Player owner,
            LivingEntity avatar
    ) {

        EntityEquipment ownerEquipment =
                owner.getEquipment();

        EntityEquipment avatarEquipment =
                avatar.getEquipment();

        if (avatarEquipment == null)
            return;

        avatarEquipment.setHelmet(
                cloneItem(ownerEquipment.getHelmet())
        );

        avatarEquipment.setChestplate(
                cloneItem(ownerEquipment.getChestplate())
        );

        avatarEquipment.setLeggings(
                cloneItem(ownerEquipment.getLeggings())
        );

        avatarEquipment.setBoots(
                cloneItem(ownerEquipment.getBoots())
        );

        avatarEquipment.setItemInMainHand(
                cloneItem(ownerEquipment.getItemInMainHand())
        );

        avatarEquipment.setItemInOffHand(
                cloneItem(ownerEquipment.getItemInOffHand())
        );
    }

    private static org.bukkit.inventory.ItemStack cloneItem(
            org.bukkit.inventory.ItemStack item
    ) {

        return item == null
                ? null
                : item.clone();
    }

    public static AvatarData getAvatar(
            Player owner
    ) {

        return AVATARS.get(
                owner.getUniqueId()
        );
    }

    public static boolean hasAvatar(
            Player owner
    ) {

        AvatarData data =
                getAvatar(owner);

        return data != null
                && data.getNpc() != null
                && data.getNpc().isSpawned();
    }

    public static Collection<AvatarData> getAvatars() {

        /*
         * Task 순회 중 removeAvatar가 호출되어도
         * ConcurrentModificationException이 발생하지 않게 복사본 반환
         */
        return new ArrayList<>(
                AVATARS.values()
        );
    }

    public static void setTarget(
            Player owner,
            LivingEntity target
    ) {

        AvatarData data =
                getAvatar(owner);

        if (data == null)
            return;

        data.setTarget(target);
    }

    public static void removeAvatar(
            Player owner
    ) {

        removeAvatar(
                owner.getUniqueId()
        );
    }

    public static void removeAvatar(
            UUID ownerUuid
    ) {

        BukkitTask task =
                REMOVE_TASKS.remove(
                        ownerUuid
                );

        if (task != null
                && !task.isCancelled()) {

            task.cancel();
        }

        AvatarData data =
                AVATARS.remove(
                        ownerUuid
                );

        if (data == null)
            return;

        data.setTarget(null);

        NPC npc =
                data.getNpc();

        if (npc != null) {

            if (npc.isSpawned()) {

                npc.getNavigator().cancelNavigation();
            }

            NPCManager.remove(npc);
        }
    }

    public static void removeAll() {

        for (UUID ownerUuid :
                new ArrayList<>(AVATARS.keySet())) {

            removeAvatar(ownerUuid);
        }

        REMOVE_TASKS.clear();
    }
}