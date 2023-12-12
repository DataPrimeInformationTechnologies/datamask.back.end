package com.data.tools.api.entity;

import com.data.tools.api.dto.MigrationConfig;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_db_migration")
public class DbMigration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String description;

    @JoinColumn(name = "source_id")
    @ManyToOne
    private DbConfiguration source;

    @JoinColumn(name = "target_id")
    @ManyToOne
    private DbConfiguration target;

    private boolean selected;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setConfigToEntity(String name, String description, DbConfiguration source, DbConfiguration target) {
        this.name = name;
        this.description = description;
        this.source = source;
        this.target = target;
    }

}
