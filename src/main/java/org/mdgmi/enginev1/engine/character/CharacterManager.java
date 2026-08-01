package org.mdgmi.enginev1.engine.character;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.player.PlayerData;
import org.mdgmi.enginev1.engine.charactertree.CharacterTree;

public class CharacterManager {

    public static void selectCharacter(
            Player player,
            CharacterType type
    ) {

        PlayerData data =
                DataManager.get(player);

        data.setCharacterType(type);

        data.getSkills().clear();

        CharacterTree tree =
                CharacterRegistry.get(type);

        if(tree == null){

            player.sendMessage(
                    "§c등록되지 않은 캐릭터입니다."
            );

            return;

        }

        tree.getSkills()
                .forEach(data.getSkills()::addSkill);

        player.sendMessage(
                "§a캐릭터 [" +
                        tree.getName() +
                        "] 선택 완료!"
        );

    }

}