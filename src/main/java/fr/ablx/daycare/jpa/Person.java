package fr.ablx.daycare.jpa;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person implements Serializable {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

}
