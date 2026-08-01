package org.mdgmi.enginev1.characters.dokja;

import org.bukkit.entity.Player;
import org.mdgmi.enginev1.engine.player.DataManager;
import org.mdgmi.enginev1.engine.skill.Skill;
import org.mdgmi.enginev1.engine.skill.SkillId;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BookmarkManager {

    /*
     * 김독자 UUID → 현재 책갈피로 복사한 스킬 ID
     */
    private static final Map<UUID, SkillId> BOOKMARKS =
            new HashMap<>();

    private BookmarkManager() {
    }

    public static SkillId getBookmarkedSkill(
            Player player
    ) {

        return BOOKMARKS.get(
                player.getUniqueId()
        );
    }

    public static boolean hasBookmark(
            Player player
    ) {

        return BOOKMARKS.containsKey(
                player.getUniqueId()
        );
    }

    public static void setBookmark(
            Player player,
            Skill skill
    ) {

        removeBookmark(player);

        DataManager.get(player)
                .getSkills()
                .addSkill(skill);

        BOOKMARKS.put(
                player.getUniqueId(),
                skill.getId()
        );

        player.sendMessage(
                "§6『책갈피』 §f"
                        + skill.getName()
                        + "§7을 기록했습니다."
        );
    }

    public static void removeBookmark(
            Player player
    ) {

        SkillId previous =
                BOOKMARKS.remove(
                        player.getUniqueId()
                );

        if (previous == null)
            return;

        DataManager.get(player)
                .getSkills()
                .removeSkill(previous);
    }

    public static void clear(
            Player player
    ) {

        removeBookmark(player);
    }
}