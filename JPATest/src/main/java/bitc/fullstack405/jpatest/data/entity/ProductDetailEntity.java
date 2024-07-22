package bitc.fullstack405.jpatest.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jpa_product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

//    @Column(nullable = false)
//    private LocalDateTime createAt = LocalDateTime.now();
//
//    private LocalDateTime updatedAt;

//    @JoinColumn: 관계가 설정된 테이블에서 참조키를 설정하는 어노테이션(외래키)
//    name: 외래키 컬럼 이름
    @OneToOne
    @JoinColumn(name = "product_number")
    private ProductEntity product;
}

