package es.codeurjc.mca.practica_1_pruebas_ordinaria.event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import es.codeurjc.mca.practica_1_pruebas_ordinaria.ticket.Ticket;
import es.codeurjc.mca.practica_1_pruebas_ordinaria.user.User;

@Entity
public class Event {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getCurrent_capacity() {
        return current_capacity;
    }

    public void setCurrent_capacity(int current_capacity) {
        this.current_capacity = current_capacity;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Event() {
    }

    public interface BasicAtt {}

    public interface OrganizerAtt extends BasicAtt{}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(BasicAtt.class)
    private Long id;

    @JsonView(BasicAtt.class)
    private String name;

    @Column(length = 10000)
    @JsonView(BasicAtt.class)
    private String description;

    @JsonView(BasicAtt.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JsonView(BasicAtt.class)
    private Double price;

    @JsonView(BasicAtt.class)
    private int max_capacity;

    @JsonView(BasicAtt.class)
    private int current_capacity;

    @CreationTimestamp
    @JsonView(BasicAtt.class)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @JsonView(BasicAtt.class)
    private LocalDateTime updateDateTime;

    @ManyToOne
	@JsonView(OrganizerAtt.class)
    private User creator;
    
    @JsonView(BasicAtt.class)
    private String image;

    //@JsonView(OrganizerAtt.class)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Event(String name, String description, Date date, Double price, int max_capacity) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.max_capacity = max_capacity;
        this.current_capacity = 0;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", price="
                + price + ", max_capacity=" + max_capacity + ", current_capacity=" + current_capacity
                + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + ", creator=" + creator
                + ", image=" + image + "]";
    }

    

}
