// Agent trafficLight in project 

/* Initial beliefs and rules */

waitingSum(0).
waitingCount(0).

/* Initial goals */

/* Plans */

+color(C) : .my_name(N) <- .print("hello world, I am ", N," and my color is: ", C).

+newBid(X1,Y1) : waitingSum(X) & waitingCount(Y) & (not (X1 == X) | not (Y1 == Y)) <-
    -waitingSum(X);
    +waitingSum(X1);
    -waitingCount(Y);
    +waitingCount(Y1);
    .abolish(newBid(_,_));
    .print("waiting sum is: ",X1," and waiting count is: ",Y1);
    !placeBid.

+newBid(X1, Y1) : waitingSum(X) & waitingCount(Y) & (X1 == X) & (Y1 == Y) <-
    .abolish(newBid(_,_)).

+!placeBid : waitingSum(X) & waitingCount(Y) <-
    .print("placing bid");
    .send(controller, achieve, updateValue(X, Y)).
