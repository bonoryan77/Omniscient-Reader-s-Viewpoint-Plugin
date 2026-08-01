package org.mdgmi.enginev1.engine.player;

import org.mdgmi.enginev1.engine.character.CharacterType;
import org.mdgmi.enginev1.engine.cooldown.CooldownContainer;
import org.mdgmi.enginev1.engine.hidden.HiddenId;
import org.mdgmi.enginev1.engine.player.progress.SkillProgressContainer;
import org.mdgmi.enginev1.engine.skill.SkillContainer;
import org.mdgmi.enginev1.engine.status.StatusContainer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    private CharacterType characterType = CharacterType.NONE;

    /*
     * 기본 자원
     */


    private int plausibility = 100;
    private int serverplausibility = 100;
    private int maxserverplausibility = 100;
    /*
     * 시스템
     */

    private final SkillContainer skills =
            new SkillContainer();

    private final SkillProgressContainer progress =
            new SkillProgressContainer();

    private final StatusContainer status =
            new StatusContainer();

    private final CooldownContainer cooldown =
            new CooldownContainer();

    /*
     * 특수 상태
     */

    private boolean omniscientUsed;

    public PlayerData(UUID uuid) {

        this.uuid = uuid;

    }

    public UUID getUuid() {

        return uuid;

    }

    public CharacterType getCharacterType() {

        return characterType;

    }

    public void setCharacterType(
            CharacterType type
    ) {

        this.characterType = type;

    }

    public int getPlausibility() {

        return plausibility;

    }

    public void setPlausibility(
            int plausibility
    ) {

        this.plausibility = plausibility;

    }

    public boolean consumePlausibility(
            int amount
    ) {

        if(plausibility < amount)
            return false;

        plausibility -= amount;

        return true;

    }

    public void restorePlausibility() {

        plausibility = 100;

    }

    public SkillContainer getSkills() {

        return skills;

    }

    public SkillProgressContainer getProgress() {

        return progress;

    }

    public StatusContainer getStatus() {

        return status;

    }

    public CooldownContainer getCooldown() {

        return cooldown;

    }

    public boolean isOmniscientUsed() {

        return omniscientUsed;

    }

    private final StatusContainer statusContainer =
            new StatusContainer();

    public StatusContainer getStatuses() {
        return statusContainer;
    }
    private boolean regressionUsed;
    public boolean isRegressionUsed() {
        return regressionUsed;
    }

    public void setRegressionUsed(boolean regressionUsed) {
        this.regressionUsed = regressionUsed;
    }
    private boolean giantTransformationUsed;

    public boolean isGiantTransformationUsed() {
        return giantTransformationUsed;
    }

    public void setGiantTransformationUsed(boolean used) {
        this.giantTransformationUsed = used;
    }
    private final Set<HiddenId> hiddenSkills =
            new HashSet<>();
    public boolean hasHidden(HiddenId id) {
        return hiddenSkills.contains(id);
    }

    public void unlockHidden(HiddenId id) {
        hiddenSkills.add(id);
    }

    public Set<HiddenId> getHiddenSkills() {
        return hiddenSkills;
    }
}