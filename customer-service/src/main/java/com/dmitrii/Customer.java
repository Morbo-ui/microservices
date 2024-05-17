package com.dmitrii;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@SequenceGenerator(
			name = "customer_id_sequence",
			sequenceName = "customer_id_sequence"
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "customer_id_sequence"
	)
	Integer id;
	String firstName;
	String lastName;
	String email;
}
