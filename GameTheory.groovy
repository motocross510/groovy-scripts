random = new Random()

def pickHiderStrat(def w2) {
    def strats = [];
    strats << 2  << 1  << 3
    
    def rnd = Math.random()
    if (rnd <= w2) return strats[0]
    
    def pos = random.nextInt(2) + 1
    return strats[pos]
}

def pickGuesserStrat(def w2) {
    def guesserStrats = []
    guesserStrats << [2] << [1, 2] << [1, 3] << [3, 1] << [3, 2]
    
    def rnd = Math.random()
    if (rnd <= w2) return guesserStrats[0]
    
    def pos = random.nextInt(4) + 1
    return guesserStrats[pos]
}

def results = []
def hider2sTotal
for (def hider2s = 0.0; hider2s <=1; hider2s += 0.1) {
    for (def guessers2s = 0.0; guessers2s <=1; guessers2s += 0.1) {
        def guesserTotal = 0
        def hiderTotal = 0
        println "PLAYING:  G: $guessers2s vs H: $hider2s "
        2000.times {
            def guesserStrat = pickGuesserStrat(guessers2s)
            def hiderStrat   = pickHiderStrat(hider2s)
            def score = guess(hiderStrat, guesserStrat)
            println "$guesserStrat $hiderStrat SCORE:  $score"    
            println "----------------------------"
            guesserTotal += score[0]
            hiderTotal   += score[1]
        }
        results << ["Guesser '2' prob $guessers2s VS Hider '2' prob $hider2s:", "Guesser score: $guesserTotal", "Hider score: $hiderTotal"]
        println "overall: $guessers2s VS $hider2s:  GUESSER: ${guesserTotal} VS HIDER: ${hiderTotal}"
    }
}
results.each {
    println it
}

def guess(def num, def guesserStrat) {
    def first = guesserStrat[0]
    
    if (first == num) {
        return [1,-1]
    }
    
    if (first == 2 && first < num) {
        //println 'HIDER says HIGHER - 3'
        return [0,0]
    }

    if (first == 2 && first > num) {
        //println 'HIDER says LOWER - 1'
        return [0,0]
    }
    
    if (first > num) {
        //println "HIDER says LOWER than $first"
        def second = guesserStrat[1]
        if (second == num) {
            return [0,0]
        } else {
            return [-1, 1]
        }
    }

    if (first < num) {
        //println "HIDER says HIGHER than $first"
        def second = guesserStrat[1]
        if (second == num) {
            return [0,0]
        } else {
            return [-1, 1]
        }
    }
}