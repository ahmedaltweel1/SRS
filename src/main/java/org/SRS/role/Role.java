package org.SRS.role;


import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="role_name",unique=true,nullable=false)
    private String role_name;


    public Role(){}
    public Role(String roleName) {
        this.role_name = roleName;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
