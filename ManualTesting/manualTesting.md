# Manual Testing

------------
### Test 1: Saboteur moves to Pipe2
Command:

    Move Saboteur1 Pipe2
Expected Result:

    Saboteur1 steps on the second pipe
Result:

    Saboteur1 steps on the second pipe
Status:

    Passed

------------
### Test 2: Mechanic moves to Pipe12
Command:

    Move Mechanic1 Pipe12
Expected Result:

    Mechanic1 steps on the twelfth pipe
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 3: Saboteur moves to an occupied pipe
Command:

    Move Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe is already occupied by Mechanic1.
Result:

    Game ended with Mechanics winning
Status:

    Failed


------------
### Test 4: Mechanic moves to an occupied pipe
Command:

    Move Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe is already occupied by Saboteur1.
Result:

    Game ended with Mechanics winning
Status:

    Failed


------------
### Test 5: Saboteur moves to a non-existent pipe
Command:

    Move Saboteur1 Pipe999
Expected Result:

    Error message indicating that the pipe does not exist.
Result:

    Game ended with Mechanics winning
Status:

    Failed


------------
### Test 6: Mechanic moves to a non-existent pipe
Command:

    Move Mechanic1 Pipe999
Expected Result:

    Error message indicating that the pipe does not exist.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 7: Unknown entity tries to move
Command:

    Move UnknownEntity Pipe2
Expected Result:

    Error message indicating that the entity does not exist.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 8: Game state after a series of movements
Command:

    Move Saboteur1 Pipe2
    Move Mechanic1 Pipe12
    Move Saboteur1 Pipe3
    Move Mechanic1 Pipe13
Expected Result:

    Game state showing Saboteur1 at Pipe3 and Mechanic1 at Pipe13.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 9: Game state after a series of movements involving both Saboteur1 and Mechanic1
Command:

    Move Saboteur1 Pipe2
    Move Mechanic1 Pipe12
    Move Saboteur1 Pipe3
    Move Mechanic1 Pipe13
Expected Result:

    Game state showing Saboteur1 at Pipe3 and Mechanic1 at Pipe13.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 10: Saboteur tries to move to a blocked pipe
Command:

    Move Saboteur1 Pipe13
Expected Result:

    Error message indicating that the pipe is blocked.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 11: Saboteur moves to a pipe that is already occupied by Saboteur
Command:

    Move Saboteur1 Pipe2
    Move Saboteur2 Pipe2
Expected Result:

    Appropriate error message indicating that the pipe is already occupied by another Saboteur.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 12: Mechanic moves to a pipe that is already occupied by another Mechanic
Command:

    Move Mechanic1 Pipe12
    Move Mechanic2 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is already occupied by another Mechanic.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 13: Saboteur tries to move to a pipe that is blocked
Command:

    Move Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is blocked.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 14: Mechanic tries to move to a pipe that is blocked
Command:

    Move Mechanic1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is blocked.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 15: Saboteur tries to move to a pipe that is broken
Command:

    Move Saboteur1 Pipe13
Expected Result:

    Appropriate error message indicating that the pipe is broken.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 16: Mechanic tries to move to a pipe that is broken
Command:

    Move Mechanic1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is broken.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 17: Saboteur tries to repair a broken pipe
Command:

    Repair Saboteur1 Pipe13
Expected Result:

    Appropriate error message indicating that the Saboteur cannot repair pipes.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 18: Mechanic repairs a broken pipe
Command:

    Repair Mechanic1 Pipe13
Expected Result:

    Confirmation message indicating that the pipe has been repaired.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 19: Saboteur breaks a pipe
Command:

    Break Saboteur1 Pipe2
Expected Result:

    Confirmation message indicating that the pipe has been broken.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 20: Mechanic tries to break a pipe
Command:

    Break Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the Mechanic cannot break pipes.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 21: Saboteur tries to break a pipe that is already broken
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is already broken.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 22: Mechanic tries to repair a pipe that is not broken
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the pipe is not broken.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 23: Saboteur tries to move to a pipe that is broken and then repaired
Command:

    Move Saboteur1 Pipe2
    Repair Mechanic1 Pipe13
    Move Saboteur1 Pipe12
Expected Result:

    Confirmation message indicating that Saboteur1 has moved to the repaired pipe.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 24: Mechanic tries to move to a pipe that is broken and then repaired
Command:

    Move Mechanic1 Pipe2
    Repair Mechanic1 Pipe12
    Move Mechanic1 Pipe12
Expected Result:

    Confirmation message indicating that Mechanic1 has moved to the repaired pipe.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 25: Saboteur tries to break a pipe that is occupied by a Mechanic
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe cannot be broken because it is occupied by a Mechanic.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 26: Mechanic tries to repair a pipe that is occupied by a Saboteur
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the pipe cannot be repaired because it is occupied by a Saboteur.
Result:

    Game ended with Mechanics winning
Status:

    Failed
------------
### Test 27: Saboteur tries to move to a pipe that is occupied by a Mechanic and then vacated
Command:

    Move Saboteur1 Pipe12
    Move Mechanic1 Pipe3
    Move Saboteur1 Pipe12
Expected Result:

    Confirmation message indicating that Saboteur1 has moved to the pipe that was vacated by Mechanic1.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------
### Test 28: Mechanic tries to move to a pipe that is occupied by a Saboteur and then vacated
Command:

    Move Mechanic1 Pipe2
    Move Saboteur1 Pipe3
    Move Mechanic1 Pipe2
Expected Result:

    Confirmation message indicating that Mechanic1 has moved to the pipe that was vacated by Saboteur1.
Result:

    Game ended with Mechanics winning
Status:

    Failed

------------