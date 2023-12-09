package com.data.tools.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name="tbl_db_conf")
@NoArgsConstructor
@AllArgsConstructor
public class DbConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="connection_id")
    private Long id;

    @Column(name = "connection_name",nullable = false)
    private String connectionName;

    @Column(name="url")
    private String url;

    @Column(name="username")
    private String userName;

    @Column(name = "pass")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
