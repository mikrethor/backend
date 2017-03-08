package fr.ablx.daycare.objects;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Credentials {

    @NonNull
    private String login;
    @NonNull
    private String password;
}
