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

    Mechanic1 steps on the twelfth pipe
Status:

    Passed

------------
### Test 3: Saboteur moves to an occupied pipe
Command:

    Move Saboteur3 Pipe2
Expected Result:

    Error message indicating that the pipe is already occupied by Mechanic1.
Result:

    Nothing happened
Status:

    Failed


------------
### Test 4: Mechanic moves to an occupied pipe
Command:

    Move Mechanic3 Pipe12
Expected Result:

    Error message indicating that the pipe is already occupied by Saboteur1.
Result:

    Nothing happened
Status:

    Failed


------------
### Test 5: Saboteur moves to a non-existent pipe
Command:

    Move Saboteur1 Pipe999
Expected Result:

    Error message indicating that the pipe does not exist.
Result:

    Null pointer exception
Status:

    Failed


------------
### Test 6: Mechanic moves to a non-existent pipe
Command:

    Move Mechanic1 Pipe999
Expected Result:

    Error message indicating that the pipe does not exist.
Result:

    Null pointer exception
Status:

    Failed

------------
### Test 7: Unknown entity tries to move
Command:

    Move UnknownEntity Pipe2
Expected Result:

    Error message indicating that the entity does not exist.
Result:

    "Ervenytelen parancs" exception
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

    Game state showing Saboteur1 at Pipe3 and Mechanic1 at Pipe13.
Status:

    Passed

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

    Game state showing Saboteur1 at Pipe3 and Mechanic1 at Pipe13.
Status:

    Passed

------------
### Test 10: Saboteur tries to move to a blocked pipe
Command:

    Move Saboteur1 Pipe13
Expected Result:

    Error message indicating that the pipe is blocked.
Result:

    Nothing happened
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

    Nothing happened
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

    Nothing happened
Status:

    Failed

------------
### Test 13: Saboteur tries to move to a pipe that is blocked
Command:

    Move Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is blocked.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 14: Mechanic tries to move to a pipe that is blocked
Command:

    Move Mechanic1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is blocked.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 15: Saboteur tries to move to a pipe that is broken
Command:

    Move Saboteur1 Pipe13
Expected Result:

    Appropriate error message indicating that the pipe is broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 16: Mechanic tries to move to a pipe that is broken
Command:

    Move Mechanic1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 17: Saboteur tries to repair a broken pipe
Command:

    Repair Saboteur1 Pipe13
Expected Result:

    Appropriate error message indicating that the Saboteur cannot repair pipes.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 18: Mechanic repairs a broken pipe
Command:

    Repair Mechanic1 Pipe13
Expected Result:

    Pipe13 is repaired
Result:

    Pipe13 is repaired
Status:

    Passed

------------
### Test 19: Saboteur breaks a pipe
Command:

    Damage Saboteur1 Pipe2
Expected Result:

    Pipe2 is broken
Result:

    Pipe2 is broken
Status:

    Passed

------------
### Test 20: Mechanic tries to break a pipe
Command:

    Damage Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the Mechanic cannot break pipes.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 21: Saboteur tries to break a pipe that is already broken
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe is already broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 22: Mechanic tries to repair a pipe that is not broken
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the pipe is not broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 23: Saboteur tries to move to a pipe that is broken and then repaired
Command:

    Move Saboteur1 Pipe2
    Repair Mechanic1 Pipe13
    Move Saboteur1 Pipe12
Expected Result:

    Saboteur1 moves to Pipe12
Result:

    Saboteur1 moves to Pipe12
Status:

    Passed

------------
### Test 24: Mechanic tries to move to a pipe that is broken and then repaired
Command:

    Move Mechanic1 Pipe2
    Repair Mechanic1 Pipe12
    Move Mechanic1 Pipe12
Expected Result:

    Mechanic1 moves to Pipe12
Result:

    Mechanic1 moves to Pipe12
Status:

    Passed

------------
### Test 25: Saboteur tries to break a pipe that is occupied by a Mechanic
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Appropriate error message indicating that the pipe cannot be broken because it is occupied by a Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 26: Mechanic tries to repair a pipe that is occupied by a Saboteur
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Appropriate error message indicating that the pipe cannot be repaired because it is occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed
------------
### Test 27: Saboteur tries to move to a pipe that is occupied by a Mechanic and then vacated
Command:

    Move Saboteur1 Pipe12
    Move Mechanic1 Pipe3
    Move Saboteur1 Pipe12
Expected Result:

    Saboteur1 moves to Pipe12
Result:

    Saboteur1 moves to Pipe12
Status:

    Passed

------------
### Test 28: Mechanic tries to move to a pipe that is occupied by a Saboteur and then vacated
Command:

    Move Mechanic1 Pipe2
    Move Saboteur1 Pipe3
    Move Mechanic1 Pipe2
Expected Result:

    Mechanic1 moves to Pipe2
Result:

    Mechanic1 moves to Pipe2
Status:

    Passed

------------
### Test 29: Saboteur tries to move to a pipe that is occupied by another Saboteur
Command:

    Move Saboteur1 Pipe2
    Move Saboteur2 Pipe2
Expected Result:

    Error message indicating that the pipe is already occupied by another Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 30: Mechanic tries to move to a pipe that is occupied by another Mechanic
Command:

    Move Mechanic1 Pipe12
    Move Mechanic2 Pipe12
Expected Result:

    Error message indicating that the pipe is already occupied by another Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 31: Saboteur tries to break a pipe that is already broken
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe is already broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 32: Mechanic tries to repair a pipe that is not broken
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe is not broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 33: Saboteur tries to move to a pipe that is broken and then repaired
Command:

    Move Saboteur1 Pipe2
    Repair Mechanic1 Pipe13
    Move Saboteur1 Pipe12
