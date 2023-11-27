package online.shop.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User {

    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private Boolean archive;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_del")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> userOrders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;
}
