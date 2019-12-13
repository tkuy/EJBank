package com.ejbank.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ejbank_advisor")
@DiscriminatorValue("advisor")
public class AdvisorEntity extends UserEntity {

}
