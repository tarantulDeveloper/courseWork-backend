package kg.beaver.warehouse.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="materials",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name="category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    private double price;
    private String photo;
    private String description;
    private String size;
    private int quantity;
}
