package com.netcracker.model;

import javax.persistence.*;


    @Entity
    @Table(name = "cg_status")
    public class Status {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "status_id")
        private Long id;

        @Column (name = "name")
        private String name;

        @Column (name = "description")
        private String description;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }