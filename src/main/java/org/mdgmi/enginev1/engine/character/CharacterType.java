package org.mdgmi.enginev1.engine.character;

public enum CharacterType {

    NONE("없음"),
    KIM_DOKJA("김독자"),
    YOO_JOONGHYUK("유중혁"),
    HAN_SOOYOUNG("한수영"),
    SHIN_YOOSEUNG("신유승"),
    JUNG_HEEWON("정희원");

    private final String displayName;

    CharacterType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}