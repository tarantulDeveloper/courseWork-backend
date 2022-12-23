package kg.beaver.warehouse.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name= "order_id")
    private String name;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
