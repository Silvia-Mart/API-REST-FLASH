package com.apirest.flash.models;

import com.sun.istack.NotNull;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_PARTNERS")
public class Partners {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @Lob
    private String linkImage;

    @ManyToMany(mappedBy = "partners")
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
