package org.yvasilchuk.domain.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class IntegerIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
