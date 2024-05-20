package org.example.model;

import lombok.*;
import org.example.enumerators.ComputerState;
import org.example.enumerators.UserState;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User extends BaseModel{
    private String firstName;
    private String username;
    private String phoneNumber;
    private Long chatId;
    private Location location;
    private UserState userState;
    private ComputerState state;
    private UUID startedProduct;
}
