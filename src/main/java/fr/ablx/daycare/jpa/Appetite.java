package fr.ablx.daycare.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Appetite {
    GOOD(10),
    MEDIUM(5),
    BAD(0);

    @Getter
    Integer level;

    public static Appetite fromCode(Integer level) {
        switch (level) {
            case 0:
                return BAD;
            case 5:
                return MEDIUM;
            case 10:
                return GOOD;

            default:
                // do we need this? if for nothing else it catches forgotten case
                // when enum is modified
                throw new IllegalArgumentException("Invalid level " + level);
        }

    }

}
