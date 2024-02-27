package org.tku.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "announcement")
@Table(name = "announcement", schema = "public", catalog = "tku2023")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Integer announcement_id;

    @Column(name="username")
    private String username;

    @Column(name="atype")
    private String atype;

    @Column(name="atitle")
    private String atitle;

    @Column(name="acontent")
    private String acontent;
}
