package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Challenge {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotNull
  @Size(max = 100)
  @Column(length = 100)
  private String name;

  @NotNull
  @Size(max = 50)
  @Column(length = 100)
  private String slug;

  @CreatedDate
  private LocalDateTime createdAt;

  @OneToMany
  private List<Acceleration> accelerations;

  @OneToMany
  private List<Submission> submissions;
}
