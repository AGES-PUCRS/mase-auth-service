
package br.pucrs.ages.mase.authservice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;

public class RegisterRequestDto {

    @NotEmpty
    private ObjectId userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String role;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    /**
     * @return the userId
     */
    public ObjectId getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
