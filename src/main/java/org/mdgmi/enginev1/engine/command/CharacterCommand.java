package org.mdgmi.enginev1.engine.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.character.CharacterManager;
import org.mdgmi.enginev1.engine.character.CharacterType;

public class CharacterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player player))
            return true;

        if (args.length != 1) {

            player.sendMessage(ChatColor.RED +
                    "/character <독자|중혁|수영|유승>");

            return true;
        }

        switch (args[0].toLowerCase()) {

            case "독자":
            case "dokja":
                CharacterManager.selectCharacter(
                        player,
                        CharacterType.KIM_DOKJA
                );
                break;
            case "중혁":
            case "joonghyuk":
                CharacterManager.selectCharacter(
                        player,
                        CharacterType.YOO_JOONGHYUK
                );
                break;

            case "수영":
            case "sooyoung":
                CharacterManager.selectCharacter(
                        player,
                        CharacterType.HAN_SOOYOUNG
                );
                break;

            case "유승":
            case "yooseung":
                CharacterManager.selectCharacter(
                        player,
                        CharacterType.SHIN_YOOSEUNG
                );
                break;
            case "희원":
            case "heewon":
                CharacterManager.selectCharacter(
                        player,
                        CharacterType.JUNG_HEEWON
                );
                break;
            default:
                player.sendMessage(
                        ChatColor.RED + "존재하지 않는 캐릭터입니다."
                );

        }

        return true;
    }

}