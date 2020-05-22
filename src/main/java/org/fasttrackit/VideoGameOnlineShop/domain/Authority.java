package org.fasttrackit.VideoGameOnlineShop.domain;

import org.fasttrackit.VideoGameOnlineShop.config.Security.AuthorityType;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private AuthorityType name;
    public Authority(){

    }
    public Authority(AuthorityType name){
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }
}
