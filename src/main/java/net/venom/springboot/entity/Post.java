package net.venom.springboot.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(name = "url")
	private String url;
	
	
	@Column(nullable = false, columnDefinition = "longtext")
	private String content;
	
	@Column(name = "shortDescription")
	private String shortDescription;
	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime updatedOn;

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private Set<Comment> comments =new HashSet<>();


	
}
