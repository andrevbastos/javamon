package br.com.andrebastos.javamon_online.move;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.andrebastos.javamon_online.move.Move;

public interface MoveRepository extends JpaRepository<Move, Long> {
}