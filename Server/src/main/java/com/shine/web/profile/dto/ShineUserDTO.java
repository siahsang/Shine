package com.shine.web.profile.dto;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class ShineUserDTO {
    private String name;

    private String family;

    private String login;

    private String email;

    private String clearTextPassword;

    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class ShineUserDTOBuilder {
        private ShineUserDTO shineUserDTO;

        private ShineUserDTOBuilder() {
            shineUserDTO = new ShineUserDTO();
        }

        public static ShineUserDTOBuilder aShineUserDTO() {
            return new ShineUserDTOBuilder();
        }

        public ShineUserDTOBuilder withName(String name) {
            shineUserDTO.setName(name);
            return this;
        }

        public ShineUserDTOBuilder withFamily(String family) {
            shineUserDTO.setFamily(family);
            return this;
        }

        public ShineUserDTOBuilder withUserName(String userName) {
            shineUserDTO.setLogin(userName);
            return this;
        }

        public ShineUserDTOBuilder withPassword(String password) {
            shineUserDTO.setClearTextPassword(password);
            return this;
        }

        public ShineUserDTOBuilder withConfirmPassword(String confirmPassword) {
            shineUserDTO.setConfirmPassword(confirmPassword);
            return this;
        }

        public ShineUserDTOBuilder withEmail(String email) {
            shineUserDTO.setEmail(email);
            return this;
        }

        public ShineUserDTO build() {
            return shineUserDTO;
        }
    }
}
