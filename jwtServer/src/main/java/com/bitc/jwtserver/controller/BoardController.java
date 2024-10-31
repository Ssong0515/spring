package com.bitc.jwtserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardController {


  @GetMapping({"", "/"})
  public ResponseEntity<?> boardList() {
    return ResponseEntity.ok("board list");
  }

  @GetMapping("/{boardIdx}")
  public ResponseEntity<?> boardDetail(@PathVariable int boardIdx) {
    return ResponseEntity.ok("board detail " + boardIdx);
  }

  @PostMapping("/")
  public ResponseEntity<?> boardWrite() {
    return ResponseEntity.ok("board write");
  }

  @PutMapping("/{boardIdx}")
  public ResponseEntity<?> boardUpdate(@PathVariable int boardIdx) {
    return ResponseEntity.ok("board update " + boardIdx);
  }

  @DeleteMapping("/{boardIdx}")
  public ResponseEntity<?> boardDelete(@PathVariable int boardIdx) {
    return ResponseEntity.ok("board delete " + boardIdx);
  }
}












