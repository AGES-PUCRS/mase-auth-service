
package br.pucrs.ages.mase.authservice.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import br.pucrs.ages.mase.authservice.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterRequestDto {

    @NotEmpty
    @Length(min = 6, max = 64)
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private Role role;

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
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
