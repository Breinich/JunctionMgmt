// Agent trafficLight in project 

/* Initial beliefs and rules */

waitingSum(0).
waitingCount(0).

/* Initial goals */

/* Plans */

+color(C) : .my_name(N) <- .print("hello world, I am ", N," and my color is: ", C).

+newBid(X1,Y1) : waitingSum(X) & waitingCount(Y) <-
    -waitingSum(X);
    +waitingSum(X1);
    -waitingCount(Y);
    +waitingCount(Y1);
    -newBid(X1,Y1);
    .print("waiting sum is: ",X1," and waiting count is: ",Y1);
    !placeBid.

+!placeBid : waitingSum(X) & waitingCount(Y) <-
    .print("placing bid");
    .send(controller, achieve, updateBid(X, Y)).
