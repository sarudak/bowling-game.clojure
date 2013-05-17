(ns test.score
  (:use midje.sweet
        bowling-game.score))

(fact "When scoring a game with no spares or strikes the score is the sum of all rolls"
  (score-game (repeat 20 1)) => 20
  (score-game [4 3 6 1 0 7 3 5 5 4 9 0 8 1 3 3 4 4 1 0]) => 71)

(fact "When scoring a game with a spare the next rolls score is added to the frame with the spare"
  (score-game [4 6 6 1 0 7 3 5 5 4 9 0 8 1 3 3 4 4 1 0]) => 80
  (score-game [4 6 6 ]) => 22
  )

(fact "When scoring a game with a strike the next two rolls score are added to the frame with the strike"
  (score-game [10 6 1 0 7 3 5 5 4 9 0 8 1 10 4 4 1 0]) => 93
  )

(fact "Frames after the tenth are only counted by strikes and spares"
  (score-game [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 3]) => 13
  (score-game [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 3 6]) => 19
  )

(fact "When scoring a perfect game the score is 300"
  (score-game (repeat 12 10)) => 300)
