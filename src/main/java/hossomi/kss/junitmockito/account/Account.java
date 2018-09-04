package hossomi.kss.junitmockito.account;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class Account {

    @Id
    private String id;

    @Column(nullable = false)
    private Double balance;
}
