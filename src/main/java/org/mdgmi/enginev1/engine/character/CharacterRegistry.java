package org.mdgmi.enginev1.engine.character;

import org.mdgmi.enginev1.engine.charactertree.CharacterTree;

import java.util.EnumMap;
import java.util.Map;

public class CharacterRegistry {

    private static final Map<
            CharacterType,
            CharacterTree
            > registry =
            new EnumMap<>(CharacterType.class);

    public static void register(
            CharacterType type,
            CharacterTree tree
    ) {

        registry.put(
                type,
                tree
        );

    }

    public static CharacterTree get(
            CharacterType type
    ) {

        return registry.get(type);

    }

}