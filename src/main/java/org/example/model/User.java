package org.example.model;

import lombok.*;
import org.example.enumerators.UserState;

import javax.xml.stream.Location;

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
}
