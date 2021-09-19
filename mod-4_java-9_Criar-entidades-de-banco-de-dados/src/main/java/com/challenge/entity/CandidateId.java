package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateId implements Serializable {

  @ManyToOne
  private User user;

  @ManyToOne
  private Acceleration acceleration;

  @ManyToOne
  private Company company;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CandidateId that = (CandidateId) o;
    return Objects.equals(user, that.user) && Objects.equals(acceleration, that.acceleration) && Objects.equals(company, that.company);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, acceleration, company);
  }
}
