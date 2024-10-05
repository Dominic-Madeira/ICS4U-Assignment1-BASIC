/**
* This program calculates the heating time of a food
*
* By:      Dominic M.
* Version: 1.0
* Since:   2024-09-26
*/

import { createPrompt } from 'bun-promptx'
// Global variables to be used throughout the program
const gridSize = 10
const numMugwumps = 4
const maxTries = 10
let mugwumps: number[][] = new Array(numMugwumps).fill(0).map(() => [0, 0])

console.log('THE OBJECT OF THIS GAME IS TO FIND FOUR MUGWUMPS')
console.log('HIDDEN ON A 10 BY 10 GRID. HOMEBASE IS POSITION 0,0')
console.log('ANY GUESS YOU MAKE MUST BE TWO NUMBERS WITH EACH')
console.log('NUMBER BETWEEN 0 AND 9; INCLUSIVE. FIRST NUMBER')
console.log('IS DISTANCE TO RIGHT OF HOMEBASE AND SECOND NUMBER')
console.log('IS DISTANCE ABOVE HOMEBASE.')
console.log()
console.log('YOU GET 10 TRIES. AFTER EACH TRY, I WILL TELL')
console.log('YOU HOW FAR YOU ARE FROM EACH MUGWUMP.')
console.log()

do {
    playGame()
    console.log("THAT WAS FUN! LET'S PLAY AGAIN...")
    console.log('FOUR NEW MUGWUMPS ARE NOW IN HIDING.')
} while (true)

function playGame() {
    placeMugwumps()
    let tries = 0

    while (tries < maxTries) {
        tries++
        console.log()
        console.log(`TURN NO. ${tries} WHAT IS YOUR GUESS?`)
        let xCoordinate = createPrompt('WHAT IS THE X COORDINATE? ')
        let yCoordinate = createPrompt('WHAT IS THE Y COORDINATE? ')

        let foundAll = true

        for (let counter = 0; counter < numMugwumps; counter++) {
            if (mugwumps[counter][0] == -1) {
                continue
            }
            if (mugwumps[counter][0] == xCoordinate && mugwumps[counter][1] == yCoordinate) {
                mugwumps[counter][0] = -1
                console.log(`YOU HAVE FOUND MUGWUMP ${counter + 1}`)
            } else {
                console.log(mugwumps[counter][0])
                console.log(xCoordinate)
                console.log(mugwumps[counter][1])
                console.log(yCoordinate)
                let distance = Math.sqrt(Math.pow(mugwumps[counter][0] - xCoordinate, 2) + Math.pow(mugwumps[counter][1] - yCoordinate, 2))
                console.log(distance)
                console.log(`YOU ARE ${distance.toFixed(1)} UNITS FROM MUGWUMP ${counter + 1}`)
                foundAll = false
            }
        }

        if (foundAll) {
            console.log()
            console.log(`YOU GOT THEM ALL IN ${tries} TURNS!`)
            return
        }
    }

    console.log()
    console.log("SORRY, THAT'S 10 TRIES. HERE IS WHERE THEY'RE HIDING:")
    for (let counter = 0; counter < numMugwumps; counter++) {
        if (mugwumps[counter][0] != -1) {
            console.log(`MUGWUMP ${counter + 1} IS AT ( ${mugwumps[counter][0]} , ${mugwumps[counter][1]} )`)
        }
    }
}

function placeMugwumps() {
    for (let counter = 0; counter < numMugwumps; counter++) {
        mugwumps[counter][0] = Math.floor(Math.random() * gridSize);
        mugwumps[counter][1] = Math.floor(Math.random() * gridSize);
    }
}