Expected Result:

    Saboteur1 moves to Pipe12
Result:

    Saboteur1 moves to Pipe12
Status:

    Passed

------------
### Test 34: Mechanic tries to move to a pipe that is broken and then repaired
Command:

    Move Mechanic1 Pipe2
    Repair Mechanic1 Pipe12
    Move Mechanic1 Pipe12
Expected Result:

    Mechanic1 moves to Pipe12
Result:

    Mechanic1 moves to Pipe12
Status:

    Passed

------------
### Test 35: Saboteur tries to break a pipe that is occupied by a Mechanic
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be broken because it is occupied by a Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 36: Mechanic tries to repair a pipe that is occupied by a Saboteur
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe cannot be repaired because it is occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 37: Saboteur tries to move to a pipe that is occupied by a Mechanic and then vacated
Command:

    Move Saboteur1 Pipe12
    Move Mechanic1 Pipe3
    Move Saboteur1 Pipe12
Expected Result:

    Saboteur1 moves to Pipe12
Result:

    Saboteur1 moves to Pipe12
Status:

    Failed

------------
### Test 38: Mechanic tries to move to a pipe that is occupied by a Saboteur and then vacated
Command:

    Move Mechanic1 Pipe2
    Move Saboteur1 Pipe3
    Move Mechanic1 Pipe2
Expected Result:

    Mechanic1 moves to Pipe2
Result:

    Mechanic1 moves to Pipe2
Status:

    Passed

------------
### Test 39: Saboteur tries to break a pipe that is already occupied by a Mechanic
Command:

    Break Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be broken because it is occupied by a Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 40: Mechanic tries to repair a pipe that is already occupied by a Saboteur
Command:

    Repair Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe cannot be repaired because it is occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 41: Saboteur tries to move to a pipe that is already occupied by a Mechanic
Command:

    Move Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe is already occupied by a Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 42: Mechanic tries to move to a pipe that is already occupied by a Saboteur
Command:

    Move Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe is already occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 43: Saboteur tries to break a pipe that is already occupied by a Saboteur
Command:

    Break Saboteur1 Pipe2
Expected Result:

    Error message indicating that the pipe cannot be broken because it is occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 44: Saboteur tries to disconnect a connected pipe
Command:

    Disconnect Saboteur1 Pipe2
Expected Result:

    Pipe2 is disconnected
Result:

    Pipe2 is disconnected
Status:

    Passed

------------
### Test 45: Mechanic tries to disconnect a connected pipe
Command:

    Disconnect Mechanic1 Pipe12
Expected Result:

    Error message indicating that the Mechanic cannot disconnect pipes.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 46: Saboteur tries to connect a disconnected pipe
Command:

    Connect Saboteur1 Pipe2
Expected Result:

    Error message indicating that the Saboteur cannot connect pipes.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 47: Mechanic tries to connect a disconnected pipe
Command:

    Connect Mechanic1 Pipe12
Expected Result:

    Pipe12 is connected
Result:

    Pipe12 is connected
Status:

    Passed

------------
### Test 48: Saboteur tries to disconnect a pipe that is already disconnected
Command:

    Disconnect Saboteur1 Pipe2
Expected Result:

    Error message indicating that the pipe is already disconnected.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 49: Mechanic tries to connect a pipe that is already connected
Command:

    Connect Mechanic1 Pipe12
Expected Result:

    Error message indicating that the pipe is already connected.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 50: Saboteur tries to disconnect a pipe that is occupied by a Mechanic
Command:

    Disconnect Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be disconnected because it is occupied by a Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 51: Mechanic tries to connect a pipe that is occupied by a Saboteur
Command:

    Connect Mechanic1 Pipe2
Expected Result:

    Error message indicating that the pipe cannot be connected because it is occupied by a Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 52: Saboteur tries to disconnect a pipe that is occupied by another Saboteur
Command:

    Disconnect Saboteur1 Pipe2
Expected Result:

    Error message indicating that the pipe cannot be disconnected because it is occupied by another Saboteur.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 53: Mechanic tries to connect a pipe that is occupied by another Mechanic
Command:

    Connect Mechanic1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be connected because it is occupied by another Mechanic.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 54: Saboteur tries to disconnect a pipe that is broken
Command:

    Disconnect Saboteur1 Pipe13
Expected Result:

    Error message indicating that the pipe cannot be disconnected because it is broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 55: Mechanic tries to connect a pipe that is broken
Command:

    Connect Mechanic1 Pipe13
Expected Result:

    Error message indicating that the pipe cannot be connected because it is broken.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 56: Saboteur tries to disconnect a pipe that is blocked
Command:

    Disconnect Saboteur1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be disconnected because it is blocked.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 57: Mechanic tries to connect a pipe that is blocked
Command:

    Connect Mechanic1 Pipe12
Expected Result:

    Error message indicating that the pipe cannot be connected because it is blocked.
Result:

    Nothing happened
Status:

    Failed

------------
### Test 58: Saboteur tries to disconnect a pipe that is disconnected and then connected
Command:

    Disconnect Saboteur1 Pipe2
    Connect Mechanic1 Pipe2
    Disconnect Saboteur1 Pipe2
Expected Result:

    Pipe2 is disconnected
Result:

    Pipe2 is disconnected
Status:

    Passed

------------
### Test 59: Mechanic tries to connect a pipe that is connected and then disconnected
Command:

    Connect Mechanic1 Pipe12
    Disconnect Saboteur1 Pipe12
    Connect Mechanic1 Pipe12
Expected Result:

    Pipe12 is connected
Result:

    Pipe12 is connected
Status:

    Passed