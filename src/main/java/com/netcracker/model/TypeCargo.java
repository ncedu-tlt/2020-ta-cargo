package com.netcracker.model;

import javax.persistence.*;

    @Entity
    @Table(name = "cg_type_cargo")
    public class TypeCargo {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "type_id")
        private Integer typeId;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            if(typeId != null) {
                this.typeId = typeId;
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

