package org.mdgmi.enginev1;

import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;
import org.mdgmi.enginev1.characters.dokja.*;
import org.mdgmi.enginev1.characters.heewon.JungHeewonTree;
import org.mdgmi.enginev1.characters.heewon.ShinsalSkill;
import org.mdgmi.enginev1.characters.heewon.TimeOfJudgmentSkill;
import org.mdgmi.enginev1.characters.joonghyuk.*;
import org.mdgmi.enginev1.characters.sooyoung.*;
import org.mdgmi.enginev1.engine.character.CharacterRegistry;
import org.mdgmi.enginev1.engine.character.CharacterType;
import org.mdgmi.enginev1.engine.charactertree.CharacterTree;
import org.mdgmi.enginev1.engine.command.CharacterCommand;
import org.mdgmi.enginev1.engine.gui.GUIListener;
import org.mdgmi.enginev1.engine.gui.transcendent.TranscendentListener;
import org.mdgmi.enginev1.engine.listener.CombatListener;
import org.mdgmi.enginev1.engine.listener.JoinListener;
import org.mdgmi.enginev1.engine.listener.QuitListener;
import org.mdgmi.enginev1.engine.listener.SkillListener;
import org.mdgmi.enginev1.engine.skill.SkillRegistry;
import org.mdgmi.enginev1.engine.task.PlausibilityTask;
import org.mdgmi.enginev1.engine.task.ScoreboardTask;
import org.mdgmi.enginev1.engine.task.StatusTickTask;

public final class Enginev1 extends JavaPlugin {
    private static Enginev1 instance;

    public static Enginev1 getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance=this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(
                new JoinListener(),
                this
        );

        getServer().getPluginManager().registerEvents(
                new QuitListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new CombatListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new SkillListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new RegressionListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new GUIListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new TranscendentListener(),
                this
        );
        new PlausibilityTask().runTaskTimer(
                this,
                20L,
                20L
        );
        new ScoreboardTask().runTaskTimer(
                this,
                20L,
                10L
        );
        new StatusTickTask().runTaskTimer(
                this,
                1L,
                1L
        );
        new AvatarTask().runTaskTimer(
                this,
                20L,
                5L
        );
        getCommand("character")
                .setExecutor(
                        new CharacterCommand()
                );
        //스킬등록
        SkillRegistry.register(
                new ElectrificationSkill()
        );

        SkillRegistry.register(
                new FourthWallSkill()
        );

        SkillRegistry.register(
                new OmniscientViewpointSkill()
        );
        SkillRegistry.register(
                new VermilionStepSkill()
        );
        SkillRegistry.register(
                new SkyBreakingSwordSkill()
        );
        SkillRegistry.register(
                new TimeOfJudgmentSkill()
        );
        SkillRegistry.register(
                new GiantTransformationSkill()
        );
        SkillRegistry.register(
                new ShinsalSkill()
        );
        SkillRegistry.register(
                new BookmarkSkill()
        );
        SkillRegistry.register(
                new AvatarSkill()
        );
        SkillRegistry.register(
                new PlagiarismSkill()
        );
        SkillRegistry.register(
                new TranscedentSkill()
        );
        //캐릭터 등록
        CharacterRegistry.register(
                CharacterType.KIM_DOKJA,
                new KimDokjaTree()
        );

        CharacterRegistry.register(
                CharacterType.YOO_JOONGHYUK,
                new YooJoonghyukTree()
        );
        CharacterRegistry.register(
                CharacterType.JUNG_HEEWON,
                new JungHeewonTree()
        );
        CharacterRegistry.register(
                CharacterType.HAN_SOOYOUNG,
                (CharacterTree)new HanSooyoungTree()
        );
        /*

        CharacterRegistry.register(
                CharacterType.SHIN_YOOSEUNG,
                (CharacterTree)new ShinYooseungTree()
        );

         */


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        AvatarManager.removeAll();
    }
}
