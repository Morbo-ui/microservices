package com.dmitrii.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
	
	@Id
	@SequenceGenerator(
			name = "notification_id_sequence",
			sequenceName = "notification_id_sequence"
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "notification_id_sequence"
	)
	private Integer notificationId;
	private Integer toCustomerId;
	private String toCustomerEmail;
	private String sender;
	private String message;
	private LocalDateTime sentAt;
}
