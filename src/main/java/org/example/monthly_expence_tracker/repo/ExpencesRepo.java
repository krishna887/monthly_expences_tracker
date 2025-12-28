package org.example.monthly_expence_tracker.repo;

import org.example.monthly_expence_tracker.model.Expences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpencesRepo extends JpaRepository<Expences,Integer> {
}
