/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.moc.eca.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alan
 */
@Entity
@XmlRootElement
@NamedQueries( {
    @NamedQuery(name = "Group.getAllByUser",query = "SELECT g FROM MocGroup g, MocUser u WHERE g IN (u.groups) and u = :user")
})
public class MocGroup implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotNull
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(nullable = false)
    private Date initDate;
    
    @ManyToMany
    @JoinTable(name="user_group", joinColumns=
      {@JoinColumn(name="user_id")}, inverseJoinColumns=
        {@JoinColumn(name="group_id")})
    private List<MocUser> users;

    public MocGroup(String name) {
        this.name = name;
        this.initDate = new Date(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Long getId() {
        return id;
    }

    public MocGroup() {
    }

    public List<MocUser> getUsers() {
        return users;
    }

    public void setUsers(List<MocUser> users) {
        this.users = users;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.initDate != null ? this.initDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MocGroup other = (MocGroup) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.initDate != other.initDate && (this.initDate == null || !this.initDate.equals(other.initDate))) {
            return false;
        }
        return true;
    }
    
    
    
}
