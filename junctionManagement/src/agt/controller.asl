// Agent controller in project 

/* Initial beliefs and rules */

// waiting weight and waiting count
bid(0,0)[source(trafficLight1)].
bid(0,0)[source(trafficLight2)].
bid(0,0)[source(trafficLight3)].
bid(0,0)[source(trafficLight4)].

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello main.world.").

+!updateBid(W,C)[source(S)] : bid(W1,C1)[source(X)] & bid(W2,C2)[source(Y)] & bid(W3,C3)[source(Z)] <-
    .print("new bid: ",W,", ",C," from: ",S);
    .abolish(bid(_,_)[source(S)]);
    +bid(W,C)[source(S)];
    optimizeLamps(W1,C1,X,W2,C2,Y,W3,C3,Z,W,C,S).