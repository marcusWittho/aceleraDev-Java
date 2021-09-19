package com.challenge.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubmissionId implements Serializable {

  @ManyToOne
  private User user;

  @ManyToOne
  private Challenge challenge;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SubmissionId that = (SubmissionId) o;
    return Objects.equals(user, that.user) && Objects.equals(challenge, that.challenge);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, challenge);
  }
}
