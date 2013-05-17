(ns bowling-game.score)

(defn safe-sum [& values] (apply + (filter identity values)))

(defn strike-or-spare? [[first-roll second-roll]]
  (>= (safe-sum first-roll second-roll) 10))

(defn score-frame [rolls]
  (let [contributing-rolls (if (strike-or-spare? rolls) 3 2)]
    (apply safe-sum (take contributing-rolls rolls))))

(defn next-frame [[first-roll, second-roll & remaining]]
  (if (= first-roll 10)
    (cons second-roll remaining)
    remaining))

(defn score-game [game]
  (loop [score 0
         remaining-rolls game
         frame 1]
    (if (or (empty? remaining-rolls) (> frame 10) )
      score
      (recur (+ (score-frame remaining-rolls) score)
        (next-frame remaining-rolls)
        (inc frame)))))