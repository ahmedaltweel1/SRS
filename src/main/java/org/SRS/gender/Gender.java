package org.SRS.gender;


import javax.persistence.*;

@Entity
@Table(name="genders")
public class Gender {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="gender_name",nullable=false,unique=true)
    private String gender_name;

    public Gender(){}
    public Gender(String genderName) {
        gender_name = genderName;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }
}
