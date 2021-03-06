package com.netcracker.model;

import javax.persistence.*;

    @Entity
    @Table(name = "cg_status")
    public class Status {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "status_id")
        private Integer id;

        @Column (name = "name")
        private String name;

        @Column (name = "description")
        private String description;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            if(id != null) {
                this.id = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            if(name != null && !name.isEmpty()) {
                this.name = name;
            }
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            if(description != null && !description.isEmpty()) {
                this.description = description;
            }
        }
    }
