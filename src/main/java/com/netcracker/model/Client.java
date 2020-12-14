package com.netcracker.model;

public class Client {
    private Integer userId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    private String driveCategory;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        if (userId != null)  {
            this.userId = userId;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if ((lastName != null)&&(!lastName.isEmpty())) {
            this.lastName = lastName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if ((firstName != null)&&(!firstName.isEmpty())) {
            this.firstName = firstName;
        }
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if ((middleName != null)&&(!middleName.isEmpty())) {
            this.middleName = middleName;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if ((phone != null)&&(!phone.isEmpty())) {
            this.phone = phone;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if ((email != null)&&(!email.isEmpty())) {
            this.email = email;
        }
    }

    public String getDriveCategory() {
        return driveCategory;
    }

    public void setDriveCategory(String driveCategory) {
        if ((driveCategory != null)&&(!driveCategory.isEmpty())) {
            this.driveCategory = driveCategory;
        }
    }
}
