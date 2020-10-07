package com.ishan.rd.beorg.domain.entities;

import com.ishan.rd.beorg.domain.dto.UserSummary;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;


@Getter @Setter
@Document(collection = "users")
public class User extends BaseDocument{

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String password;

    @DBRef
    private Collection<Role> roles;

    private String email;

    public UserSummary toUserSummary() {
        UserSummary userSummary = new UserSummary();
        userSummary.setEmail(this.email);
        userSummary.setUserId(this._id);
        return userSummary;
    }
}
