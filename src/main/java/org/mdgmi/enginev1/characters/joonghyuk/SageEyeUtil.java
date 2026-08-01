package org.mdgmi.enginev1.characters.joonghyuk;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.skill.Skill;

public final class SageEyeUtil {

    private SageEyeUtil() {
    }

    public static void showInfo(
            Player viewer,
            Player target
    ) {

        PlayerData data =
                DataManager.get(target);

        viewer.sendMessage("§6========== 현자의 눈 ==========");
        viewer.sendMessage("§e대상 §7: §f" + target.getName());
        viewer.sendMessage("§e캐릭터 §7: §f" + data.getCharacterType());

        viewer.sendMessage("§e보유 스킬");

        for (Skill skill : data.getSkills().getAllSkills()) {

            viewer.sendMessage(" §7- §f" + skill.getName());

        }

        viewer.sendMessage("§6==============================");

    }

}