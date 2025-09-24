package br.com.andrebastos.javamon_online.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/battles")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @PostMapping("/start")
    public ResponseEntity<BattleLogDto> startBattle(@RequestBody BattleRequestDto request) {
        BattleLogDto result = battleService.startBattle(
                request.getPokemon1Id(),
                request.getPokemon2Id()
        );
        return ResponseEntity.ok(result);
    }
}