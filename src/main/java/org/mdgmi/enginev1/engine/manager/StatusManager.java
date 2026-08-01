package org.mdgmi.enginev1.engine.manager;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.status.StatusInstance;
import org.mdgmi.enginev1.engine.status.StatusType;
import org.mdgmi.enginev1.engine.skill.SkillId;

public class StatusManager {

    public static void addStatus(
            Player player,
            StatusType type,
            int duration,
            int amplifier
    ) {

        // 제4의 벽
        if (DataManager.get(player)
                .getSkills()
                .hasSkill(SkillId.FOURTH_WALL)) {

            switch (type) {

                case FEAR:
                case CONFUSION:
                    player.sendMessage("§7『제4의 벽』이 정신 간섭을 차단했습니다.");
                    return;

                case STUN:
                    duration /= 2;
                    break;

                default:
                    break;
            }
        }

        DataManager.get(player)
                .getStatuses()
                .add(new StatusInstance(
                        type,
                        duration,
                        amplifier
                ));
    }

    public static boolean hasStatus(
            Player player,
            StatusType type
    ) {

        return DataManager.get(player)
                .getStatuses()
                .has(type);

    }

    public static void removeStatus(
            Player player,
            StatusType type
    ) {

        DataManager.get(player)
                .getStatuses()
                .remove(type);

    }
    public static StatusInstance getStatus(
            Player player,
            StatusType type
    ) {

        return DataManager.get(player)
                .getStatuses()
                .get(type);

    }
}