package com.salma.model;

import javax.persistence.*;

@Entity
@Table(name="APP_PATIENT_DOCUMENT")
public class PatientDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name="description", length=255)
    private String description;

    @Column(name="type", length=100, nullable=false)
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PatientDocument))
            return false;
        PatientDocument other = (PatientDocument) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PatientDocument [id=" + id + ", name=" + name + ", description="
                + description + ", type=" + type + "]";
    }



}
