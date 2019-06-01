package br.pucrs.ages.mase.authservice.entity;

import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.pucrs.ages.mase.authservice.model.Role;

@Document(collection = "auths")
public class AuthEntity {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Field
    private Role role = Role.USER;

    @Field
    private boolean deleted = false;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
